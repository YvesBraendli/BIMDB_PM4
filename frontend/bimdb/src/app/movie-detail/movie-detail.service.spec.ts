import { TestBed } from '@angular/core/testing';
import { MovieDetail, MovieDetailService } from './movie-detail.service';
import { Observable } from 'rxjs';

describe('MovieDetailService', () => {
	let service: MovieDetailService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(MovieDetailService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	describe('when getMovie is called', () => {
		let result: Observable<MovieDetail>;

		beforeEach(() => {
			result = service.getMovie(100);
		});

		it('should return an observable', () => {
			expect(result).toBeTruthy();
		});
	});
});
