import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpService } from '../core/services/http.service';
import { MovieDetails, WatchProvidersResult } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class MovieDetailService {

	public constructor(private httpService: HttpService) {
	}

	public getMovie(id: number): Observable<MovieDetails> {
		return this.httpService.get<MovieDetails>(`${environment.apiBaseUrl}/movie/${id}`);
	}

	public getWatchProviders(id: number): Observable<WatchProvidersResult> {
		return this.httpService.get<WatchProvidersResult>(`${environment.apiBaseUrl}/movie/${id}/watch-providers`);
	}
}

