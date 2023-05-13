import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { DiscoverMovie } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class DiscoverService {

	public constructor(private httpService: HttpService) { }

	public discover(page: number, hasCustomErrorHandler = false): Observable<DiscoverMovie> {
		return this.httpService.get<DiscoverMovie>(`/discover/movies?page=${page}`, hasCustomErrorHandler);
	}

}

