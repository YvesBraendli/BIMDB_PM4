import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpHeaderConstants } from '../constants/http-headers';
import { HttpService } from './http.service';

describe('HttpService', () => {
	let service: HttpService;
	let httpTestingController: HttpTestingController;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		service = TestBed.inject(HttpService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should get with custom error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.get(url, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const getRequest = httpTestingController.expectOne(url);
		expect(getRequest.request.method).toEqual('GET');
		expect(getRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should get with global error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.get(url).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const getRequest = httpTestingController.expectOne(url);
		expect(getRequest.request.method).toEqual('GET');
		expect(getRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should put with custom error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.put(url, {}, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const putRequest = httpTestingController.expectOne(url);
		expect(putRequest.request.method).toEqual('PUT');
		expect(putRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		putRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should put with global error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.put(url).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const putRequest = httpTestingController.expectOne(url);
		expect(putRequest.request.method).toEqual('PUT');
		expect(putRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		putRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should post with custom error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.post(url, {}, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const postRequest = httpTestingController.expectOne(url);
		expect(postRequest.request.method).toEqual('POST');
		expect(postRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		postRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should post with global error handler', () => {
		const url = 'https://bimdb.ch';
		const mockResponse = { success: true };
		service.post(url).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const postRequest = httpTestingController.expectOne(url);
		expect(postRequest.request.method).toEqual('POST');
		expect(postRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		postRequest.flush(mockResponse);

		httpTestingController.verify();
	});

});
