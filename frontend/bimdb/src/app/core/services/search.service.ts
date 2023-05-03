import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { AutocompleteOption } from 'src/app/autocomplete/single-autocomplete/autocomplete.component';
import { Movie } from 'src/app/discover/discover.component';

@Injectable({
	providedIn: 'root'
})
export class SearchService {

	private static readonly movies = [
		{ name: 'Barbie', date: new Date(2023, 6, 21) },
		{ name: 'Shazam', date: new Date(2023, 6, 21) },
		{ name: 'Ant-Man', date: new Date(2023, 6, 21) },
		{ name: 'Thor', date: new Date(2023, 6, 21) },
	];

	// public constructor(private httpClient: HttpClient) { }

	public search(query: string): Observable<Movie[]> {
		// return this.httpClient.get<Movie[]>('/api/search', { params: { query } });
		return of(SearchService.movies.filter(movie => movie.name.toLowerCase().includes(query.toLowerCase())));
	}

	public suggestions(query: string): Observable<AutocompleteOption[]> {
		return of(SearchService.movies.filter(movie => movie.name.toLowerCase().includes(query.toLowerCase())))
			.pipe(map(movies => movies.map(movie => ({ displayValue: movie.name, value: movie.name }))));
	}
}
