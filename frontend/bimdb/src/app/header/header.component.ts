import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { HeaderSearchComponent } from '../header-search/header-search.component';
import { LocalStorageKeys } from '../shared/models/storage-keys';
import { WindowService } from '../shared/services/window.service';
import { RouterModule } from '@angular/router';

@Component({
	standalone: true,
	imports: [CommonModule, HeaderSearchComponent, TranslateModule, RouterModule],
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
	public language = 'en';

	public constructor(private windowService: WindowService, private translateService: TranslateService) {
		this.language = this.translateService.currentLang ?? this.translateService.defaultLang;
	}

	public changeLanguage(event: Event): void {
		const select = event.target as HTMLSelectElement;
		this.language = select.value;
		localStorage.setItem(LocalStorageKeys.Language, this.language);
		this.windowService.reload();
	}
}
