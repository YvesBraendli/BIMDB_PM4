import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Media, MediaType } from '../media-list/media';
import { TvShowListService } from './tv-show-list.service';
import { MediaListComponent } from '../media-list/media-list.component';

@Component({
	selector: 'app-tv-show-list',
	standalone: true,
	imports: [CommonModule, MediaListComponent],
	templateUrl: './tv-show-list.component.html',
	styleUrls: ['./tv-show-list.component.scss']
})
export class TvShowListComponent {
	public page = 1;
	public totalPages = 10;
	public tvShows: Media[] = [];

	public constructor(private tvShowListService: TvShowListService) {
		this.discover();
	}

	public discover(): void {
		this.tvShowListService.discover(this.page).subscribe((result) => {
			this.tvShows = result.results.map((tvShow) => ({
				id: tvShow.id,
				title: tvShow.name,
				release_date: new Date(tvShow.first_air_date),
				poster_path: tvShow.poster_path,
				mediaType: MediaType.TvShow
			}) as Media);
			this.totalPages = result.total_pages;
		});
	}

	public onPageChange(page: number): void {
		this.page = page;
		this.discover();
	}
}
