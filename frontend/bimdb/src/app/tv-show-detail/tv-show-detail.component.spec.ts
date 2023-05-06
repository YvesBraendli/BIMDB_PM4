import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { MovieDetailComponent } from '../movie-detail/movie-detail.component';
import { TvShowDetailComponent } from './tv-show-detail.component';
import { TvShowDetailService } from './tv-show-detail.service';

describe('TvDetailComponent', () => {
	let component: TvShowDetailComponent;
	let fixture: ComponentFixture<TvShowDetailComponent>;
	let mockTvShowDetailService: TvShowDetailService;
	let mockConfigService: ConfigService;

	describe('when a tv show id is passed', () => {
		const tvShowId = 100;
		const seasonNumber = 1;

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
					HttpClientTestingModule,
					MovieDetailComponent,
					TranslateTestingModule.withTranslations({}),
					BrowserAnimationsModule
				],
				providers: [
					{
						provide: ActivatedRoute,
						useValue: {
							snapshot: {
								paramMap: convertToParamMap({ 'tv-show-id': tvShowId })
							}
						}
					}
				]
			}).compileComponents();

			mockTvShowDetailService = TestBed.inject(TvShowDetailService);
			mockTvShowDetailService.getTvShow = jasmine.createSpy().and.returnValue(of({}));
			mockTvShowDetailService.getTvSeason = jasmine.createSpy().and.returnValue(of({ episodes: [] }));

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

		it(`should call getTvShow with ${tvShowId}`, () => {
			expect(mockTvShowDetailService.getTvShow).toHaveBeenCalledOnceWith(tvShowId);
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

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
					HttpClientTestingModule,
					MovieDetailComponent,
					TranslateTestingModule.withTranslations({}),
					RouterTestingModule
				]
			}).compileComponents();

			mockTvShowDetailService = TestBed.inject(TvShowDetailService);
			mockTvShowDetailService.getTvShow = jasmine.createSpy().and.returnValue(of({}));
			mockTvShowDetailService.getTvSeason = jasmine.createSpy().and.returnValue(of({}));

			fixture = TestBed.createComponent(TvShowDetailComponent);
			component = fixture.componentInstance;
			fixture.detectChanges();
		});

		it('should create', () => {
			expect(component).toBeTruthy();
		});

		it('should not call getTvShow', () => {
			expect(mockTvShowDetailService.getTvShow).not.toHaveBeenCalled();
		});

		it('should not call getTvSeason', () => {
			expect(mockTvShowDetailService.getTvSeason).not.toHaveBeenCalled();
		});
	});
});
