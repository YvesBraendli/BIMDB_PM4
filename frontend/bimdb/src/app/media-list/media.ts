export interface Media {
	id: number;
	title: string;
	release_date: Date;
	poster_path: string;
	mediaType: MediaType
}

export enum MediaType {
	Movie,
	TvShow
}
