import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpService } from '../core/services/http.service';
import { DiscoverTv } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class TvShowListService {

	public constructor(private httpService: HttpService) {
	}

	public discover(page: number): Observable<DiscoverTv> {
		return this.httpService.get<DiscoverTv>(`${environment.apiBaseUrl}/discover/tv?page=${page}`);
	}
}
