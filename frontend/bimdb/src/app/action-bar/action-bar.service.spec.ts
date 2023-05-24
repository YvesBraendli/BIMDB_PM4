import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { MediaType } from '../generated/contract';
import { ActionBarService } from './action-bar.service';

describe('ActionBarService', () => {
	let service: ActionBarService;
	let httpService: HttpService;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		service = TestBed.inject(ActionBarService);
		httpService = TestBed.inject(HttpService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should check favorite status', () => {
		spyOn(httpService, 'get').and.returnValue(of({ isFavorite: true }));
		service.checkFavoriteStatus(1, MediaType.Movie).subscribe(res => {
			expect(res).toEqual({ isFavorite: true });
		});
	});

	it('should add to favorites', () => {
		spyOn(httpService, 'post').and.returnValue(of({ isFavorite: true }));
		service.addFavorite(2, MediaType.TvShow).subscribe(res => {
			expect(res).toEqual({ isFavorite: true });
		});
	});

	it('should remove from favorites', () => {
		spyOn(httpService, 'delete').and.returnValue(of({ isFavorite: false }));
		service.removeFavorite(3, MediaType.Person).subscribe(res => {
			expect(res).toEqual({ isFavorite: false });
		});
	});

});
