import { CommonModule, Location } from '@angular/common';
import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { ActivatedRoute } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { Subject, debounceTime, takeUntil } from 'rxjs';
import { MOVIES_ROUTE, PEOPLE_ROUTE, SEARCH_ROUTE, TV_SHOWS_ROUTE } from '../core/constants/routes';
import { SearchService } from '../core/services/search.service';
import { CreditListComponent } from '../credit-list/credit-list.component';
import { DiscoverMovie, DiscoverTv, People, SearchResultWrapper } from '../generated/contract';
import { HeaderService } from '../header/header.service';
import { MediaListComponent } from '../media-list/media-list.component';

export const QUERY_PARAM_SEARCH_QUERY = 'query';
export const DATA_TAB_INDEX = 'tabIndex';

@Component({
	selector: 'app-search',
	standalone: true,
	templateUrl: './search.component.html',
	styleUrls: ['./search.component.scss'],
	imports: [CommonModule, MatTabsModule, TranslateModule, MediaListComponent, CreditListComponent, ReactiveFormsModule]
})
export class SearchComponent implements OnInit, OnDestroy, AfterViewInit {
	public results?: SearchResultWrapper;
	public searchControl = new FormControl();
	public discoverMovie?: DiscoverMovie;
	public discoverTv?: DiscoverTv;
	public people?: People;
	public selectedTabIndex = 0;
	@ViewChild('searchInput')
	public searchInput!: ElementRef;
	private onDestroy$ = new Subject<void>();
	private query = '';

	public constructor(private searchService: SearchService,
		private route: ActivatedRoute,
		private headerService: HeaderService,
		private location: Location) {
	}

	public ngOnInit(): void {
		this.searchControl.valueChanges.pipe(
			debounceTime(150)
		).subscribe((query: string) => {
			if (query !== this.query) {
				this.query = query;
			}
			this.search();
			this.updateLocation();
		});
		this.route.data.subscribe((data) => {
			this.selectedTabIndex = data[DATA_TAB_INDEX] ?? SearchTabs.ALL;
		});
		this.route.queryParams.subscribe(params => {
			const query = params[QUERY_PARAM_SEARCH_QUERY];
			if (query && query !== this.query) {
				this.query = query;
				this.searchControl.setValue(query);
			}
		});
		this.headerService.focusSearch$.pipe(takeUntil(this.onDestroy$)).subscribe(() => {
			this.searchInput.nativeElement.focus();
		});
	}

	public ngAfterViewInit(): void {
		this.searchInput.nativeElement.focus();
	}

	public ngOnDestroy(): void {
		this.onDestroy$.next();
		this.onDestroy$.complete();
	}

	public selectedIndexChange(index: number): void {
		this.selectedTabIndex = index;
		this.updateLocation();
	}

	public search(): void {
		this.searchMulti();
		this.searchMovies();
		this.searchTv();
		this.searchPeople();
	}

	public searchMulti(page = 1): void {
		this.searchService.search(this.query, page).subscribe(results => this.results = results);
	}

	public searchMovies(page = 1): void {
		this.searchService.searchMovies(this.query, page).subscribe(discoverMovies => this.discoverMovie = discoverMovies);
	}

	public searchTv(page = 1): void {
		this.searchService.searchTv(this.query, page).subscribe(discoverTv => this.discoverTv = discoverTv);
	}

	public searchPeople(page = 1): void {
		this.searchService.searchPeople(this.query, page).subscribe(people => this.people = people);
	}

	private updateLocation(): void {
		let state = `/${SEARCH_ROUTE}`;
		const routeParam = this.getRouteParam(this.selectedTabIndex);
		if (routeParam !== '') {
			state += `/${routeParam}`;
		}
		if (this.query && this.query !== '') {
			state += `?${QUERY_PARAM_SEARCH_QUERY}=${this.query}`;
		}
		this.location.replaceState(state);
	}

	private getRouteParam(index: number): string {
		switch (index) {
			case SearchTabs.MOVIES:
				return MOVIES_ROUTE;
			case SearchTabs.TV:
				return TV_SHOWS_ROUTE;
			case SearchTabs.PEOPLE:
				return PEOPLE_ROUTE;
			default:
				return '';
		}
	}
}

export enum SearchTabs {
	ALL,
	MOVIES,
	TV,
	PEOPLE
}
