import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { MultiAutocompleteComponent } from './multi-autocomplete.component';

describe('MultiAutocompleteComponent', () => {
	let component: MultiAutocompleteComponent;
	let fixture: ComponentFixture<MultiAutocompleteComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [ MultiAutocompleteComponent, TranslateTestingModule.withTranslations({}) ]
		})
			.compileComponents();

		fixture = TestBed.createComponent(MultiAutocompleteComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should add option', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.onMatChipInputTokenEnd({value: 'foo'} as MatChipInputEvent);
		expect(component.selectedOptions.length).toBe(1);
	});

	it('should add option via autocomplete event', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.selectOption({option: {value: {displayValue: 'foo'}}} as MatAutocompleteSelectedEvent);
		expect(component.selectedOptions.length).toBe(1);
	});

	it('should remove option if already selected', () => {
		const option = { displayValue: 'foo', value: 'foo' };
		component.options = [option, { displayValue: 'bar', value: 'bar' }];
		component.selectedOptions = [option];
		expect(component.selectedOptions.length).toBe(1);
		component.onMatChipInputTokenEnd({value: 'foo'} as MatChipInputEvent);
		expect(component.selectedOptions.length).toBe(0);
	});

	it('should not add option if empty', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.onMatChipInputTokenEnd({} as MatChipInputEvent);
		expect(component.selectedOptions.length).toBe(0);
	});

	it('should not add option if unavailable', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.onMatChipInputTokenEnd({value: 'asdf'} as MatChipInputEvent);
		expect(component.selectedOptions.length).toBe(0);
	});

});
