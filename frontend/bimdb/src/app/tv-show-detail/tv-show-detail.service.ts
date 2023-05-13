import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { TvShowDetails, TvShowSeasonDetails, WatchProvidersResult } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class TvShowDetailService {

	public constructor(private httpService: HttpService) {
	}

	public getTvShow(id: number): Observable<TvShowDetails> {
		return this.httpService.get<TvShowDetails>(`/tv/${id}`);
	}

	public getTvSeason(id: number, season: number): Observable<TvShowSeasonDetails> {
		return this.httpService.get(`/tv/${id}/${season}`);
	}

	public getWatchProviders(id: number): Observable<WatchProvidersResult> {
		return this.httpService.get<WatchProvidersResult>(`/tv/${id}/watch-providers`);
	}
}
