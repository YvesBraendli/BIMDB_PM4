import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { TvShowDetails, TvShowSeasonDetails, WatchProvidersResult } from '../generated/contract';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class TvShowDetailService {

	public constructor(private httpService: HttpService) {
	}

	public getTvShow(id: number): Observable<TvShowDetails> {
		return this.httpService.get<TvShowDetails>(`${environment.apiBaseUrl}/tv/${id}`);
	}

	public getTvSeason(id: number, season: number): Observable<TvShowSeasonDetails> {
		return this.httpService.get(`${environment.apiBaseUrl}/tv/${id}/${season}`);
	}

	public getWatchProviders(id: number): Observable<WatchProvidersResult> {
		return this.httpService.get<WatchProvidersResult>(`${environment.apiBaseUrl}/tv/${id}/watch-providers`);
	}
}
