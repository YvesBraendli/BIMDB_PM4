import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { StorageKeys } from '../core/constants/storage-keys';
import { WindowService } from '../core/services/window.service';
import { HeaderService } from './header.service';

@Component({
	standalone: true,
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.scss'],
	imports: [CommonModule, TranslateModule, RouterModule]
})
export class HeaderComponent {
	public language = 'en';

	public constructor(
		private windowService: WindowService,
		private translateService: TranslateService,
		private headerService: HeaderService) {
		this.language = this.translateService.currentLang ?? this.translateService.defaultLang;
	}

	public changeLanguage(event: Event): void {
		const select = event.target as HTMLSelectElement;
		this.language = select.value;
		localStorage.setItem(StorageKeys.Language, this.language);
		this.windowService.reload();
	}

	public focusSearch(): void {
		this.headerService.focusSearch();
	}
}
