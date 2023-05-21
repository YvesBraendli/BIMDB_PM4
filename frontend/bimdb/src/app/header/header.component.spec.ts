import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { WindowService } from '../core/services/window.service';

import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { HeaderComponent } from './header.component';
import { HeaderService } from './header.service';

describe('HeaderComponent', () => {
	let component: HeaderComponent;
	let fixture: ComponentFixture<HeaderComponent>;
	let reloadSpy: jasmine.Spy;
	let focusSearchSpy: jasmine.Spy;
	let loginSpy: jasmine.Spy;
	let logoutSpy: jasmine.Spy;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				HeaderComponent,
				TranslateTestingModule.withTranslations({}),
				RouterTestingModule,
				KeycloakAngularModule
			]
		})
			.compileComponents();

		reloadSpy = spyOn(TestBed.inject(WindowService), 'reload').and.callFake(() => undefined);
		focusSearchSpy = spyOn(TestBed.inject(HeaderService), 'focusSearch');
		const keycloakService = TestBed.inject(KeycloakService);
		spyOn(keycloakService, 'isLoggedIn').and.resolveTo(true);
		spyOn(keycloakService, 'loadUserProfile').and.resolveTo({ username: 'user' });
		loginSpy = spyOn(keycloakService, 'login').and.resolveTo();
		logoutSpy = spyOn(keycloakService, 'logout').and.resolveTo();
		fixture = TestBed.createComponent(HeaderComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
		expect(component.isLoggedIn).toBeTrue();
	});

	it('should change language', () => {
		const element = fixture.nativeElement as HTMLElement;
		const select = element.querySelector('select') as HTMLSelectElement;
		select.value = 'de';
		select.dispatchEvent(new Event('change'));
		expect(component.language).toEqual('de');
		expect(reloadSpy).toHaveBeenCalled();
	});

	it('should call focus search', () => {
		component.focusSearch();
		expect(focusSearchSpy).toHaveBeenCalled();
	});

	it('should login', () => {
		component.login();
		expect(loginSpy).toHaveBeenCalled();
	});

	it('should logout', () => {
		component.logout();
		expect(logoutSpy).toHaveBeenCalled();
	});

	it('should toggle expanded', () => {
		expect(component.expanded).toBeFalse();
		component.toggleExpanded();
		expect(component.expanded).toBeTrue();
	});
});
