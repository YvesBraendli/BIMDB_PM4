import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Credit } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { ImageSize, ImageType } from '../core/models/tmdb-img-config';
import { RouterLink } from '@angular/router';

@Component({
	selector: 'app-credit-list',
	standalone: true,
	imports: [CommonModule, TmdbImgComponent, RouterLink],
	templateUrl: './credit-list.component.html',
	styleUrls: ['./credit-list.component.scss']
})
export class CreditListComponent {

	@Input()
	public credits?: Credit[];
	@Input()
	public descFn?: (credit: Credit) => string;

	protected readonly ImageSize = ImageSize;
	protected readonly ImageType = ImageType;
}
