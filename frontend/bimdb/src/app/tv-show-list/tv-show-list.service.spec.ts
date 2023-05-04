import { TvShowListService } from './tv-show-list.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { DiscoverTv } from '../generated/contract';

describe('TvShowListService', () => {
	let service: TvShowListService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		service = TestBed.inject(TvShowListService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return discover tv show', () => {
		const mockResponse = {
			page: 1,
			total_pages: 1,
			total_results: 0,
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
