import { TestBed } from '@angular/core/testing';

import { SearchService } from './search.service';

describe('SearchService', () => {
	let service: SearchService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(SearchService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should search', () => {
		service.search('bar').subscribe(movies => {
			expect(movies.length).toBe(1);
			expect(movies[0]?.name).toBe('Barbie');
		});
	});

});
