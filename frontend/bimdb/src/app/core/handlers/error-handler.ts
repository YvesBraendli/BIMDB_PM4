import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { CustomHttpErrorResponse } from '../models/custom-http-error';
import { NotificationService } from '../services/notification.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

	public constructor(private injector: Injector) { }

	public handleError(error: CustomHttpErrorResponse | Error): void {
		const notificationService = this.injector.get(NotificationService);
		const isCustomHttpError = error instanceof CustomHttpErrorResponse;
		if (!isCustomHttpError || (isCustomHttpError && !error.alreadyHandled)) {
			notificationService.error(error.message);
		}
	}
}
