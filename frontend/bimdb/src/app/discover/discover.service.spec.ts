import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DiscoverMovie } from '../generated/contract';
import { DiscoverService } from './discover.service';

describe('DiscoverService', () => {
	let service: DiscoverService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		service = TestBed.inject(DiscoverService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return discover movie', () => {
		const mockResponse = {
			page: 1,
			total_pages: 10,
			total_results: 200,
			results: []
		} as DiscoverMovie;

		service.discover(1).subscribe(res => {
			expect(res.page).toBe(1);
		});

		const discoverRequest = httpTestingController.expectOne('/api/discover/movie?page=1');
		expect(discoverRequest.request.method).toEqual('GET');
		discoverRequest.flush(mockResponse);

		httpTestingController.verify();
	});
});
