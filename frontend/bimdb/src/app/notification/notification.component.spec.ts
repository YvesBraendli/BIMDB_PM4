import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { NotificationService } from '../core/services/notification.service';
import { NotificationComponent } from './notification.component';

describe('NotificationComponent', () => {
	let component: NotificationComponent;
	let fixture: ComponentFixture<NotificationComponent>;
	let notificationService: NotificationService;
	let router: Router;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				NotificationComponent,
				RouterTestingModule.withRoutes([
					{ path: 'test', component: NotificationComponent }
				]),
				TranslateTestingModule.withTranslations({})]
		})
			.compileComponents();

		notificationService = TestBed.inject(NotificationService);
		router = TestBed.inject(Router);
		fixture = TestBed.createComponent(NotificationComponent);
		component = fixture.componentInstance;
		router.initialNavigation();
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should clear notification', () => {
		notificationService.info('it works');
		expect(component.notifications.length).toBe(1);
		component.clearNotification(0);
		expect(component.notifications.length).toBe(0);
	});

	it('should clear messages on redirect', fakeAsync(() => {
		notificationService.info('it works');
		expect(component.notifications.length).toBe(1);
		router.navigate(['test']);
		tick();
		expect(component.notifications.length).toBe(0);
	}));

});
