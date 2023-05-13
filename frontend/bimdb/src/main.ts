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
import { DISCOVER_ROUTE, MOVIES_ROUTE, MOVIE_ROUTE, PEOPLE_ROUTE, SEARCH_ROUTE, TV_SHOWS_ROUTE, TV_SHOW_ROUTE } from './app/core/constants/routes';
import { StorageKeys } from './app/core/constants/storage-keys';
import { GlobalErrorHandler } from './app/core/handlers/error-handler';
import { BimdbMissingTranslationHandler } from './app/core/handlers/missing-translation.handler';
import { ErrorInterceptor } from './app/core/interceptors/error.interceptor';
import { NotificationService } from './app/core/services/notification.service';
import { MovieDetailComponent, PARAM_MOVIE_ID } from './app/movie-detail/movie-detail.component';
import { DATA_TAB_INDEX, SearchComponent, SearchTabs } from './app/search/search.component';
import { PARAM_TV_SHOW_ID, TvShowDetailComponent } from './app/tv-show-detail/tv-show-detail.component';
import { TvShowListComponent } from './app/tv-show-list/tv-show-list.component';
import { environment } from './environments/environment';

if (environment.production) {
	enableProdMode();
}
const routes: Routes = [
	{ path: '', redirectTo: DISCOVER_ROUTE, pathMatch: 'full' },
	{ path: DISCOVER_ROUTE, component: DiscoverComponent },
	// TODO add movies entrypoint in backend and navigate to MovieListComponent
	{ path: MOVIES_ROUTE, component: DiscoverComponent },
	{ path: `${MOVIE_ROUTE}/:${PARAM_MOVIE_ID}`, component: MovieDetailComponent },
	{ path: TV_SHOWS_ROUTE, component: TvShowListComponent },
	{ path: `${TV_SHOW_ROUTE}/:${PARAM_TV_SHOW_ID}`, component: TvShowDetailComponent },
	{ path: SEARCH_ROUTE, component: SearchComponent, data: { [DATA_TAB_INDEX]: SearchTabs.ALL } },
	{ path: `${SEARCH_ROUTE}/${MOVIES_ROUTE}`, component: SearchComponent, data: { [DATA_TAB_INDEX]: SearchTabs.MOVIES } },
	{ path: `${SEARCH_ROUTE}/${TV_SHOWS_ROUTE}`, component: SearchComponent, data: { [DATA_TAB_INDEX]: SearchTabs.TV } },
	{ path: `${SEARCH_ROUTE}/${PEOPLE_ROUTE}`, component: SearchComponent, data: { [DATA_TAB_INDEX]: SearchTabs.PEOPLE } },
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
