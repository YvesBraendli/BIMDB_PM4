import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DiscoverMovie, DiscoverTv, People, SearchResultWrapper } from 'src/app/generated/contract';
import { MOVIES_ROUTE, PEOPLE_ROUTE } from '../constants/routes';
import { HttpService } from './http.service';

export const SEARCH_BASE_URL = '/search';
export const SEARCH_TV_ROUTE = 'tv';

@Injectable({
	providedIn: 'root'
})
export class SearchService {

	public constructor(private httpService: HttpService) { }

	public search(query: string, page = 1, hasCustomErrorHandler = false): Observable<SearchResultWrapper> {
		return this.httpService.get(`${SEARCH_BASE_URL}?query=${query}&page=${page}`, hasCustomErrorHandler);
	}

	public searchMovies(query: string, page = 1, hasCustomErrorHandler = false): Observable<DiscoverMovie> {
		return this.httpService.get(`${SEARCH_BASE_URL}/${MOVIES_ROUTE}?query=${query}&page=${page}`, hasCustomErrorHandler);
	}

	public searchTv(query: string, page = 1, hasCustomErrorHandler = false): Observable<DiscoverTv> {
		return this.httpService.get(`${SEARCH_BASE_URL}/${SEARCH_TV_ROUTE}?query=${query}&page=${page}`, hasCustomErrorHandler);
	}

	public searchPeople(query: string, page = 1, hasCustomErrorHandler = false): Observable<People> {
		return this.httpService.get(`${SEARCH_BASE_URL}/${PEOPLE_ROUTE}?query=${query}&page=${page}`, hasCustomErrorHandler);
	}
}
