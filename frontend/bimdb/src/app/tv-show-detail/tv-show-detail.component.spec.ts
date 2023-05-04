import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TvShowDetailComponent } from './tv-show-detail.component';
import { TvShowDetailService } from './tv-show-detail.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MovieDetailComponent } from '../movie-detail/movie-detail.component';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('TvDetailComponent', () => {
	let component: TvShowDetailComponent;
	let fixture: ComponentFixture<TvShowDetailComponent>;
	let mockTvShowDetailService: TvShowDetailService;

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
