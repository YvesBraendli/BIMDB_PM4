import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Movie } from './discover.component';

@Injectable({
	providedIn: 'root'
})
export class DiscoverService {
	private static readonly MOVIES = Array.from({ length: 120 }, (_, i) => ({
		id: i,
		name: `Barbie ${i + 1}`,
		date: new Date(2023, 6, 21),
		imgUrl: 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xMVsBwvvw1iKhC97rtI5mB91C0O.jpg'
	} as Movie));

	// public constructor(private httpClient: HttpClient) {}

	public discover(page: number): Observable<DiscoverResults> {
		return of({ totalPages: DiscoverService.MOVIES.length / 20, movies: DiscoverService.MOVIES.slice((page - 1) * 20, page * 20) });
	}

}

export interface DiscoverResults {
	movies: Movie[];
	totalPages: number;
}
