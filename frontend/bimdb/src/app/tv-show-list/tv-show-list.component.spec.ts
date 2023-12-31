import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePipe } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { DiscoverTv } from '../generated/contract';
import { TvShowListComponent } from './tv-show-list.component';
import { TvShowListService } from './tv-show-list.service';

describe('TvShowListComponent', () => {
	let component: TvShowListComponent;
	let fixture: ComponentFixture<TvShowListComponent>;
	let tvShowListService: TvShowListService;
	let discoverSpy: jasmine.Spy;
	let mockConfigService: ConfigService;
	const discoverTv: DiscoverTv = {
		page: 1,
		totalPages: 1,
		totalResults: 0,
		results: [{ id: 0, name: 'Title', releaseDate: new Date('01.01.2023'), posterPath: '' }]
	} as DiscoverTv;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [TvShowListComponent,
				HttpClientTestingModule,
				TranslateTestingModule.withTranslations({}),
				RouterTestingModule],
			providers: [DatePipe]
		})
			.compileComponents();
		tvShowListService = TestBed.inject(TvShowListService);
		discoverSpy = spyOn(tvShowListService, 'discover').and.returnValue(of(discoverTv));
		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());
		fixture = TestBed.createComponent(TvShowListComponent);
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
