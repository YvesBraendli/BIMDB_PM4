import { HttpBackend, HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_INITIALIZER, enableProdMode, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { Observable } from 'rxjs';
import { AppComponent } from './app/app.component';
import { DiscoverComponent } from './app/discover/discover.component';
import { NotFoundComponent } from './app/not-found/not-found.component';
import { HeaderInterceptor } from './app/shared/interceptors/header.interceptor';
import { LocalStorageKeys } from './app/shared/models/storage-keys';

import { environment } from './environments/environment.prod';

if (environment.production) {
	enableProdMode();
}
const routes: Routes = [
	{ path: '', redirectTo: 'discover', pathMatch: 'full' },
	{ path: 'discover', component: DiscoverComponent },
	{ path: '**', component: NotFoundComponent }
];

function HttpLoaderFactory(httpHandler: HttpBackend): TranslateHttpLoader {
	return new TranslateHttpLoader(new HttpClient(httpHandler));
}

function initializeApp(translateService: TranslateService) {
	return (): Observable<void> => {
		return new Observable(observer => {
			const language = localStorage.getItem(LocalStorageKeys.Language) ?? 'en';
			translateService.setDefaultLang('en');
			translateService.use(language);
			observer.next();
			observer.complete();
		});
	};
}

bootstrapApplication(AppComponent, {
	providers: [
		provideRouter(routes),
		importProvidersFrom(HttpClientModule),
		importProvidersFrom(TranslateModule.forRoot({
			defaultLanguage: 'en',
			loader: {
				provide: TranslateLoader,
				useFactory: HttpLoaderFactory,
				deps: [HttpBackend]
			}
		})),
		{ provide: APP_INITIALIZER, useFactory: initializeApp, deps: [TranslateService], multi: true },
		{ provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, deps: [TranslateService], multi: true }
	]
});
