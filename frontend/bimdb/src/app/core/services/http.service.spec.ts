import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpHeaderConstants } from '../constants/http-headers';
import { Environment } from '../models/environment';
import { EnvironmentService } from './environment.service';
import { HttpService } from './http.service';

describe('HttpService', () => {
	let service: HttpService;
	let httpTestingController: HttpTestingController;
	const requestUrl = '/test';
	const calledUrl = `/api${requestUrl}`;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		spyOn(TestBed.inject(EnvironmentService), 'getConfig').and.returnValue({ apiBaseUrl: '/api' } as Environment);
		service = TestBed.inject(HttpService);
		httpTestingController = TestBed.inject(HttpTestingController);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should get with custom error handler', () => {
		const mockResponse = { success: true };
		service.get(requestUrl, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const getRequest = httpTestingController.expectOne(calledUrl);
		expect(getRequest.request.method).toEqual('GET');
		expect(getRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should get with global error handler', () => {
		const mockResponse = { success: true };
		service.get(requestUrl).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const getRequest = httpTestingController.expectOne(calledUrl);
		expect(getRequest.request.method).toEqual('GET');
		expect(getRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		getRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should put with custom error handler', () => {
		const mockResponse = { success: true };
		service.put(requestUrl, {}, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const putRequest = httpTestingController.expectOne(calledUrl);
		expect(putRequest.request.method).toEqual('PUT');
		expect(putRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		putRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should put with global error handler', () => {
		const mockResponse = { success: true };
		service.put(requestUrl).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const putRequest = httpTestingController.expectOne(calledUrl);
		expect(putRequest.request.method).toEqual('PUT');
		expect(putRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		putRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should post with custom error handler', () => {
		const mockResponse = { success: true };
		service.post(requestUrl, {}, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const postRequest = httpTestingController.expectOne(calledUrl);
		expect(postRequest.request.method).toEqual('POST');
		expect(postRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		postRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should post with global error handler', () => {
		const mockResponse = { success: true };
		service.post(requestUrl).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const postRequest = httpTestingController.expectOne(calledUrl);
		expect(postRequest.request.method).toEqual('POST');
		expect(postRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		postRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should delete with custom error handler', () => {
		const mockResponse = { success: true };
		service.delete(requestUrl, true).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const deleteRequest = httpTestingController.expectOne(calledUrl);
		expect(deleteRequest.request.method).toEqual('DELETE');
		expect(deleteRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('true');
		deleteRequest.flush(mockResponse);

		httpTestingController.verify();
	});

	it('should delete with global error handler', () => {
		const mockResponse = { success: true };
		service.delete(requestUrl).subscribe(res => {
			expect(res).toEqual({ success: true });
		});
		const deleteRequest = httpTestingController.expectOne(calledUrl);
		expect(deleteRequest.request.method).toEqual('DELETE');
		expect(deleteRequest.request.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER)).toBe('false');
		deleteRequest.flush(mockResponse);

		httpTestingController.verify();
	});

});
