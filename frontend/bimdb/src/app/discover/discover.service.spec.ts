import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Environment } from '../core/models/environment';
import { EnvironmentService } from '../core/services/environment.service';
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
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		service = TestBed.inject(DiscoverService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return discover movie', () => {
		const mockResponse = {
			page: 1,
			totalPages: 10,
			totalResults: 200,
			results: []
		} as DiscoverMovie;

		service.discover(1).subscribe(res => {
			expect(res.page).toBe(1);
		});

		const discoverRequest = httpTestingController.expectOne('/api/discover/movies?page=1');
		expect(discoverRequest.request.method).toEqual('GET');
		discoverRequest.flush(mockResponse);

		httpTestingController.verify();
	});
});
