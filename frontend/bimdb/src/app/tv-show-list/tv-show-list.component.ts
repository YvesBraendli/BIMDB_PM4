import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Media } from '../generated/contract';
import { MediaListComponent } from '../media-list/media-list.component';
import { TvShowListService } from './tv-show-list.service';

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
			this.tvShows = result.results;
			this.totalPages = result.totalPages;
		});
	}

	public onPageChange(page: number): void {
		this.page = page;
		this.discover();
	}
}
