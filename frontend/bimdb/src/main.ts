import { HTTP_INTERCEPTORS, HttpBackend, HttpClient, HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, enableProdMode, ErrorHandler, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes, withHashLocation } from '@angular/router';
import { MissingTranslationHandler, TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppComponent } from './app/app.component';
import { HeaderInterceptor } from './app/core/interceptors/header.interceptor';
import { DiscoverComponent } from './app/discover/discover.component';
import { NotFoundComponent } from './app/not-found/not-found.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import {
	DISCOVER_ROUTE,
	MOVIE_ROUTE,
	MOVIES_ROUTE,
	PEOPLE_ROUTE,
	PERSON_ROUTE,
	SEARCH_ROUTE,
	TV_SHOW_ROUTE,
	TV_SHOWS_ROUTE
} from './app/core/constants/routes';
import { StorageKeys } from './app/core/constants/storage-keys';
import { GlobalErrorHandler } from './app/core/handlers/error-handler';
import { BimdbMissingTranslationHandler } from './app/core/handlers/missing-translation.handler';
import { ErrorInterceptor } from './app/core/interceptors/error.interceptor';
import { ConfigService } from './app/core/services/config.service';
import { NotificationService } from './app/core/services/notification.service';
import { MovieDetailComponent, PARAM_MOVIE_ID } from './app/movie-detail/movie-detail.component';
import { DATA_TAB_INDEX, SearchComponent, SearchTabs } from './app/search/search.component';
import { PARAM_TV_SHOW_ID, TvShowDetailComponent } from './app/tv-show-detail/tv-show-detail.component';
import { TvShowListComponent } from './app/tv-show-list/tv-show-list.component';
import { environment } from './environments/environment';
import { PARAM_PERSON_ID, PersonDetailComponent } from './app/person-detail/person-detail.component';

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
	{
		path: `${SEARCH_ROUTE}/${MOVIES_ROUTE}`,
		component: SearchComponent,
		data: { [DATA_TAB_INDEX]: SearchTabs.MOVIES }
	},
	{
		path: `${SEARCH_ROUTE}/${TV_SHOWS_ROUTE}`,
		component: SearchComponent,
		data: { [DATA_TAB_INDEX]: SearchTabs.TV }
	},
	{
		path: `${SEARCH_ROUTE}/${PEOPLE_ROUTE}`,
		component: SearchComponent,
		data: { [DATA_TAB_INDEX]: SearchTabs.PEOPLE }
	},
	{ path: `${PERSON_ROUTE}/:${PARAM_PERSON_ID}`, component: PersonDetailComponent },
	{ path: '**', component: NotFoundComponent }
];

function HttpLoaderFactory(httpHandler: HttpBackend): TranslateHttpLoader {
	return new TranslateHttpLoader(new HttpClient(httpHandler));
}

function initializeApp(translateService: TranslateService, configService: ConfigService) {
	return (): Observable<void> => {
		const language = localStorage.getItem(StorageKeys.Language) ?? 'en';
		translateService.setDefaultLang('en');
		translateService.use(language);
		return configService.loadAppConfig(true);
	};
}

function initializeKeycloak(keycloak: KeycloakService) {
	return (): Promise<boolean> => {
		return keycloak.init({
			config: {
				url: environment.keycloackUrl,
				realm: 'master',
				clientId: 'bimdb'
			},
			initOptions: {
				onLoad: 'check-sso',
				silentCheckSsoRedirectUri:
					window.location.origin + '/assets/silent-check-sso.html'
			}
		});
	};
}

bootstrapApplication(AppComponent, {
	providers: [
		provideRouter(routes, withHashLocation()),
		importProvidersFrom(HttpClientModule, BrowserAnimationsModule, KeycloakAngularModule, TranslateModule.forRoot({
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
		{ provide: APP_INITIALIZER, useFactory: initializeApp, deps: [TranslateService, ConfigService], multi: true },
		{
			provide: APP_INITIALIZER,
			useFactory: initializeKeycloak,
			multi: true,
			deps: [KeycloakService]
		},
		{ provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, deps: [TranslateService], multi: true },
		{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, deps: [NotificationService], multi: true },
		{ provide: ErrorHandler, useClass: GlobalErrorHandler }
	]
}).catch(() => {
	// TODO: proper error handling in case application can't start
	const errorElement = document.createElement('div');
	errorElement.innerText = 'RIP BIMDB';
	document.body.appendChild(errorElement);
});
