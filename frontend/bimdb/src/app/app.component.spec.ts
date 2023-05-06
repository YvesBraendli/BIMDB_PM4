import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { of, throwError } from 'rxjs';
import { AppComponent } from './app.component';
import { ConfigService } from './core/services/config.service';
import { NotificationService } from './core/services/notification.service';

describe('AppComponent', () => {
	let mockConfigService: ConfigService;
	let notificationService: NotificationService;
	let errorNotificationSpy: jasmine.Spy;
	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				AppComponent,
				TranslateTestingModule.withTranslations({}),
				HttpClientTestingModule,
				RouterTestingModule
			],
		}).compileComponents();
		mockConfigService = TestBed.inject(ConfigService);
		notificationService = TestBed.inject(NotificationService);
		errorNotificationSpy = spyOn(notificationService, 'error');
	});

	it('should create the app', () => {
		spyOn(mockConfigService, 'loadAppConfig').and.returnValue(of(undefined));
		const fixture = TestBed.createComponent(AppComponent);
		const app = fixture.componentInstance;
		expect(app).toBeTruthy();
		expect(app.configInitialized).toBeTrue();
	});

	it('should fail to load config', () => {
		spyOn(mockConfigService, 'loadAppConfig').and.returnValue(throwError(() => undefined));
		const fixture = TestBed.createComponent(AppComponent);
		const app = fixture.componentInstance;
		expect(app).toBeTruthy();
		expect(errorNotificationSpy).toHaveBeenCalledWith('error.config');
		expect(app.configInitialized).toBeFalse();
	});
});
