import { COMMA } from '@angular/cdk/keycodes';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent, MatChipsModule } from '@angular/material/chips';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { TranslateModule } from '@ngx-translate/core';
import { BaseAutocompleteComponent } from '../base-autocomplete.component';
import { AutocompleteOption } from '../single-autocomplete/autocomplete.component';

@Component({
	selector: 'app-multi-autocomplete',
	standalone: true,
	imports: [CommonModule, MatAutocompleteModule, MatChipsModule, MatFormFieldModule, MatIconModule, ReactiveFormsModule, TranslateModule],
	templateUrl: './multi-autocomplete.component.html',
	styleUrls: ['./multi-autocomplete.component.scss']
})
export class MultiAutocompleteComponent extends BaseAutocompleteComponent {
	public separatorKeysCodes: number[] = [COMMA];
	public selectedOptions: AutocompleteOption[] = [];

	public onMatChipInputTokenEnd(event: MatChipInputEvent): void {
		this.add(event.value);
	}

	public override selectOption(event: MatAutocompleteSelectedEvent): void {
		super.selectOption(event);
		this.add(event.option.value?.displayValue);
	}

	public remove(option: AutocompleteOption): void {
		const index = this.selectedOptions.indexOf(option);
		if (index >= 0) {
			this.selectedOptions.splice(index, 1);
		}
	}

	private add(value: string): void {
		value = value ?? '';
		const alreadySelected = this.selectedOptions.map(o => o.displayValue.toLowerCase()).indexOf(value.toLowerCase()) >= 0;
		const option = this.options.find(o => o.displayValue.toLowerCase() === value.toLowerCase());
		if (value && option && !alreadySelected) {
			this.selectedOptions.push(option);
		} else if (alreadySelected && option) {
			this.remove(option);
		}
		this.autocompleteControl.setValue('');
	}
}
