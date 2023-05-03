import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
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
	public title = 'bimdb';
}
