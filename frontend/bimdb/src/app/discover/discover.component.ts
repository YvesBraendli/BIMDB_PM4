import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Media, MediaType } from '../media-list/media';
import { MediaListComponent } from '../media-list/media-list.component';
import { DiscoverService } from './discover.service';

@Component({
	standalone: true,
	selector: 'app-discover',
	templateUrl: './discover.component.html',
	styleUrls: ['./discover.component.scss'],
	imports: [CommonModule, MediaListComponent],
	providers: [DatePipe]
})
export class DiscoverComponent {
	public page = 1;
	public totalPages = 10;
	public movies: Media[] = [];

	public constructor(private discoverService: DiscoverService) {
		this.discover();
	}

	public discover(): void {
		this.discoverService.discover(this.page).subscribe({
			next: result => {
				this.movies = result.results.map((movie) => ({
					id: movie.id,
					title: movie.title,
					release_date: movie.release_date,
					poster_path: movie.poster_path,
					mediaType: MediaType.Movie
				}) as Media);
				this.totalPages = result.total_pages;
			}
		});
	}

	public onPageChange(page: number): void {
		this.page = page;
		this.discover();
	}
}

export class Movie {
	public id?: number;
	public name?: string;
	public date?: Date;
	public imgUrl?: string;
}
