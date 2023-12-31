import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { MovieListComponent } from './movie-list.component';

describe('MovieListComponent', () => {
	let component: MovieListComponent;
	let fixture: ComponentFixture<MovieListComponent>;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [MovieListComponent, TranslateTestingModule.withTranslations({}), RouterTestingModule, HttpClientTestingModule]
		})
			.compileComponents();

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

		fixture = TestBed.createComponent(MovieListComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
