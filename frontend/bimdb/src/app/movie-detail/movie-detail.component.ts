import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { CastListComponent } from '../cast-list/cast-list.component';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { Cast, Movie, MovieDetails } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { MovieDetailService } from './movie-detail.service';
import { MatTabsModule } from '@angular/material/tabs';
import { MediaListComponent } from '../media-list/media-list.component';
import { Media, MediaType } from '../media-list/media';

export const PARAM_MOVIE_ID = 'movie-id';

@Component({
	selector: 'app-movie-detail',
	standalone: true,
	templateUrl: './movie-detail.component.html',
	styleUrls: ['./movie-detail.component.scss'],
	providers: [DatePipe],
	imports: [CommonModule, CastListComponent, TranslateModule, LocaleDatePipe, TmdbImgComponent, MatTabsModule, MediaListComponent]
})
export class MovieDetailComponent implements OnInit {
	public id?: number;
	public movie?: MovieDetails;
	public genreNames?: string;
	public cast?: Cast[];
	public recommendations?: Media[];
	public similarMovies?: Media[];

	public constructor(private route: ActivatedRoute, private movieDetailService: MovieDetailService) {
	}

	public ngOnInit(): void {
		this.route.params.subscribe(params => {
			this.id = params[PARAM_MOVIE_ID];
			if (this.id && this.id >= 0) {
				this.movieDetailService.getMovie(this.id).subscribe({
					next: result => {
						this.movie = result;
						this.genreNames = this.movie.genres.map(genre => genre.name).join(', ');
						this.cast = this.movie.credits.cast;
						this.recommendations = this.movie.recommendations.results.map(recommendation => this.convertToMedia(recommendation));
						this.similarMovies = this.movie.similar.results.map(similar => this.convertToMedia(similar));
					}
				});
			}
		});
	}

	private convertToMedia(movie: Movie): Media {
		return ({
			id: movie.id,
			title: movie.title,
			release_date: movie.release_date,
			poster_path: movie.poster_path,
			mediaType: MediaType.Movie
		}) as Media;
	}
}
