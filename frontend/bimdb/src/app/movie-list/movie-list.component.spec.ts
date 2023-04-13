import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { RouterTestingModule } from '@angular/router/testing';
import { MovieListComponent } from './movie-list.component';

describe('MovieListComponent', () => {
	let component: MovieListComponent;
	let fixture: ComponentFixture<MovieListComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [ MovieListComponent, TranslateTestingModule.withTranslations({}), RouterTestingModule ]
		})
			.compileComponents();

		fixture = TestBed.createComponent(MovieListComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
