import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Movie } from '../discover/discover.component';

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
}
