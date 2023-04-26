import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { MovieDetailComponent } from './movie-detail.component';
import { MovieDetailService } from './movie-detail.service';

describe('MovieDetailComponent', () => {
	let component: MovieDetailComponent;
	let fixture: ComponentFixture<MovieDetailComponent>;
	let mockMovieDetailService: MovieDetailService;

	describe('when a movie-id is passed', () => {
		const movieId = 100;

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
					MovieDetailComponent,
					HttpClientTestingModule,
					TranslateTestingModule.withTranslations({})
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
			mockMovieDetailService.getMovie = jasmine.createSpy().and.returnValue(of({ genres: [{ id: 0, name: 'Action' }, {id: 1, name: 'Drama'}] }));

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
					TranslateTestingModule.withTranslations({})
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
