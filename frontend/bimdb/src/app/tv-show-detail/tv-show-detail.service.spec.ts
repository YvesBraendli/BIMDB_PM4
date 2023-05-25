import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Environment } from '../core/models/environment';
import { EnvironmentService } from '../core/services/environment.service';
import { TvShowDetails, TvShowSeasonDetails, WatchProviders, WatchProvidersResult } from '../generated/contract';
import { TvShowDetailService } from './tv-show-detail.service';

describe('TvShowDetailService', () => {
	let service: TvShowDetailService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		service = TestBed.inject(TvShowDetailService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return tv show', () => {
		const id = 1;
		const mockResponse = {
			id
		} as TvShowDetails;

		service.getTvShow(id).subscribe(res => {
			expect(res.id).toBe(id);
		});

		const discoverRequest = httpTestingController.expectOne(`/api/tv/${id}`);
		expect(discoverRequest.request.method).toEqual('GET');
		discoverRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should return tv season', () => {
		const id = 1;
		const seasonNumber = 1;
		const mockResponse = {
			id,
			seasonNumber
		} as TvShowSeasonDetails;

		service.getTvSeason(id, seasonNumber).subscribe(res => {
			expect(res.id).toBe(id);
		});

		const discoverRequest = httpTestingController.expectOne(`/api/tv/${id}/${seasonNumber}`);
		expect(discoverRequest.request.method).toEqual('GET');
		discoverRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should return watch providers', () => {
		const id = 1;
		const watchProviders: WatchProviders[] = [];
		const mockResponse = {
			watchProviders
		} as WatchProvidersResult;

		service.getWatchProviders(id).subscribe(res => {
			expect(res.watchProviders).toEqual(watchProviders);
		});

		const watchProvidersRequest = httpTestingController.expectOne(`/api/tv/${id}/watch-providers`);
		expect(watchProvidersRequest.request.method).toEqual('GET');
		watchProvidersRequest.flush(mockResponse);

		httpTestingController.verify();
	});
});
