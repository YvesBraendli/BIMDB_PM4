import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { AutocompleteComponent, AutocompleteOption } from './autocomplete.component';

describe('AutocompleteComponent', () => {
	let component: AutocompleteComponent;
	let fixture: ComponentFixture<AutocompleteComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [AutocompleteComponent, TranslateTestingModule.withTranslations({})]
		})
			.compileComponents();

		fixture = TestBed.createComponent(AutocompleteComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should return all options with null query', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.autocompleteControl.setValue(null);
		expect(component.filteredOptions.length).toBe(2);
	});

	it('should filter options with autocomplete option', () => {
		component.options = [{ displayValue: 'foo', value: 'foo' }, { displayValue: 'bar', value: 'bar' }];
		component.autocompleteControl.setValue({displayValue: 'f'} as AutocompleteOption);
		expect(component.filteredOptions.length).toBe(1);
	});

});
