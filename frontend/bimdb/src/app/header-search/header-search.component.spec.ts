import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { HeaderSearchComponent } from './header-search.component';

describe('HeaderSearchComponent', () => {
	let component: HeaderSearchComponent;
	let fixture: ComponentFixture<HeaderSearchComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [ HeaderSearchComponent, TranslateTestingModule.withTranslations({}) ]
		})
			.compileComponents();

		fixture = TestBed.createComponent(HeaderSearchComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should trigger search on input', () => {
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.value = 'Barbie';
		input.dispatchEvent(new Event('input'));
		expect(component.results.length).toEqual(1);
	});

	it('should increase dropdown index on arrow down', () => {
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'ArrowDown' }));
		expect(component.dropdownIndex).toEqual(0);
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'ArrowDown' }));
		expect(component.dropdownIndex).toEqual(0);
	});

	it('should increase dropdown index on arrow down', () => {
		component.dropdownIndex = 1;
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'ArrowUp' }));
		expect(component.dropdownIndex).toEqual(0);
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'ArrowUp' }));
		expect(component.dropdownIndex).toEqual(0);
	});

	it('should select dropdown item on enter', () => {
		component.dropdownIndex = 0;
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter' }));
		expect(input.value).toEqual('Test');
	});

	it('should select dropdown item on tab', () => {
		component.dropdownIndex = 0;
		const element = fixture.nativeElement as HTMLElement;
		const input = element.querySelector('input') as HTMLInputElement;
		input.dispatchEvent(new KeyboardEvent('keydown', { key: 'Tab' }));
		expect(input.value).toEqual('Test');
	});

});
