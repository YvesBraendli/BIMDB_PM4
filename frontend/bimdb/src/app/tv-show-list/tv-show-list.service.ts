import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { DiscoverTv } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class TvShowListService {

	public constructor(private httpService: HttpService) {
	}

	public discover(page: number): Observable<DiscoverTv> {
		return this.httpService.get<DiscoverTv>(`/discover/tv?page=${page}`);
	}
}
