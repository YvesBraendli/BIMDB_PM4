import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { CastListComponent } from '../cast-list/cast-list.component';
import { ImageSize } from '../core/models/tmdb-img-config';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { Episode, TvShowDetails } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { TvShowDetailService } from './tv-show-detail.service';


export const PARAM_TV_SHOW_ID = 'tv-show-id';

@Component({
	selector: 'app-tv-detail',
	standalone: true,
	templateUrl: './tv-show-detail.component.html',
	styleUrls: ['./tv-show-detail.component.scss'],
	providers: [DatePipe],
	imports: [CommonModule, CastListComponent, TranslateModule, MatTabsModule, LocaleDatePipe, TmdbImgComponent]
})
export class TvShowDetailComponent implements OnInit {
	public id?: number;
	public seasonNumber?: number;

	public tvShow?: TvShowDetails;
	public episodesMap: Map<number, Episode[]> = new Map();
	public ImageSize = ImageSize;

	public constructor(private route: ActivatedRoute, private tvShowDetailService: TvShowDetailService) {
	}

	public ngOnInit(): void {
		this.id = +(this.route.snapshot.paramMap.get(PARAM_TV_SHOW_ID) ?? -1);
		if (this.id >= 0) {
			this.tvShowDetailService
				.getTvShow(this.id)
				.subscribe((result) => this.tvShow = result);
		}
	}

	public selectSeason(seasonNumber: number): void {
		this.seasonNumber = seasonNumber;
		if (this.id && this.id >= 0 && this.seasonNumber) {
			this.tvShowDetailService
				.getTvSeason(this.id, this.seasonNumber)
				.subscribe((result) => this.episodesMap.set(seasonNumber, result.episodes));
		}
	}

	public getEpisodes(seasonNumber: number): Episode[] | undefined {
		return this.episodesMap.get(seasonNumber);
	}
}
