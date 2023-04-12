import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { MovieDetailComponent } from './movie-detail.component';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { MovieDetailService } from './movie-detail.service';
import { of } from 'rxjs';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

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
			mockMovieDetailService.getMovie = jasmine.createSpy().and.returnValue(of({}));

			fixture = TestBed.createComponent(MovieDetailComponent);
			component = fixture.componentInstance;
			fixture.detectChanges();
		});

		it('should create', () => {
			expect(component).toBeTruthy();
		});

		it(`should call getMovie with ${movieId}`, () => {
			expect(mockMovieDetailService.getMovie).toHaveBeenCalledOnceWith(movieId);
		});
	});

	describe('when no movie-id is passed', () => {

		beforeEach(async () => {
			await TestBed.configureTestingModule({
				imports: [
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
