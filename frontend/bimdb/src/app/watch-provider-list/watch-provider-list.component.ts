import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WatchProvidersResult } from '../generated/contract';
import { MatTabsModule } from '@angular/material/tabs';
import { CountryIsoPipe } from '../core/pipes/country-iso.pipe';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { ImageType } from '../core/models/tmdb-img-config';
import { MatExpansionModule } from '@angular/material/expansion';
import { TranslateModule } from '@ngx-translate/core';

@Component({
	selector: 'app-watch-provider-list',
	standalone: true,
	imports: [CommonModule, MatTabsModule, CountryIsoPipe, TmdbImgComponent, MatExpansionModule, TranslateModule],
	templateUrl: './watch-provider-list.component.html',
	styleUrls: ['./watch-provider-list.component.scss']
})
export class WatchProviderListComponent {

	private _watchProvidersResult?: WatchProvidersResult;

	public get watchProvidersResult(): WatchProvidersResult | undefined {
		return this._watchProvidersResult;
	}

	@Input()
	public set watchProvidersResult(value: WatchProvidersResult | undefined) {
		this._watchProvidersResult = value;
		if (this._watchProvidersResult) {
			this._watchProvidersResult.watchProviders = this._watchProvidersResult.watchProviders.filter(watchProviders =>
				watchProviders.flatrate && watchProviders.flatrate.length > 0 ||
				watchProviders.rent && watchProviders.rent.length > 0 ||
				watchProviders.buy && watchProviders.buy.length > 0
			);
		}
	}

	protected readonly ImageType = ImageType;
}
