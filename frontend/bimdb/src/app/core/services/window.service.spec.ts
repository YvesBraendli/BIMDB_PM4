import { TestBed } from '@angular/core/testing';

import { WindowService } from './window.service';

describe('WindowService', () => {
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

	it('should return navigator', () => {
		expect(service.navigator).toEqual(window.navigator);
	});

	it('should reload window', () => {
		const reloadSpy = spyOnProperty(service, 'location').and.returnValue({ reload: () => undefined } as Location);
		service.reload();
		expect(reloadSpy).toHaveBeenCalled();
	});

});
