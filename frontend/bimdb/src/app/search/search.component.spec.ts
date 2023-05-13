import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { Location } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { MOVIES_ROUTE, PEOPLE_ROUTE, SEARCH_ROUTE, TV_SHOWS_ROUTE } from '../core/constants/routes';
import { SearchService } from '../core/services/search.service';
import { DiscoverMovie, DiscoverTv, People, SearchResultWrapper } from '../generated/contract';
import { HeaderService } from '../header/header.service';
import { QUERY_PARAM_SEARCH_QUERY, SearchComponent, SearchTabs } from './search.component';

describe('SearchComponent', () => {
	let component: SearchComponent;
	let fixture: ComponentFixture<SearchComponent>;
	let headerService: HeaderService;
	let locationReplaceStateSpy: jasmine.Spy;
	let router: Router;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				SearchComponent,
				HttpClientTestingModule,
				RouterTestingModule.withRoutes([
					{ path: SEARCH_ROUTE, component: SearchComponent }
				]),
				BrowserAnimationsModule,
				TranslateTestingModule.withTranslations({}),
			]
		})
			.compileComponents();

		locationReplaceStateSpy = spyOn(TestBed.inject(Location), 'replaceState');
		const searchService = TestBed.inject(SearchService);
		spyOn(searchService, 'search').and.returnValue(of({ page: 1, totalPages: 1, totalResults: 0, results: [] } as SearchResultWrapper));
		spyOn(searchService, 'searchMovies').and.returnValue(of({ page: 1, totalPages: 1, totalResults: 0, results: [] } as DiscoverMovie));
		spyOn(searchService, 'searchTv').and.returnValue(of({ page: 1, totalPages: 1, totalResults: 0, results: [] } as DiscoverTv));
		spyOn(searchService, 'searchPeople').and.returnValue(of({ page: 1, totalPages: 1, totalResults: 0, results: [] } as People));
		headerService = TestBed.inject(HeaderService);
		router = TestBed.inject(Router);
		fixture = TestBed.createComponent(SearchComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should replace location state for multi search', fakeAsync(() => {
		const searchQuery = 'all';
		component.selectedTabIndex = SearchTabs.ALL;
		component.searchControl.setValue(searchQuery);
		tick(150);
		expect(locationReplaceStateSpy).toHaveBeenCalledWith(`/${SEARCH_ROUTE}?${QUERY_PARAM_SEARCH_QUERY}=${searchQuery}`);
	}));

	it('should replace location state for movie search', fakeAsync(() => {
		const searchQuery = 'movie';
		component.selectedTabIndex = SearchTabs.MOVIES;
		component.searchControl.setValue(searchQuery);
		tick(150);
		expect(locationReplaceStateSpy).toHaveBeenCalledWith(`/${SEARCH_ROUTE}/${MOVIES_ROUTE}?${QUERY_PARAM_SEARCH_QUERY}=${searchQuery}`);
	}));

	it('should replace location state for tv search', fakeAsync(() => {
		const searchQuery = 'tv';
		component.selectedTabIndex = SearchTabs.TV;
		component.searchControl.setValue(searchQuery);
		tick(150);
		expect(locationReplaceStateSpy).toHaveBeenCalledWith(`/${SEARCH_ROUTE}/${TV_SHOWS_ROUTE}?${QUERY_PARAM_SEARCH_QUERY}=${searchQuery}`);
	}));

	it('should replace location state for people search', fakeAsync(() => {
		const searchQuery = 'person';
		component.selectedTabIndex = SearchTabs.PEOPLE;
		component.searchControl.setValue(searchQuery);
		tick(150);
		expect(locationReplaceStateSpy).toHaveBeenCalledWith(`/${SEARCH_ROUTE}/${PEOPLE_ROUTE}?${QUERY_PARAM_SEARCH_QUERY}=${searchQuery}`);
	}));

	it('should update location and slected index', () => {
		expect(component.selectedTabIndex).toBe(SearchTabs.ALL);
		component.selectedIndexChange(SearchTabs.PEOPLE);
		expect(component.selectedTabIndex).toBe(SearchTabs.PEOPLE);
		expect(locationReplaceStateSpy).toHaveBeenCalledWith(`/${SEARCH_ROUTE}/${PEOPLE_ROUTE}`);
	});

	it('should focus search input', () => {
		const searchInputSpy = spyOn(component.searchInput.nativeElement, 'focus');
		headerService.focusSearch();
		expect(searchInputSpy).toHaveBeenCalled();
	});

	it('should update query', fakeAsync(() => {
		const query = 'test';
		expect(component.searchControl.value).toBe(null);
		router.navigate([SEARCH_ROUTE], { queryParams: { query } });
		tick(150);
		expect(component.searchControl.value).toBe(query);
	}));

});
