import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { HttpHeaderConstants } from '../constants/http-headers';

@Injectable()
export class HeaderInterceptor implements HttpInterceptor {

	public constructor(private translateService: TranslateService) { }

	public intercept<T>(req: HttpRequest<T>, next: HttpHandler): Observable<HttpEvent<T>> {
		req = req.clone({ setHeaders: { [HttpHeaderConstants.ACCEPT_LANGUAGE] : this.translateService.currentLang } });
		return next.handle(req);
	}
}
