import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { ApiConfig, Country } from 'src/app/generated/contract';
import { ImgSizeConfig, TmdbImgConfig } from '../models/tmdb-img-config';
import { HttpService } from './http.service';

@Injectable({
	providedIn: 'root'
})
export class ConfigService {
	private imgConfig?: TmdbImgConfig;
	private countries: Country[] = [];

	public constructor(private httpService: HttpService) {
	}

	public loadAppConfig(hasCustomErrorHandler = false): Observable<void> {
		return new Observable(subscriber => {
			forkJoin([this.loadApiConfig(hasCustomErrorHandler), this.loadCountries(hasCustomErrorHandler)]).subscribe({
				next: () => {
					subscriber.next();
					subscriber.complete();
				},
				error: err => subscriber.error(err)
			});
		});
	}

	public getCountries(): Country[] {
		if (this.countries.length === 0) {
			this.loadCountries().subscribe();
		}
		return this.countries;
	}

	public getTmbdImgConfig(): TmdbImgConfig {
		if (!this.imgConfig) {
			this.loadApiConfig().subscribe();
		}
		return this.imgConfig ?? {} as TmdbImgConfig;
	}

	public getImageBaseUrl(): string {
		return this.imgConfig?.baseUrl ?? '';
	}

	private loadApiConfig(hasCustomErrorHandler = false): Observable<void> {
		return new Observable(subscriber => {
			this.httpService.get<ApiConfig>('/api/config', hasCustomErrorHandler).subscribe({
				next: res => {
					this.imgConfig = {} as TmdbImgConfig;
					this.imgConfig.baseUrl = res.images.secure_base_url;
					this.imgConfig.backdropSizes = this.createImgSizeConfig(res.images.backdrop_sizes);
					this.imgConfig.logoSizes = this.createImgSizeConfig(res.images.logo_sizes);
					this.imgConfig.posterSizes = this.createImgSizeConfig(res.images.poster_sizes);
					this.imgConfig.profileSizes = this.createImgSizeConfig(res.images.profile_sizes);
					this.imgConfig.stillSizes = this.createImgSizeConfig(res.images.still_sizes);
					subscriber.next();
					subscriber.complete();
				},
				error: err => subscriber.error(err)
			});
		});
	}

	private loadCountries(hasCustomErrorHandler = false): Observable<void> {
		return new Observable(subscriber => {
			this.httpService.get<Country[]>('/api/config/countries', hasCustomErrorHandler).subscribe({
				next: res => {
					this.countries = res;
					subscriber.next();
					subscriber.complete();
				},
				error: err => subscriber.error(err)
			});
		});
	}

	private createImgSizeConfig(sizes: string[]): ImgSizeConfig {
		const sizesWithoutOriginal = sizes.filter(s => s !== 'original');
		const length = sizesWithoutOriginal.length;
		if (length === 0) {
			return new ImgSizeConfig();
		}
		const small = sizesWithoutOriginal[0] as string;
		const mediumIndex = length === 3 ? 1 : Math.ceil(length / 2);
		const medium = sizesWithoutOriginal[mediumIndex] as string;
		const large = sizesWithoutOriginal[length - 1] as string;
		return new ImgSizeConfig({ small, medium, large } as ImgSizeConfig);
	}

}
