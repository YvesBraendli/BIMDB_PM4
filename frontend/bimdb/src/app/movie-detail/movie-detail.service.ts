import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Cast } from '../cast-list/cast-list.component';

@Injectable({
	providedIn: 'root'
})
export class MovieDetailService {

	public getMovie(id: number): Observable<MovieDetail> {
		return of({
			id,
			adult: false,
			backdropPath: 'https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/35slSk2fwFGDK8pZnPprOFKWxhV.jpg',
			budget: 125_000_000.00,
			genres: ['Action', 'Comedy', 'Fantasy'],
			originalLanguage: 'EN',
			originalTitle: 'Shazam! Fury of the Gods',
			overview: 'Billy Batson and his foster siblings, who transform into superheroes by saying "Shazam!", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world.',
			popularity: 1,
			posterPath: 'https://www.themoviedb.org/t/p/w300_and_h450_bestv2/3GrRgt6CiLIUXUtoktcv1g2iwT5.jpg',
			releaseDate: new Date(2023, 2, 17),
			revenue: 102_000_000.00,
			runtime: 130,
			status: 'Released',
			tagline: 'Oh. My. Gods.',
			title: 'Shazam! Fury of the Gods',
			voteAverage: 68,
			voteCount: 293,
			cast: [
				{
					id: 69899,
					name: 'Zachary Levi',
					role: 'Shazam',
					imagePath: 'https://www.themoviedb.org/t/p/w138_and_h175_face/1W8L3kEMMPF9umT3ZGaNIiCYKfZ.jpg'
				},
				{
					id: 1768966,
					name: 'Asher Angel',
					role: 'Billy Batson',
					imagePath: 'https://www.themoviedb.org/t/p/w138_and_h175_face/lgBt67iggDs0d8QBSyjdk2ytHtK.jpg'
				}
			]
		});
	}
}

export interface MovieDetail {
	id?: number;
	imdbId?: number;
	adult?: boolean;
	backdropPath?: string;
	// belongsToCollection;
	budget?: number;
	genres?: string[];
	homepage?: string;
	originalLanguage?: string;
	originalTitle?: string;
	overview?: string;
	popularity?: number;
	posterPath?: string;
	// productionCompanies
	// productionCountries
	releaseDate?: Date;
	revenue?: number;
	runtime?: number;
	// spokenLanguages
	status?: string;
	tagline?: string;
	title?: string;
	video?: boolean;
	voteAverage?: number;
	voteCount?: number;
	cast?: Cast[];
}
