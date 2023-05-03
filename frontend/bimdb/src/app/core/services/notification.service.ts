import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ErrorNotification, InfoNotification, Notification, SuccessNotification, WarningNotification } from '../models/notification';

@Injectable({
	providedIn: 'root'
})
export class NotificationService {
	public notificationSubject$ = new Subject<Notification>();

	public success(message: string): void {
		const success = new SuccessNotification(message);
		this.notificationSubject$.next(success);
	}

	public info(message: string): void {
		const info = new InfoNotification(message);
		this.notificationSubject$.next(info);
	}

	public warning(message: string): void {
		const warning = new WarningNotification(message);
		this.notificationSubject$.next(warning);
	}

	public error(message: string): void {
		const error = new ErrorNotification(message);
		this.notificationSubject$.next(error);
	}

}
