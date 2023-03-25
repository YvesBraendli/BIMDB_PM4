import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { SearchService } from './search.service';

@Component({
	standalone: true,
	imports: [CommonModule, TranslateModule],
	selector: 'app-header-search',
	templateUrl: './header-search.component.html',
	styleUrls: ['./header-search.component.scss']
})
export class HeaderSearchComponent {
	public results = ['Test'] as string[];
	public dropdownIndex = -1;

	public constructor(private searchService: SearchService) { }

	public onKeyDown(event: KeyboardEvent): void {
		switch (event.key) {
		case 'ArrowUp':
			this.dropdownIndex = this.dropdownIndex > 0 ? this.dropdownIndex - 1 : this.results.length - 1;
			break;
		case 'ArrowDown':
			this.dropdownIndex = this.dropdownIndex < this.results.length - 1 ? this.dropdownIndex + 1 : 0;
			break;
		case 'Tab':
		case 'Enter':
			if (this.dropdownIndex >= 0) {
				(event.target as HTMLInputElement).value = this.results[this.dropdownIndex] ?? '';
			}
		}
	}

	public onFocus(event: Event): void {
		this.dropdownIndex = this.results.indexOf((event.target as HTMLInputElement).value);
	}

	public onInput(event: Event): void {
		const prefix = (event.target as HTMLInputElement).value;
		this.searchService.search(prefix).subscribe({
			next: results => this.results = results.map(result => result.name ?? '')
		});
	}
}
