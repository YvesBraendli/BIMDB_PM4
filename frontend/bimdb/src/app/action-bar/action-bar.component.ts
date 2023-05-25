import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { TranslateModule } from '@ngx-translate/core';
import { KeycloakService } from 'keycloak-angular';
import { WindowService } from '../core/services/window.service';
import { MediaType } from '../generated/contract';
import { ActionBarService } from './action-bar.service';


@Component({
	selector: 'app-action-bar',
	standalone: true,
	templateUrl: './action-bar.component.html',
	styleUrls: ['./action-bar.component.scss'],
	imports: [CommonModule, TranslateModule, MatButtonModule, MatIconModule, MatSnackBarModule, TranslateModule]
})
export class ActionBarComponent implements OnInit {
	public isLoggedIn = false;
	public isFavorite = false;

	@Input()
	public id?: number;

	@Input()
	public mediaType?: MediaType;

	public constructor(
		private keycloak: KeycloakService,
		private actionBarService: ActionBarService,
		private snackBar: MatSnackBar,
		private windowService: WindowService
	) {
	}

	public ngOnInit(): void {
		this.keycloak.isLoggedIn().then((isLoggedIn) => {
			this.isLoggedIn = isLoggedIn;
			this.getFavoriteStatus();
		});
	}

	public getFavoriteStatus(): void {
		if (this.isLoggedIn && this.id && this.id >= 0 && this.mediaType) {
			this.actionBarService.checkFavoriteStatus(this.id, this.mediaType).subscribe({
				next: result => {
					this.isFavorite = result.isFavorite;
				}
			});
		}
	}

	public removeFromFavorites(): void {
		if (this.isLoggedIn && this.id && this.id >= 0 && this.mediaType) {
			this.actionBarService.removeFavorite(this.id, this.mediaType).subscribe({
				next: result => {
					this.isFavorite = result.isFavorite;
				}
			});
		}
	}

	public addToFavorites(): void {
		if (this.isLoggedIn && this.id && this.id >= 0 && this.mediaType) {
			this.actionBarService.addFavorite(this.id, this.mediaType).subscribe({
				next: result => {
					this.isFavorite = result.isFavorite;
				}
			});
		}
	}

	public copyToClipboard(): void {
		const currentLocation = this.windowService.location.href;
		navigator.clipboard.writeText(currentLocation)
			.then(() => {
				this.snackBar.open('Link copied to clipboard!', 'Close', {
					duration: 2000
				});
			})
			.catch((error) => {
				console.error('Failed to copy link to clipboard:', error);
			});
	}

}
