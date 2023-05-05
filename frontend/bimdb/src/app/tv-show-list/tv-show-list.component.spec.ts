import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePipe } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { DiscoverTv } from '../generated/contract';
import { TvShowListComponent } from './tv-show-list.component';
import { TvShowListService } from './tv-show-list.service';

describe('TvShowListComponent', () => {
	let component: TvShowListComponent;
	let fixture: ComponentFixture<TvShowListComponent>;
	let tvShowListService: TvShowListService;
	let discoverSpy: jasmine.Spy;
	const discoverTv: DiscoverTv = {
		page: 1,
		total_pages: 1,
		total_results: 0,
		results: [{ id: 0, name: 'Title', first_air_date: '01.01.2023', poster_path: '' }]
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
