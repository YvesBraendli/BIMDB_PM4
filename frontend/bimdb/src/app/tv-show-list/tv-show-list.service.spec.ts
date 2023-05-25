import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Environment } from '../core/models/environment';
import { EnvironmentService } from '../core/services/environment.service';
import { DiscoverTv } from '../generated/contract';
import { TvShowListService } from './tv-show-list.service';

describe('TvShowListService', () => {
	let service: TvShowListService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		service = TestBed.inject(TvShowListService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return discover tv show', () => {
		const mockResponse = {
			page: 1,
			totalPages: 1,
			totalResults: 0,
			results: []
		} as DiscoverTv;

		service.discover(1).subscribe(res => {
			expect(res.page).toBe(1);
		});

		const discoverRequest = httpTestingController.expectOne('/api/discover/tv?page=1');
		expect(discoverRequest.request.method).toEqual('GET');
		discoverRequest.flush(mockResponse);

		httpTestingController.verify();
	});
});
