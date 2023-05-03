import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NotificationService } from 'src/app/core/services/notification.service';
import { HttpHeaderConstants } from '../constants/http-headers';
import { CustomHttpErrorResponse } from '../models/custom-http-error';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

	public constructor(private notificationService: NotificationService) { }

	public intercept<T>(req: HttpRequest<T>, next: HttpHandler): Observable<HttpEvent<T>> {
		const hasCustomErrorHandler = JSON.parse(req.headers.get(HttpHeaderConstants.CUSTOM_ERROR_HANDLER) ?? '');
		return new Observable(observer => {
			next.handle(req).subscribe({
				next: res => observer.next(res),
				error: (err: HttpErrorResponse) => {
					if (!hasCustomErrorHandler) {
						this.notificationService.error(`error.http.${err.status}`);
					}
					observer.error(new CustomHttpErrorResponse(err, !hasCustomErrorHandler));
				}
			}
			);
		});
	}
}
