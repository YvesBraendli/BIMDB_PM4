import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DiscoverMovie, DiscoverTv, MediaType, People, SearchResultWrapper } from 'src/app/generated/contract';
import { MOVIES_ROUTE, PEOPLE_ROUTE } from '../constants/routes';
import { Environment } from '../models/environment';
import { EnvironmentService } from './environment.service';
import { SEARCH_BASE_URL, SEARCH_TV_ROUTE, SearchService } from './search.service';

describe('SearchService', () => {
	let service: SearchService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		service = TestBed.inject(SearchService);
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should search', () => {
		const mockResponse = {
			page: 1, totalPages: 1, totalResults: 3, results: [
				{ name: 'movie', mediaType: MediaType.Movie, },
				{ name: 'tv show', mediaType: MediaType.TvShow, },
				{ name: 'person', mediaType: MediaType.Person }
			]
		} as SearchResultWrapper;
		service.search('o').subscribe(res => {
			expect(res).toEqual(mockResponse);
		});
		const getRequest = httpTestingController.expectOne(`/api${SEARCH_BASE_URL}?query=o&page=1`);
		expect(getRequest.request.method).toEqual('GET');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should search movies', () => {
		const mockResponse = {
			page: 1, totalPages: 1, totalResults: 1, results: [{ name: 'movie', mediaType: MediaType.Movie, }]
		} as DiscoverMovie;
		service.searchMovies('movie').subscribe(res => {
			expect(res).toEqual(mockResponse);
		});
		const getRequest = httpTestingController.expectOne(`/api${SEARCH_BASE_URL}/${MOVIES_ROUTE}?query=movie&page=1`);
		expect(getRequest.request.method).toEqual('GET');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should search tv', () => {
		const mockResponse = {
			page: 1, totalPages: 1, totalResults: 1, results: [{ name: 'tv', mediaType: MediaType.TvShow, }]
		} as DiscoverTv;
		service.searchTv('tv').subscribe(res => {
			expect(res).toEqual(mockResponse);
		});
		const getRequest = httpTestingController.expectOne(`/api${SEARCH_BASE_URL}/${SEARCH_TV_ROUTE}?query=tv&page=1`);
		expect(getRequest.request.method).toEqual('GET');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should search people', () => {
		const mockResponse = {
			page: 1, totalPages: 1, totalResults: 1, results: [{ name: 'person' }]
		} as People;
		service.searchPeople('person').subscribe(res => {
			expect(res).toEqual(mockResponse);
		});
		const getRequest = httpTestingController.expectOne(`/api${SEARCH_BASE_URL}/${PEOPLE_ROUTE}?query=person&page=1`);
		expect(getRequest.request.method).toEqual('GET');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

});
