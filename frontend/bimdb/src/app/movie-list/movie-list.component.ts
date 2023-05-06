import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MultiAutocompleteComponent } from '../autocomplete/multi-autocomplete/multi-autocomplete.component';
import { AutocompleteComponent } from '../autocomplete/single-autocomplete/autocomplete.component';
import { Media, MediaType } from '../media-list/media';
import { MediaListComponent } from '../media-list/media-list.component';

@Component({
	selector: 'app-movie-list',
	standalone: true,
	templateUrl: './movie-list.component.html',
	styleUrls: ['./movie-list.component.scss'],
	imports: [CommonModule, MediaListComponent, AutocompleteComponent, MultiAutocompleteComponent]
})
export class MovieListComponent {
	public page = 1;
	public totalPages = 10;
	public movies: Media[] = [];

	public constructor() {
		this.movies = Array.from({ length: 120 }, (_, i) => ({
			id: i,
			title: `Barbie ${i + 1}`,
			release_date: new Date(2023, 6, 21),
			mediaType: MediaType.Movie
		} as Media));
	}

}
