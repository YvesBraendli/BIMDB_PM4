import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ImageSize, ImageType } from '../core/models/tmdb-img-config';
import { TmdbImgSrcPipe } from './tmdb-img-src.pipe';

@Component({
	selector: 'app-tmdb-img',
	standalone: true,
	templateUrl: './tmdb-img.component.html',
	styleUrls: ['./tmdb-img.component.scss'],
	imports: [CommonModule, TmdbImgSrcPipe]
})
export class TmdbImgComponent {
	@Input()
	public alt?: string;
	@Input()
	public title?: string;
	@Input()
	public src?: string;
	@Input()
	public type: ImageType = ImageType.Poster;
	@Input()
	public size: ImageSize = ImageSize.Medium;
	@Input()
	public customSize?: string;

	public altImg?: string;

	public onError(): void {
		this.altImg = this.type === ImageType.Profile ? 'person' : 'movie';
	}
}
