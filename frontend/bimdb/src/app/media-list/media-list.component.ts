import { CommonModule, DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { RouterModule } from '@angular/router';
import { ImageSize } from '../core/models/tmdb-img-config';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { PaginatorComponent } from '../paginator/paginator.component';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { Media, MediaType } from './media';


@Component({
	selector: 'app-media-list',
	standalone: true,
	providers: [DatePipe],
	templateUrl: './media-list.component.html',
	styleUrls: ['./media-list.component.scss'],
	imports: [CommonModule, PaginatorComponent, LocaleDatePipe, MatPaginatorModule, RouterModule, TmdbImgComponent]
})
export class MediaListComponent {
	@Input() public page = 1;
	@Input() public totalPages = 10;
	@Input() public media: Media[] = [];
	@Output() public pageChange = new EventEmitter<number>();
	public ImageSize = ImageSize;
	private static readonly maxPage = 500;

	public onPageChange(page: number): void {
		this.pageChange.emit(page);
	}

	public getMediaLink(media: Media): string {
		switch (media.mediaType) {
			case MediaType.Movie:
				return `/movie/${media.id}`;
			case MediaType.TvShow:
				return `/tv-show/${media.id}`;
			default:
				return '';
		}
	}

	public get maxPages(): number {
		return this.totalPages > MediaListComponent.maxPage ? MediaListComponent.maxPage : this.totalPages;
	}
}
