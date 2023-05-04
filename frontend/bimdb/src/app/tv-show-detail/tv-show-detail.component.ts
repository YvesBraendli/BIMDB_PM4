import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TvShowDetailService } from './tv-show-detail.service';
import { CastListComponent } from '../cast-list/cast-list.component';
import { TranslateModule } from '@ngx-translate/core';
import { ActivatedRoute } from '@angular/router';
import { ConfigService } from '../core/services/config.service';
import { MatTabsModule } from '@angular/material/tabs';
import { Episode, TvShowDetails } from '../generated/contract';


export const PARAM_TV_SHOW_ID = 'tv-show-id';

@Component({
	selector: 'app-tv-detail',
	standalone: true,
	imports: [CommonModule, CastListComponent, TranslateModule, MatTabsModule],
	templateUrl: './tv-show-detail.component.html',
	styleUrls: ['./tv-show-detail.component.scss']
})
export class TvShowDetailComponent implements OnInit {
	public id?: number;
	public seasonNumber?: number;
	public posterBaseUrl?: string;

	public tvShow?: TvShowDetails;
	public episodesMap: Map<number, Episode[]> = new Map();

	public constructor(private route: ActivatedRoute, private tvShowDetailService: TvShowDetailService, private configService: ConfigService) {
		this.configService.getImageBaseUrl().subscribe(baseUrl => this.posterBaseUrl = baseUrl);
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
