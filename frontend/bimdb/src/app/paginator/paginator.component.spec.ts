import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { PaginatorComponent } from './paginator.component';

describe('PaginatorComponent', () => {
	let component: PaginatorComponent;
	let fixture: ComponentFixture<PaginatorComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [PaginatorComponent, TranslateTestingModule.withTranslations({})]
		})
			.compileComponents();

		fixture = TestBed.createComponent(PaginatorComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should go to next page', () => {
		component.goToNextPage();
		expect(component.page).toEqual(2);
	});

	it('should go to previous page', () => {
		component.page = 2;
		component.goToPreviousPage();
		expect(component.page).toEqual(1);
	});

	it('should go to first page', () => {
		component.page = 2;
		component.goToFirstPage();
		expect(component.page).toEqual(1);
	});

	it('should go to last page', () => {
		component.totalPages = 3;
		component.goToLastPage();
		expect(component.page).toEqual(3);
	});

	it('should go to page', () => {
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.value = '2';
		input.dispatchEvent(new Event('input'));
		expect(component.page).toEqual(2);
	});

	it('should not go to page outside of range', () => {
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		component.totalPages = 1;
		input.value = '2';
		input.dispatchEvent(new Event('input'));
		expect(component.page).toEqual(1);
	});
});
