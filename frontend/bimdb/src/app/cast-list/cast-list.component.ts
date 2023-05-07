import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Cast } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { ImageSize, ImageType } from '../core/models/tmdb-img-config';

@Component({
	selector: 'app-cast-list',
	standalone: true,
	imports: [CommonModule, TmdbImgComponent],
	templateUrl: './cast-list.component.html',
	styleUrls: ['./cast-list.component.scss']
})
export class CastListComponent {

	@Input()
	public cast?: Cast[];

	protected readonly ImageSize = ImageSize;
	protected readonly ImageType = ImageType;
}
