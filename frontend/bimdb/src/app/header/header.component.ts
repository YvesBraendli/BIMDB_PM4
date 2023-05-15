import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
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
export class HeaderComponent implements OnInit {
	public language: string;
	public isLoggedIn = false;
	public user?: KeycloakProfile;

	public constructor(
		private windowService: WindowService,
		private translateService: TranslateService,
		private headerService: HeaderService,
		private keycloak: KeycloakService) {
		this.language = this.translateService.currentLang ?? this.translateService.defaultLang;
	}

	public ngOnInit(): void {
		this.keycloak.isLoggedIn().then(isLoggedIn => {
			this.isLoggedIn = isLoggedIn;
			if (isLoggedIn) {
				this.keycloak.loadUserProfile().then(user => {
					this.user = user;
				});
			}
		});
	}

	public changeLanguage(event: Event): void {
		const select = event.target as HTMLSelectElement;
		this.language = select.value;
		localStorage.setItem(StorageKeys.Language, this.language);
		this.windowService.reload();
	}

	public login(): void {
		this.keycloak.login();
	}

	public logout(): void {
		this.keycloak.logout();
	}

	public focusSearch(): void {
		this.headerService.focusSearch();
	}

}
