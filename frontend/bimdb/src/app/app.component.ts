import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ConfigService } from './core/services/config.service';
import { NotificationService } from './core/services/notification.service';
import { HeaderComponent } from './header/header.component';
import { NotificationComponent } from './notification/notification.component';

@Component({
	standalone: true,
	imports: [CommonModule, RouterModule, NotificationComponent, HeaderComponent],
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent {
	public configInitialized = false;

	public constructor(private configService: ConfigService, private notificationService: NotificationService) {
		this.configService.loadAppConfig(true).subscribe({
			next: () => this.configInitialized = true,
			error: () => this.notificationService.error('error.config')
		});
	}
}
