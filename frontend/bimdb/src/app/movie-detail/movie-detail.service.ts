import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { MovieDetails, WatchProvidersResult } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class MovieDetailService {

	public constructor(private httpService: HttpService) {
	}

	public getMovie(id: number): Observable<MovieDetails> {
		return this.httpService.get<MovieDetails>(`/movie/${id}`);
	}

	public getWatchProviders(id: number): Observable<WatchProvidersResult> {
		return this.httpService.get<WatchProvidersResult>(`/movie/${id}/watch-providers`);
	}
}

