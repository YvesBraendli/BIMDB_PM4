import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { CastListComponent } from '../cast-list/cast-list.component';
import { ConfigService } from '../core/services/config.service';
import { MovieDetails } from '../generated/contract';
import { MovieDetailService } from './movie-detail.service';

export const PARAM_MOVIE_ID = 'movie-id';

@Component({
	selector: 'app-movie-detail',
	standalone: true,
	imports: [CommonModule, CastListComponent, TranslateModule],
	templateUrl: './movie-detail.component.html',
	styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {
	public id?: number;
	public movie?: MovieDetails;
	public genreNames?: string;
	public posterBaseUrl?: string;

	public constructor(private route: ActivatedRoute, private movieDetailService: MovieDetailService, private configService: ConfigService) {
		this.configService.getImageBaseUrl().subscribe(baseUrl => this.posterBaseUrl = baseUrl);
	}

	public ngOnInit(): void {
		this.id = +(this.route.snapshot.paramMap.get(PARAM_MOVIE_ID) ?? -1);
		if (this.id >= 0) {
			this.movieDetailService.getMovie(this.id).subscribe({
				next: result => {
					this.movie = result;
					this.genreNames = this.movie.genres.map(genre => genre.name).join(', ');
				}
			});
		}
	}
}
