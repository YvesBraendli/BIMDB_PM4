import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { AutocompleteComponent } from '../autocomplete/single-autocomplete/autocomplete.component';
import { LocalStorageKeys } from '../shared/models/storage-keys';
import { SearchService } from '../shared/services/search.service';
import { WindowService } from '../shared/services/window.service';

@Component({
	standalone: true,
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss'],
	imports: [CommonModule, TranslateModule, RouterModule, AutocompleteComponent]
})
export class HeaderComponent {
	public language = 'en';
	public search = this.searchService.suggestions;

	public constructor(private windowService: WindowService, private translateService: TranslateService, private searchService: SearchService) {
		this.language = this.translateService.currentLang ?? this.translateService.defaultLang;
	}

	public changeLanguage(event: Event): void {
		const select = event.target as HTMLSelectElement;
		this.language = select.value;
		localStorage.setItem(LocalStorageKeys.Language, this.language);
		this.windowService.reload();
	}
}
