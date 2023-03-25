import { TestBed } from '@angular/core/testing';

import { WindowService } from './window.service';

describe('SearchService', () => {
	let service: WindowService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(WindowService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should return window location', () => {
		expect(service.location).toEqual(window.location);
	});

	it('should reload window', () => {
		const reloadSpy = spyOnProperty(service, 'location').and.returnValue({ reload: () => undefined } as Location);
		service.reload();
		expect(reloadSpy).toHaveBeenCalled();
	});

});
