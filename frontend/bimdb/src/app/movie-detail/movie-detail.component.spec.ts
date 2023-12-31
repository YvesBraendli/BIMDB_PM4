import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Params } from '@angular/router';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of, Subject } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { MovieDetailComponent } from './movie-detail.component';
import { MovieDetailService } from './movie-detail.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Cast, Crew } from '../generated/contract';

describe('MovieDetailComponent', () => {
	let params: Subject<Params>;
	let component: MovieDetailComponent;
	let fixture: ComponentFixture<MovieDetailComponent>;
	let mockMovieDetailService: MovieDetailService;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		params = new Subject<Params>();

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
						params
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
		mockMovieDetailService.getWatchProviders = jasmine.createSpy().and.returnValue(of({
			results: {}
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

	describe('when a movie id is passed', () => {
		const movieId = 100;

		beforeEach((done) => {
			params.next({ 'movie-id': movieId });
			done();
		});

		it(`should call getMovie with ${movieId}`, () => {
			expect(mockMovieDetailService.getMovie).toHaveBeenCalledOnceWith(movieId);
			expect(component.genreNames).toBe('Action, Drama');
		});

		it(`should call getWatchProviders with ${movieId}`, () => {
			expect(mockMovieDetailService.getWatchProviders).toHaveBeenCalledOnceWith(movieId);
		});
	});

	describe('when no movie id is passed', () => {

		beforeEach((done) => {
			params.next({});
			done();
		});

		it('should not call getMovie', () => {
			expect(mockMovieDetailService.getMovie).not.toHaveBeenCalled();
		});

		it('should not call getWatchProviders', () => {
			expect(mockMovieDetailService.getWatchProviders).not.toHaveBeenCalled();
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
