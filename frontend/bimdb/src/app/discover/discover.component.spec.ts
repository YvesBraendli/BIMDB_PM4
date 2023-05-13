import { DatePipe } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { DiscoverMovie } from '../generated/contract';
import { DiscoverComponent } from './discover.component';
import { DiscoverService } from './discover.service';

describe('DiscoverComponent', () => {
	let component: DiscoverComponent;
	let fixture: ComponentFixture<DiscoverComponent>;
	let discoverService: DiscoverService;
	let discoverSpy: jasmine.Spy;
	let mockConfigService: ConfigService;
	const discoverMovie: DiscoverMovie = {
		page: 1,
		totalPages: 10,
		totalResults: 200,
		results: [{ id: 0, name: 'Title', releaseDate: new Date('01.01.2023'), posterPath: '' }]
	} as DiscoverMovie;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				DiscoverComponent,
				HttpClientTestingModule,
				TranslateTestingModule.withTranslations({}),
				RouterTestingModule],
			providers: [DatePipe]
		})
			.compileComponents();
		discoverService = TestBed.inject(DiscoverService);
		discoverSpy = spyOn(discoverService, 'discover').and.returnValue(of(discoverMovie));

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

		fixture = TestBed.createComponent(DiscoverComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
		expect(discoverSpy).toHaveBeenCalledOnceWith(1);
	});

	it('should change page', () => {
		component.onPageChange(2);
		expect(component.page).toEqual(2);
	});
});
