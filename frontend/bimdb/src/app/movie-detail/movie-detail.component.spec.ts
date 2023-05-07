import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { MovieDetailComponent } from './movie-detail.component';
import { MovieDetailService } from './movie-detail.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('MovieDetailComponent', () => {
	let component: MovieDetailComponent;
	let fixture: ComponentFixture<MovieDetailComponent>;
	let mockMovieDetailService: MovieDetailService;
	let mockConfigService: ConfigService;

	describe('when a movie-id is passed', () => {
		const movieId = 100;

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
					MovieDetailComponent,
					HttpClientTestingModule,
					TranslateTestingModule.withTranslations({}),
					BrowserAnimationsModule
				],
				providers: [
					{
						provide: ActivatedRoute,
						useValue: {
							snapshot: {
								paramMap: convertToParamMap({ 'movie-id': movieId })
							}
						}
					}
				]
			}).compileComponents();

			mockMovieDetailService = TestBed.inject(MovieDetailService);
			mockMovieDetailService.getMovie = jasmine.createSpy().and.returnValue(of({
				genres: [{ id: 0, name: 'Action' }, { id: 1, name: 'Drama' }],
				credits: { cast: [] },
				recommendations: {
					results: [{
						id: 0,
						name: 'Recommendation'
					}]
				},
				similar: {
					results: [{
						id: 0,
						name: 'Recommendation'
					}]
				}
			}));

			mockConfigService = TestBed.inject(ConfigService);
			spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
			spyOn(mockConfigService, 'getCountries').and.returnValue([]);
			spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

			fixture = TestBed.createComponent(MovieDetailComponent);
			component = fixture.componentInstance;
			fixture.detectChanges();
		});

		it('should create', () => {
			expect(component).toBeTruthy();
		});

		it(`should call getMovie with ${movieId}`, () => {
			expect(mockMovieDetailService.getMovie).toHaveBeenCalledOnceWith(movieId);
			expect(component.genreNames).toBe('Action, Drama');
		});
	});

	describe('when no movie-id is passed', () => {

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
					HttpClientTestingModule,
					MovieDetailComponent,
					RouterTestingModule,
					TranslateTestingModule.withTranslations({}),
					BrowserAnimationsModule
				]
			}).compileComponents();

			mockMovieDetailService = TestBed.inject(MovieDetailService);
			mockMovieDetailService.getMovie = jasmine.createSpy().and.returnValue(of({}));

			fixture = TestBed.createComponent(MovieDetailComponent);
			component = fixture.componentInstance;
			fixture.detectChanges();
		});

		it('should create', () => {
			expect(component).toBeTruthy();
		});

		it('should not call getMovie', () => {
			expect(mockMovieDetailService.getMovie).not.toHaveBeenCalled();
		});
	});
});
