import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { LanguageIsoPipe } from '../core/pipes/language-iso.pipe';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { CreditListComponent } from '../credit-list/credit-list.component';
import { Cast, Credit, Crew, Media, MovieDetails, WatchProvidersResult } from '../generated/contract';
import { MediaListComponent } from '../media-list/media-list.component';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { WatchProviderListComponent } from '../watch-provider-list/watch-provider-list.component';
import { MovieDetailService } from './movie-detail.service';
import { ActionBarComponent } from '../action-bar/action-bar.component';

export const PARAM_MOVIE_ID = 'movie-id';

@Component({
	selector: 'app-movie-detail',
	standalone: true,
	templateUrl: './movie-detail.component.html',
	styleUrls: ['./movie-detail.component.scss'],
	providers: [DatePipe],
	imports: [CommonModule, CreditListComponent, TranslateModule, LocaleDatePipe, TmdbImgComponent, MatTabsModule, MediaListComponent, WatchProviderListComponent, LanguageIsoPipe, ActionBarComponent]
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
						this.recommendations = this.movie.recommendations.results;
						this.similarMovies = this.movie.similar.results;
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

}
