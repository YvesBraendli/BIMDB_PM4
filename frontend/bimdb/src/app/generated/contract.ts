/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2023-05-20 18:04:08.

export interface ApiConfig {
    images: ApiImagesConfig;
    changeKeys: string[];
}

export interface ApiImagesConfig {
    baseUrl: string;
    secureBaseUrl: string;
    backdropSizes: string[];
    logoSizes: string[];
    posterSizes: string[];
    profileSizes: string[];
    stillSizes: string[];
}

export interface Cast extends Credit {
    character: string;
}

export interface CombinedCredits {
    cast: Media[];
    crew: Media[];
}

export interface Country {
    iso: string;
    englishName: string;
}

export interface Credit {
    id: number;
    name: string;
    profilePath: string;
}

export interface Credits {
    cast: Cast[];
    crew: Crew[];
}

export interface Crew extends Credit {
    job: string;
}

export interface DiscoverMovie extends PaginationResponse {
    results: Movie[];
}

export interface DiscoverTv extends PaginationResponse {
    results: TvShow[];
}

export interface Episode {
    id: number;
    airDate: Date;
    episodeNumber: number;
    name: string;
    overview: string;
    productionCode: string;
    runtime: number;
    seasonNumber: number;
    showId: number;
    stillPath: string;
    voteAverage: number;
    voteCount: number;
}

export interface Genre {
    id: number;
    name: string;
}

export interface GenreListWrapper {
    genres: Genre[];
}

export interface Language {
    iso: string;
    englishName: string;
    name: string;
}

export interface Media {
    backdropPath: string;
    genreIds: number[];
    id: number;
    originalLanguage: string;
    overview: string;
    popularity: number;
    posterPath: string;
    voteAverage: number;
    voteCount: number;
    name: string;
    originalName: string;
    releaseDate: Date;
    mediaType: MediaType;
}

export interface Movie extends Media {
    adult: boolean;
    video: boolean;
}

export interface MovieDetails extends Movie {
    budget: number;
    genres: Genre[];
    homepage: string;
    imdbId: string;
    revenue: number;
    runtime: number;
    status: string;
    tagline: string;
    credits: Credits;
    recommendations: MovieListWrapper;
    similar: MovieListWrapper;
}

export interface MovieListWrapper {
    results: Movie[];
}

export interface Network {
    id: number;
    name: string;
    originCountry: string;
    logoPath: string;
}

export interface PaginationResponse {
    page: number;
    totalPages: number;
    totalResults: number;
}

export interface People extends PaginationResponse {
    results: Credit[];
}

export interface Person {
    id: number;
    birthday: Date;
    deathday: Date;
    name: string;
    popularity: number;
    biography: string;
    placeOfBirth: string;
    profilePath: string;
    combinedCredits: CombinedCredits;
}

export interface Preferences {
    username: string;
    favoriteTvGenres: Genre[];
    favoriteMovieGenres: Genre[];
    releaseYearFrom: number;
    releaseYearTo: number;
    ratingThreshold: number;
    language: Language;
    favoriteActors: Person[];
}

export interface SearchResultWrapper extends PaginationResponse {
    results: Media[];
}

export interface Season {
    id: number;
    airDate: string;
    episodeCount: number;
    name: string;
    overview: string;
    posterPath: string;
    seasonNumber: number;
}

export interface TvShow extends Media {
    originCountry: string[];
}

export interface TvShowDetails extends TvShow {
    episodeRunTime: number[];
    genres: Genre[];
    homepage: string;
    inProduction: boolean;
    languages: string[];
    lastAirDate: string;
    networks: Network[];
    numberOfEpisodes: number;
    numberOfSeasons: number;
    seasons: Season[];
    status: string;
    tagline: string;
    type: string;
    credits: Credits;
    recommendations: TvShowListWrapper;
    similar: TvShowListWrapper;
}

export interface TvShowListWrapper {
    results: TvShow[];
}

export interface TvShowSeasonDetails {
    id: number;
    airDate: string;
    episodes: Episode[];
    name: string;
    overview: string;
    posterPath: string;
    seasonNumber: number;
}

export interface WatchProvider {
    providerName: string;
    logoPath: string;
}

export interface WatchProviders {
    buy: WatchProvider[];
    country: string;
    flatrate: WatchProvider[];
    rent: WatchProvider[];
}

export interface WatchProvidersResult {
    watchProviders: WatchProviders[];
}

export const enum MediaType {
    Movie = "movie",
    TvShow = "tv",
    Person = "person",
}
