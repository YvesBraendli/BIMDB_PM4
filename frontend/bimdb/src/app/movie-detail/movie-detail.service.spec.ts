import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Observable } from 'rxjs';
import { MovieDetails, WatchProvidersResult } from '../generated/contract';
import { MovieDetailService } from './movie-detail.service';

describe('MovieDetailService', () => {
	let service: MovieDetailService;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		service = TestBed.inject(MovieDetailService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	describe('when getMovie is called', () => {
		let result: Observable<MovieDetails>;

		beforeEach(() => {
			result = service.getMovie(100);
		});

		it('should return an observable', () => {
			expect(result).toBeTruthy();
		});
	});

	describe('when getWatchProviders is called', () => {
		let result: Observable<WatchProvidersResult>;

		beforeEach(() => {
			result = service.getWatchProviders(100);
		});

		it('should return an observable', () => {
			expect(result).toBeTruthy();
		});
	});
});
