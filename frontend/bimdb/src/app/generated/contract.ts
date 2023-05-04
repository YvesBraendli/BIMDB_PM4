/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2023-05-04 16:35:37.

export interface Discover {
    page: number;
    total_pages: number;
    total_results: number;
}

export interface DiscoverMovie extends Discover {
	results: Movie[];
}

export interface DiscoverTv extends Discover {
	results: TvShow[];
}

export interface Episode {
	id: number;
	air_date: Date;
	episode_number: number;
	name: string;
	overview: string;
	production_code: string;
	runtime: number;
	season_number: number;
	show_id: number;
	still_path: string;
	vote_average: number;
	vote_count: number;
}

export interface Genre {
	id: number;
	name: string;
}

export interface Media {
	backdrop_path: string;
	genre_ids: number[];
	id: number;
    original_language: string;
    overview: string;
    popularity: number;
    poster_path: string;
    vote_average: number;
    vote_count: number;
}

export interface Movie extends Media {
    adult: boolean;
    original_title: string;
    release_date: Date;
    title: string;
    video: boolean;
}

export interface MovieDetails extends Movie {
    budget: number;
    genres: Genre[];
    homepage: string;
    imdb_id: string;
    revenue: number;
    runtime: number;
    status: string;
    tagline: string;
}

export interface Network {
    id: number;
    name: string;
    origin_country: string;
    logo_path: string;
}

export interface Season {
    id: number;
    air_date: string;
    episode_count: number;
    name: string;
    overview: string;
    poster_path: string;
    season_number: number;
}

export interface TvShow extends Media {
    first_air_date: string;
    origin_country: string[];
    name: string;
    original_name: string;
}

export interface TvShowDetails extends TvShow {
    episode_run_time: number[];
    genres: Genre[];
    homepage: string;
    in_production: boolean;
    languages: string[];
	last_air_date: string;
	networks: Network[];
	number_of_episodes: number;
	number_of_seasons: number;
	seasons: Season[];
	status: string;
	tagline: string;
	type: string;
}

export interface TvShowSeasonDetails {
	id: number;
	air_date: string;
	episodes: Episode[];
	name: string;
	overview: string;
	poster_path: string;
	season_number: number;
}
