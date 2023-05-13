import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTabsModule } from '@angular/material/tabs';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { ImageSize } from '../core/models/tmdb-img-config';
import { LanguageIsoPipe } from '../core/pipes/language-iso.pipe';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { CreditListComponent } from '../credit-list/credit-list.component';
import { Cast, Credit, Crew, Episode, Media, TvShowDetails, WatchProvidersResult } from '../generated/contract';
import { MediaListComponent } from '../media-list/media-list.component';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { WatchProviderListComponent } from '../watch-provider-list/watch-provider-list.component';
import { TvShowDetailService } from './tv-show-detail.service';

export const PARAM_TV_SHOW_ID = 'tv-show-id';

@Component({
	selector: 'app-tv-detail',
	standalone: true,
	templateUrl: './tv-show-detail.component.html',
	styleUrls: ['./tv-show-detail.component.scss'],
	providers: [DatePipe],
	imports: [CommonModule, CreditListComponent, TranslateModule, MatTabsModule, LocaleDatePipe, TmdbImgComponent, WatchProviderListComponent, MediaListComponent, LanguageIsoPipe, MatExpansionModule]
})
export class TvShowDetailComponent implements OnInit {
	public id?: number;
	public tvShow?: TvShowDetails;
	public recommendations?: Media[];
	public similarTvShows?: Media[];
	public watchProvidersResult!: WatchProvidersResult;
	public episodesMap: Map<number, Episode[]> = new Map();

	public ImageSize = ImageSize;

	public constructor(private route: ActivatedRoute, private tvShowDetailService: TvShowDetailService) {
	}

	public ngOnInit(): void {
		this.route.params.subscribe(params => {
			this.id = params[PARAM_TV_SHOW_ID];
			if (this.id) {
				this.loadTvShow(this.id);
				this.loadWatchProviders(this.id);
			}
		});
	}

	public selectSeason(seasonNumber: number): void {
		if (this.id && this.id >= 0 && seasonNumber && !this.episodesMap.has(seasonNumber)) {
			this.tvShowDetailService
				.getTvSeason(this.id, seasonNumber)
				.subscribe((result) => this.episodesMap.set(seasonNumber, result.episodes));
		}
	}

	public getEpisodes(seasonNumber: number): Episode[] | undefined {
		return this.episodesMap.get(seasonNumber);
	}

	public readonly descFnCast = (credit: Credit): string => (credit as Cast).character;

	public readonly descFnCrew = (credit: Credit): string => (credit as Crew).job;


	private loadWatchProviders(id: number): void {
		this.tvShowDetailService.getWatchProviders(id).subscribe(
			(result) => this.watchProvidersResult = result);
	}

	private loadTvShow(id: number): void {
		this.tvShowDetailService
			.getTvShow(id)
			.subscribe((result) => {
				this.tvShow = result;
				this.recommendations = result.recommendations.results;
				this.similarTvShows = result.similar.results;
			});
	}
}
