import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';
import { Subject, filter, takeUntil } from 'rxjs';
import { Notification } from '../core/models/notification';
import { NotificationService } from '../core/services/notification.service';

@Component({
	selector: 'app-notification',
	standalone: true,
	imports: [CommonModule, TranslateModule],
	templateUrl: './notification.component.html',
	styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit, OnDestroy {
	public notifications: Notification[] = [];
	private previousRoute?: string;
	private onDestroy$ = new Subject<void>();

	public constructor(private router: Router, private notificationService: NotificationService, private cd: ChangeDetectorRef) {
	}

	public ngOnInit(): void {
		this.router.events.pipe(
			filter(event => event instanceof NavigationEnd),
			takeUntil(this.onDestroy$)).subscribe(_event => {
			if (this.router.url !== this.previousRoute) {
				this.notifications = [];
			}
			this.previousRoute = this.router.url;
		});
		this.notificationService.notificationSubject$.pipe(
			takeUntil(this.onDestroy$)).subscribe(notification => {
			this.notifications.push(notification);
			this.cd.detectChanges();
		});
	}

	public ngOnDestroy(): void {
		this.onDestroy$.next();
		this.onDestroy$.complete();
	}

	public clearNotification(index: number): void {
		this.notifications?.splice(index, 1);
		this.cd.detectChanges();
	}
}
