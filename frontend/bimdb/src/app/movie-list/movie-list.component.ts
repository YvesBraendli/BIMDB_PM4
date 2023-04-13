import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MultiAutocompleteComponent } from '../autocomplete/multi-autocomplete/multi-autocomplete.component';
import { AutocompleteComponent, AutocompleteOption } from '../autocomplete/single-autocomplete/autocomplete.component';
import { Media } from '../media-list/media';
import { MediaListComponent } from '../media-list/media-list.component';
import { ConfigService } from '../shared/services/config.service';

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
	public countries: AutocompleteOption[] = [];

	public constructor(private configService: ConfigService) {
		this.movies = Array.from({ length: 120 }, (_, i) => ({
			name: `Barbie ${i + 1}`,
			date: new Date(2023, 6, 21),
			imgUrl: 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xMVsBwvvw1iKhC97rtI5mB91C0O.jpg'
		} as Media));
		this.configService.getCountries().subscribe(countries => this.countries = countries.map(country => ({ value: country.english_name, displayValue: country.english_name })));
	}

}
