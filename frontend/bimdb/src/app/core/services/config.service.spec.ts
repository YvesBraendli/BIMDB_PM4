import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { throwError } from 'rxjs';
import { ApiConfig, ApiImagesConfig } from 'src/app/generated/contract';
import { Environment } from '../models/environment';
import { ImgSizeConfig, TmdbImgConfig } from '../models/tmdb-img-config';
import { ConfigService } from './config.service';
import { EnvironmentService } from './environment.service';
import { HttpService } from './http.service';

describe('ConfigService', () => {
	let service: ConfigService;
	let mockHttpService: HttpService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		service = TestBed.inject(ConfigService);
		mockHttpService = TestBed.inject(HttpService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should load app config', () => {
		const mockApiConfig = {
			images: {
				baseUrl: '',
				secureBaseUrl: '',
				backdropSizes: [],
				logoSizes: [],
				posterSizes: [],
				profileSizes: [],
				stillSizes: []
			} as ApiImagesConfig
		} as ApiConfig;
		service.loadAppConfig().subscribe();
		const apiConfigRequest = httpTestingController.expectOne('/api/config');
		expect(apiConfigRequest.request.method).toEqual('GET');
		apiConfigRequest.flush(mockApiConfig);

		const countriesRequest = httpTestingController.expectOne('/api/config/countries');
		expect(countriesRequest.request.method).toEqual('GET');
		countriesRequest.flush([]);

		const languagesRequest = httpTestingController.expectOne('/api/config/languages');
		expect(languagesRequest.request.method).toEqual('GET');
		languagesRequest.flush([]);

		httpTestingController.verify();

	});

	it('should fail loading app config', () => {
		spyOn(mockHttpService, 'get').and.returnValue(throwError(() => 'failed'));
		service.loadAppConfig().subscribe({
			error: (error) => {
				expect(error).toBe('failed');
			}
		});

	});

	it('should get img config', () => {
		const secureBaseUrl = 'https://super-secure.com/';
		const mockImgConfigResponse = {
			images: {
				baseUrl: '',
				secureBaseUrl,
				backdropSizes: ['w100', 'w200', 'w300', 'original'],
				logoSizes: ['w100', 'w200', 'w300', 'w400', 'original'],
				posterSizes: [],
				profileSizes: [],
				stillSizes: []
			} as ApiImagesConfig
		} as ApiConfig;
		httpTestingController.expectNone('/api/config');

		expect(service.getImageBaseUrl()).toBe('');
		expect(service.getTmbdImgConfig()).toEqual({} as TmdbImgConfig);

		const countriesRequest = httpTestingController.expectOne('/api/config');
		expect(countriesRequest.request.method).toEqual('GET');
		countriesRequest.flush(mockImgConfigResponse);

		httpTestingController.verify();
		const backdropImgSizeConfig = new ImgSizeConfig({ small: 'w100', medium: 'w200', large: 'w300', originial: 'original' });
		const logoImgSizeConfig = new ImgSizeConfig({ small: 'w100', medium: 'w300', large: 'w400', originial: 'original' });
		expect(service.getTmbdImgConfig().backdropSizes).toEqual(backdropImgSizeConfig);
		expect(service.getTmbdImgConfig().logoSizes).toEqual(logoImgSizeConfig);
		expect(service.getImageBaseUrl()).toBe(secureBaseUrl);

		httpTestingController.expectNone('/api/config');
	});

	it('should get countries', () => {
		const mockCountriesResponse = [{ iso: 'CH', englishName: 'Switzerland' }];
		httpTestingController.expectNone('/api/config/countries');

		expect(service.getCountries()).toEqual([]);

		const countriesRequest = httpTestingController.expectOne('/api/config/countries');
		expect(countriesRequest.request.method).toEqual('GET');
		countriesRequest.flush(mockCountriesResponse);

		httpTestingController.verify();
		expect(service.getCountries()).toEqual(mockCountriesResponse);

		httpTestingController.expectNone('/api/config/countries');
	});

	it('should get languages', () => {
		const mockLanguagesResponse = [{ iso: 'en', englishName: 'English', name: 'English' }];
		httpTestingController.expectNone('/api/config/languages');

		expect(service.getLanguages()).toEqual([]);

		const countriesRequest = httpTestingController.expectOne('/api/config/languages');
		expect(countriesRequest.request.method).toEqual('GET');
		countriesRequest.flush(mockLanguagesResponse);

		httpTestingController.verify();
		expect(service.getLanguages()).toEqual(mockLanguagesResponse);

		httpTestingController.expectNone('/api/config/languages');
	});

});
