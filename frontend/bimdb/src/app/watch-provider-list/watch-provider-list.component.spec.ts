import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WatchProviderListComponent } from './watch-provider-list.component';
import { TranslateTestingModule } from 'ngx-translate-testing';

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
});
