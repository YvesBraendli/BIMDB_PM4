import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, Params } from '@angular/router';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of, Subject } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { TvShowDetailComponent } from './tv-show-detail.component';
import { TvShowDetailService } from './tv-show-detail.service';
import { Cast, Crew } from '../generated/contract';

describe('TvDetailComponent', () => {
	let params: Subject<Params>;
	let component: TvShowDetailComponent;
	let fixture: ComponentFixture<TvShowDetailComponent>;
	let mockTvShowDetailService: TvShowDetailService;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		params = new Subject<Params>();

		await TestBed.configureTestingModule({
			imports: [
				TvShowDetailComponent,
				HttpClientTestingModule,
				TranslateTestingModule.withTranslations({}),
				BrowserAnimationsModule
			],
			providers: [
				{
					provide: ActivatedRoute,
					useValue: {
						params
					}
				}
			]
		}).compileComponents();

		mockTvShowDetailService = TestBed.inject(TvShowDetailService);
		mockTvShowDetailService.getTvShow = jasmine.createSpy().and.returnValue(of({
			credits: {
				cast: [],
				crew: []
			},
			recommendations: {
				results: [{
					id: 0,
					name: 'Recommendation'
				}]
			},
			similar: {
				results: [
					{
						id: 1,
						name: 'Similar'
					}
				]
			}
		}));
		mockTvShowDetailService.getTvSeason = jasmine.createSpy().and.returnValue(of({ episodes: [] }));
		mockTvShowDetailService.getWatchProviders = jasmine.createSpy().and.returnValue(of({
			results: {}
		}));

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

		fixture = TestBed.createComponent(TvShowDetailComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	describe('when a tv show id is passed', () => {
		const tvShowId = 100;
		const seasonNumber = 1;

		beforeEach((done) => {
			params.next({ 'tv-show-id': tvShowId });
			done();
		});

		it(`should call getTvShow with ${tvShowId}`, () => {
			expect(mockTvShowDetailService.getTvShow).toHaveBeenCalledOnceWith(tvShowId);
		});

		it(`should call getWatchProviders with ${tvShowId}`, () => {
			expect(mockTvShowDetailService.getWatchProviders).toHaveBeenCalledOnceWith(tvShowId);
		});

		it('should not call getTvSeason', () => {
			expect(mockTvShowDetailService.getTvSeason).not.toHaveBeenCalled();
		});

		it('should not return any season episodes', () => {
			expect(component.getEpisodes(seasonNumber)).toBeUndefined();
		});

		describe('and selectSeason is called', () => {

			beforeEach((done) => {
				component.selectSeason(seasonNumber);
				done();
			});

			it(`should call getTvSeason with ${tvShowId} and ${seasonNumber}`, () => {
				expect(mockTvShowDetailService.getTvSeason).toHaveBeenCalledOnceWith(tvShowId, seasonNumber);
			});

			it('should return episodes for the season', () => {
				expect(component.getEpisodes(seasonNumber)).toBeDefined();
			});
		});
	});

	describe('when no tv show id is passed', () => {

		it('should not call getTvShow', () => {
			expect(mockTvShowDetailService.getTvShow).not.toHaveBeenCalled();
		});

		it('should not call getWatchProviders', () => {
			expect(mockTvShowDetailService.getWatchProviders).not.toHaveBeenCalled();
		});

		it('should not call getTvSeason', () => {
			expect(mockTvShowDetailService.getTvSeason).not.toHaveBeenCalled();
		});
	});

	it('should return character when descFnCast is called', () => {
		const cast = {
			character: 'Max'
		} as Cast;
		expect(component.descFnCast(cast)).toEqual(cast.character);
	});

	it('should return job when descFnCrew is called', () => {
		const crew = {
			job: 'Set Designer'
		} as Crew;
		expect(component.descFnCrew(crew)).toEqual(crew.job);
	});
});
