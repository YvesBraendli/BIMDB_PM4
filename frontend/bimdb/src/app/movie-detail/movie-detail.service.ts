import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MovieDetails } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class MovieDetailService {

	public constructor(private httpClient: HttpClient) { }

	public getMovie(id: number): Observable<MovieDetails> {
		return this.httpClient.get<MovieDetails>(`${environment.apiBaseUrl}/movie/${id}`);
	}
}

