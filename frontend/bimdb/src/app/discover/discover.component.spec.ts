import { DatePipe } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { RouterTestingModule } from '@angular/router/testing';
import { DiscoverComponent } from './discover.component';

describe('DiscoverComponent', () => {
	let component: DiscoverComponent;
	let fixture: ComponentFixture<DiscoverComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				DiscoverComponent,
				TranslateTestingModule.withTranslations({}),
				RouterTestingModule],
			providers: [ DatePipe ]
		})
			.compileComponents();

		fixture = TestBed.createComponent(DiscoverComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should change page', () => {
		component.onPageChange(2);
		expect(component.page).toEqual(2);
	});
});
