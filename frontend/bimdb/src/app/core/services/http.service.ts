import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaderConstants } from '../constants/http-headers';

@Injectable({
	providedIn: 'root'
})
export class HttpService {
	public constructor(private httpClient: HttpClient) {
	}

	public get<T>(url: string, hasCustomErrorHandler = false): Observable<T> {
		const headers = new HttpHeaders().set(HttpHeaderConstants.CUSTOM_ERROR_HANDLER, String(hasCustomErrorHandler));
		return this.httpClient.get<T>(url, { headers });
	}

	public put<T, R>(url: string, body?: T, hasCustomErrorHandler = false): Observable<R> {
		const headers = new HttpHeaders().set(HttpHeaderConstants.CUSTOM_ERROR_HANDLER, String(hasCustomErrorHandler));
		return this.httpClient.put<R>(url, body ?? {}, { headers });
	}

	public post<T, R>(url: string, body?: T, hasCustomErrorHandler = false): Observable<R> {
		const headers = new HttpHeaders().set(HttpHeaderConstants.CUSTOM_ERROR_HANDLER, String(hasCustomErrorHandler));
		return this.httpClient.post<R>(url, body ?? {}, { headers });
	}
}
