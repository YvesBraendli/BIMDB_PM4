import { HTTP_INTERCEPTORS, HttpBackend, HttpClient, HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, ErrorHandler, enableProdMode, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { Routes, provideRouter, withHashLocation } from '@angular/router';
import { MissingTranslationHandler, TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { Observable } from 'rxjs';
import { AppComponent } from './app/app.component';
import { HeaderInterceptor } from './app/core/interceptors/header.interceptor';
import { DiscoverComponent } from './app/discover/discover.component';
import { NotFoundComponent } from './app/not-found/not-found.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { StorageKeys } from './app/core/constants/storage-keys';
import { GlobalErrorHandler } from './app/core/handlers/error-handler';
import { BimdbMissingTranslationHandler } from './app/core/handlers/missing-translation.handler';
import { ErrorInterceptor } from './app/core/interceptors/error.interceptor';
import { NotificationService } from './app/core/services/notification.service';
import { MovieDetailComponent, PARAM_MOVIE_ID } from './app/movie-detail/movie-detail.component';
import { PARAM_TV_SHOW_ID, TvShowDetailComponent } from './app/tv-show-detail/tv-show-detail.component';
import { TvShowListComponent } from './app/tv-show-list/tv-show-list.component';
import { environment } from './environments/environment';

if (environment.production) {
	enableProdMode();
}
const routes: Routes = [
	{ path: '', redirectTo: 'discover', pathMatch: 'full' },
	{ path: 'discover', component: DiscoverComponent },
	// TODO add movies entrypoint in backend and navigate to MovieListComponent
	{ path: 'movies', component: DiscoverComponent },
	{ path: `movie/:${PARAM_MOVIE_ID}`, component: MovieDetailComponent },
	{ path: 'tv-shows', component: TvShowListComponent },
	{ path: `tv-show/:${PARAM_TV_SHOW_ID}`, component: TvShowDetailComponent },
	{ path: '**', component: NotFoundComponent }
];

function HttpLoaderFactory(httpHandler: HttpBackend): TranslateHttpLoader {
	return new TranslateHttpLoader(new HttpClient(httpHandler));
}

function initializeApp(translateService: TranslateService) {
	return (): Observable<void> => {
		return new Observable(observer => {
			const language = localStorage.getItem(StorageKeys.Language) ?? 'en';
			translateService.setDefaultLang('en');
			translateService.use(language);
			observer.next();
			observer.complete();
		});
	};
}

bootstrapApplication(AppComponent, {
	providers: [
		provideRouter(routes, withHashLocation()),
		importProvidersFrom(HttpClientModule, BrowserAnimationsModule, TranslateModule.forRoot({
			defaultLanguage: 'en',
			loader: {
				provide: TranslateLoader,
				useFactory: HttpLoaderFactory,
				deps: [HttpBackend]
			},
			missingTranslationHandler: {
				provide: MissingTranslationHandler,
				useClass: BimdbMissingTranslationHandler
			}
		})),
		{ provide: APP_INITIALIZER, useFactory: initializeApp, deps: [TranslateService], multi: true },
		{ provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, deps: [TranslateService], multi: true },
		{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, deps: [NotificationService], multi: true },
		{ provide: ErrorHandler, useClass: GlobalErrorHandler }
	]
});
