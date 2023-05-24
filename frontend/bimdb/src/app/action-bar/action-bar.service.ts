import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpService } from '../core/services/http.service';
import { Favorite, FavoriteResponse, MediaType } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class ActionBarService {

	public constructor(private httpService: HttpService) {
	}

	public checkFavoriteStatus(id: number, type: MediaType): Observable<FavoriteResponse> {
		return this.httpService.get<FavoriteResponse>(`/favorites?id=${id}&mediaType=${type}`);
	}

	public addFavorite(id: number, type: MediaType): Observable<FavoriteResponse> {
		const payload: Favorite = {
			id: id,
			mediaType: type
		};
		return this.httpService.post('/favorites', payload);
	}

	public removeFavorite(id: number, type: MediaType): Observable<FavoriteResponse> {
		return this.httpService.delete(`/favorites?id=${id}&mediaType=${type}`);
	}
}
