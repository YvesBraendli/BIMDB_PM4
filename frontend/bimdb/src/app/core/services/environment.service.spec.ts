import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Environment } from '../models/environment';
import { EnvironmentService } from './environment.service';
import { WindowService } from './window.service';

describe('EnvironmentService', () => {
	let service: EnvironmentService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		service = TestBed.inject(EnvironmentService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should load dev config file', () => {
		const apiBaseUrl = '/api';
		spyOnProperty(TestBed.inject(WindowService), 'location').and.returnValue({ host: 'localhost:4200' } as Location);
		service.loadEnvironmentConfig().subscribe(() => {
			expect(service.getConfig()).toBeDefined();
			expect(service.getConfig().apiBaseUrl).toBe(apiBaseUrl);
		});

		const apiConfigRequest = httpTestingController.expectOne('/assets/environments/environment.json');
		expect(apiConfigRequest.request.method).toEqual('GET');
		apiConfigRequest.flush({
			apiBaseUrl
		} as Environment);

		httpTestingController.verify();
	});

	it('should load stage config file', () => {
		const apiBaseUrl = 'https://bimdb-backend-stage.pm4.init-lab.ch/api';
		spyOnProperty(TestBed.inject(WindowService), 'location').and.returnValue({ host: 'bimdb-stage.pm4.init-lab.ch' } as Location);
		service.loadEnvironmentConfig().subscribe(() => {
			expect(service.getConfig()).toBeDefined();
			expect(service.getConfig().apiBaseUrl).toBe(apiBaseUrl);
		});

		const apiConfigRequest = httpTestingController.expectOne('/assets/environments/environment.stage.json');
		expect(apiConfigRequest.request.method).toEqual('GET');
		apiConfigRequest.flush({
			apiBaseUrl
		} as Environment);

		httpTestingController.verify();
	});

	it('should load prod config file', () => {
		const apiBaseUrl = 'https://bimdb-backend-prod.pm4.init-lab.ch/api';
		spyOnProperty(TestBed.inject(WindowService), 'location').and.returnValue({ host: 'bimdb-prod.pm4.init-lab.ch' } as Location);
		service.loadEnvironmentConfig().subscribe(() => {
			expect(service.getConfig()).toBeDefined();
			expect(service.getConfig().apiBaseUrl).toBe(apiBaseUrl);
		});

		const apiConfigRequest = httpTestingController.expectOne('/assets/environments/environment.prod.json');
		expect(apiConfigRequest.request.method).toEqual('GET');
		apiConfigRequest.flush({
			apiBaseUrl
		} as Environment);

		httpTestingController.verify();
	});
});
