import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { TranslateModule } from '@ngx-translate/core';
import { BaseAutocompleteComponent } from '../base-autocomplete.component';

@Component({
	selector: 'app-autocomplete',
	standalone: true,
	imports: [CommonModule, MatAutocompleteModule, ReactiveFormsModule, TranslateModule],
	templateUrl: './autocomplete.component.html',
	styleUrls: ['./autocomplete.component.scss']
})
export class AutocompleteComponent extends BaseAutocompleteComponent {
}

export interface AutocompleteOption {
	value: string;
	displayValue: string;
}
