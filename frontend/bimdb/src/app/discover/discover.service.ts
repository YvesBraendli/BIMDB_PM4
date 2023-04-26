import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DiscoverMovie } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class DiscoverService {

	public constructor(private httpClient: HttpClient) { }

	public discover(page: number): Observable<DiscoverMovie> {
		return this.httpClient.get<DiscoverMovie>(`${environment.apiBaseUrl}/discover/movie?page=${page}`);
	}

}

