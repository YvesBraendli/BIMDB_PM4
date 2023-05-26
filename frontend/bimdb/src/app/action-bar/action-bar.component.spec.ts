import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HarnessLoader } from '@angular/cdk/testing';
import { TestbedHarnessEnvironment } from '@angular/cdk/testing/testbed';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatSnackBarHarness } from '@angular/material/snack-bar/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { KeycloakAngularModule } from 'keycloak-angular';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of } from 'rxjs';
import { WindowService } from '../core/services/window.service';
import { MediaType } from '../generated/contract';
import { ActionBarComponent } from './action-bar.component';
import { ActionBarService } from './action-bar.service';
import { SimpleChanges } from '@angular/core';

describe('ActionBarComponent', () => {
	let component: ActionBarComponent;
	let fixture: ComponentFixture<ActionBarComponent>;
	let actionBarService: ActionBarService;
	let windowService: WindowService;
	let loader: HarnessLoader;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				ActionBarComponent,
				HttpClientTestingModule,
				TranslateTestingModule.withTranslations({}),
				KeycloakAngularModule,
				BrowserAnimationsModule
			]
		})
			.compileComponents();
		actionBarService = TestBed.inject(ActionBarService);
		windowService = TestBed.inject(WindowService);
		fixture = TestBed.createComponent(ActionBarComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
		loader = TestbedHarnessEnvironment.documentRootLoader(fixture);
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should check favorite status', () => {
		const favoriteStatusSpy = spyOn(actionBarService, 'checkFavoriteStatus').and.returnValue(of({ isFavorite: true }));
		component.isLoggedIn = true;
		component.id = 1;
		component.mediaType = MediaType.Movie;
		component.ngOnChanges({} as SimpleChanges);
		expect(favoriteStatusSpy).toHaveBeenCalledOnceWith(1, MediaType.Movie);
		expect(component.isFavorite).toBeTrue();
	});

	it('should add to favorites', () => {
		const addToFavoritesSpy = spyOn(actionBarService, 'addFavorite').and.returnValue(of({ isFavorite: true }));
		component.isLoggedIn = true;
		component.id = 2;
		component.mediaType = MediaType.TvShow;
		component.addToFavorites();
		expect(addToFavoritesSpy).toHaveBeenCalledOnceWith(2, MediaType.TvShow);
		expect(component.isFavorite).toBeTrue();
	});

	it('should remove from favorites', () => {
		const removeFromFavoritesSpy = spyOn(actionBarService, 'removeFavorite').and.returnValue(of({ isFavorite: false }));
		component.isLoggedIn = true;
		component.id = 3;
		component.mediaType = MediaType.Person;
		component.removeFromFavorites();
		expect(removeFromFavoritesSpy).toHaveBeenCalledOnceWith(3, MediaType.Person);
		expect(component.isFavorite).toBeFalse();
	});

	it('should copy to clipboard', async () => {
		const locationSpy = spyOnProperty(windowService, 'location').and.returnValue({ href: 'myUrl' } as Location);
		spyOn(windowService.navigator.clipboard, 'writeText').and.callFake(() => new Promise((resolve) => resolve()));
		component.copyToClipboard();
		expect(locationSpy).toHaveBeenCalled();
		const snackBar = await loader.getHarness(MatSnackBarHarness);
		expect(await snackBar.getMessage()).toBe('linkCopied');
	});

	it('should fail to clipboard', async () => {
		const locationSpy = spyOnProperty(windowService, 'location').and.returnValue({ href: 'myUrl' } as Location);
		spyOn(windowService.navigator.clipboard, 'writeText').and.callFake(() => new Promise((_resolve, reject) => reject()));
		component.copyToClipboard();
		expect(locationSpy).toHaveBeenCalled();
	});

});
