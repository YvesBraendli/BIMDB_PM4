import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TranslateTestingModule } from 'ngx-translate-testing';
import { WatchProvider, WatchProviders, WatchProvidersResult } from '../generated/contract';
import { WatchProviderListComponent } from './watch-provider-list.component';

describe('WatchProviderListComponent', () => {
	let component: WatchProviderListComponent;
	let fixture: ComponentFixture<WatchProviderListComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				WatchProviderListComponent,
				TranslateTestingModule.withTranslations({})
			]
		})
			.compileComponents();

		fixture = TestBed.createComponent(WatchProviderListComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should filter watch providers', () => {
		const watchProvider = {
			providerName: 'Google Play Movies'
		} as WatchProvider;

		component.watchProvidersResult = {
			watchProviders: [
				{
					country: 'CH',
					flatrate: [watchProvider],
					rent: [],
					buy: []
				} as WatchProviders,
				{
					country: 'DE',
					flatrate: [],
					rent: [],
					buy: []
				} as WatchProviders
			]
		} as WatchProvidersResult;

		expect(component.watchProvidersResult.watchProviders.length).toEqual(1);
	});
});
