export class TmdbImgConfig {
	public baseUrl: string;
	public backdropSizes: ImgSizeConfig;
	public logoSizes: ImgSizeConfig;
	public posterSizes: ImgSizeConfig;
	public profileSizes: ImgSizeConfig;
	public stillSizes: ImgSizeConfig;

	public constructor(config?: TmdbImgConfig) {
		this.baseUrl = config?.baseUrl ?? '';
		this.backdropSizes = config?.backdropSizes ?? new ImgSizeConfig();
		this.logoSizes = config?.logoSizes ?? new ImgSizeConfig();
		this.posterSizes = config?.posterSizes ?? new ImgSizeConfig();
		this.profileSizes = config?.profileSizes ?? new ImgSizeConfig();
		this.stillSizes = config?.stillSizes ?? new ImgSizeConfig();
	}
}

export class ImgSizeConfig {
	public small: string;
	public medium: string;
	public large: string;
	public readonly originial = 'original';

	public constructor(imgSizeConfig?: ImgSizeConfig) {
		this.small = imgSizeConfig?.small ?? this.originial;
		this.medium = imgSizeConfig?.medium ?? this.originial;
		this.large = imgSizeConfig?.large ?? this.originial;
	}

}

export enum ImageSize {
	Small,
	Medium,
	Large,
	Original,
	Custom
}

export enum ImageType {
	Backdrop = 'Backdrop',
	Logo = 'Logo',
	Poster = 'Poster',
	Profile = 'Profile',
	Still = 'Still'
}
