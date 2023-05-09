import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { CreditListComponent } from '../credit-list/credit-list.component';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { Cast, Credit, Crew, Movie, MovieDetails, WatchProvidersResult } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { MovieDetailService } from './movie-detail.service';
import { MatTabsModule } from '@angular/material/tabs';
import { MediaListComponent } from '../media-list/media-list.component';
import { Media, MediaType } from '../media-list/media';
import { WatchProviderListComponent } from '../watch-provider-list/watch-provider-list.component';
import { LanguageIsoPipe } from '../core/pipes/language-iso.pipe';

export const PARAM_MOVIE_ID = 'movie-id';

@Component({
	selector: 'app-movie-detail',
	standalone: true,
	templateUrl: './movie-detail.component.html',
	styleUrls: ['./movie-detail.component.scss'],
	providers: [DatePipe],
	imports: [CommonModule, CreditListComponent, TranslateModule, LocaleDatePipe, TmdbImgComponent, MatTabsModule, MediaListComponent, WatchProviderListComponent, LanguageIsoPipe]
})
export class MovieDetailComponent implements OnInit {
	public id?: number;
	public movie?: MovieDetails;
	public genreNames?: string;
	public cast?: Cast[];
	public crew?: Crew[];
	public recommendations?: Media[];
	public similarMovies?: Media[];
	public watchProvidersResult!: WatchProvidersResult;

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
						this.crew = this.movie.credits.crew;
						this.recommendations = this.movie.recommendations.results.map(recommendation => this.convertToMedia(recommendation));
						this.similarMovies = this.movie.similar.results.map(similar => this.convertToMedia(similar));
					}
				});
				this.movieDetailService.getWatchProviders(this.id).subscribe(result => {
					this.watchProvidersResult = result;
				});
			}
		});
	}

	public readonly descFnCast = (credit: Credit): string => (credit as Cast).character;

	public readonly descFnCrew = (credit: Credit): string => (credit as Crew).job;

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
