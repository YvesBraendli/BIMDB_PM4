import { Pipe, PipeTransform } from '@angular/core';
import { ImageSize, ImageType, ImgSizeConfig, TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';

@Pipe({
	name: 'tmdbImgSrc',
	standalone: true
})
export class TmdbImgSrcPipe implements PipeTransform {
	private imgConfig: TmdbImgConfig;

	public constructor(private configService: ConfigService) {
		this.imgConfig = this.configService.getTmbdImgConfig();
	}

	public transform(src: string | undefined, type: ImageType, size: ImageSize, custom?: string): string {
		if (custom && size !== ImageSize.Custom) {
			size = ImageSize.Custom;
		}
		const sizeUrlSegment = this.getSizeUrlSegment(type, size, custom);
		return `${this.imgConfig.baseUrl}${sizeUrlSegment}${src}`;
	}

	private getSizeUrlSegment(type: ImageType, size: ImageSize, custom?: string): string {
		const config = this.getSizeConfig(type);
		switch (size) {
			case ImageSize.Small:
				return config.small;
			case ImageSize.Medium:
				return config.medium;
			case ImageSize.Large:
				return config.large;
			case ImageSize.Original:
				return config.originial;
			case ImageSize.Custom:
				return custom ?? config.originial;
		}
	}

	private getSizeConfig(type: ImageType): ImgSizeConfig {
		switch (type) {
			case ImageType.Backdrop:
				return this.imgConfig.backdropSizes;
			case ImageType.Logo:
				return this.imgConfig.logoSizes;
			case ImageType.Poster:
				return this.imgConfig.posterSizes;
			case ImageType.Profile:
				return this.imgConfig.profileSizes;
			case ImageType.Still:
				return this.imgConfig.stillSizes;
		}
	}
}
