import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ConfigService {

	// public constructor(private httpClient: HttpClient) { }

	public getImageBaseUrl(): Observable<string> {
		return of('https://www.themoviedb.org/t/p/w600_and_h900_bestv2/');
	}
}

export interface Country {
	iso_3166_1: string;
	english_name: string;
}
