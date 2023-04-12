import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CastListComponent } from '../cast-list/cast-list.component';
import { MovieDetail, MovieDetailService } from './movie-detail.service';
import { TranslateModule } from '@ngx-translate/core';

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
	public movie?: MovieDetail;

	public constructor(private route: ActivatedRoute, private movieDetailService: MovieDetailService) {
	}

	public ngOnInit(): void {
		this.id = +(this.route.snapshot.paramMap.get(PARAM_MOVIE_ID) ?? -1);
		if (this.id >= 0) {
			this.movieDetailService.getMovie(this.id).subscribe({
				next: result => {
					this.movie = result;
				}
			});
		}
	}
}
