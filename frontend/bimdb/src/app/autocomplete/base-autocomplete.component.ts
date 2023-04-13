import { Directive, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Observable, of, startWith } from 'rxjs';
import { AutocompleteOption } from './single-autocomplete/autocomplete.component';

@Directive()
export abstract class BaseAutocompleteComponent implements OnInit {
	@Input()
	public placeholder = 'search';
	@Input()
	public options: AutocompleteOption[] = [];
	@Input()
	public fetchRemote?: (value: string) => Observable<AutocompleteOption[]>;
	@Input()
	public theme: 'light' | 'dark' = 'dark';
	@Output()
	public optionSelected = new EventEmitter<string>();
	public autocompleteControl = new FormControl();
	public filteredOptions: AutocompleteOption[] = [];
	private fetchData!: (value: string) => Observable<AutocompleteOption[]>;

	public ngOnInit(): void {
		this.fetchData = this.fetchRemote ?? this.defaultFilter;
		this.autocompleteControl.valueChanges.pipe(
			startWith('')
		).subscribe((value: string | AutocompleteOption) => {
			const query = typeof value === 'string' ? value : value?.displayValue;
			this.fetchData(query ?? '').subscribe(options => this.filteredOptions = options);
		});
	}

	public selectOption(event: MatAutocompleteSelectedEvent): void {
		this.optionSelected.emit(event.option.value);
	}

	public displayWith(option: AutocompleteOption): string {
		return option?.displayValue ?? '';
	}

	private defaultFilter(value: string | null): Observable<AutocompleteOption[]> {
		const filterValue = value?.toLowerCase() || '';
		return of(this.options.filter(option => option.displayValue.toLowerCase().includes(filterValue) ||
			option.value.toLowerCase().includes(filterValue)));
	}
}
