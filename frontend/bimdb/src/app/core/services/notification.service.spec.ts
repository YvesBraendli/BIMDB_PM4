import { TestBed } from '@angular/core/testing';

import { NotificationService } from './notification.service';

describe('NotificationService', () => {
	let service: NotificationService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(NotificationService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	it('should create success notification', () => {
		service.notificationSubject$.subscribe(notification => {
			expect(notification.type).toBe('success');
			expect(notification.icon).toBe('done');
			expect(notification.message).toBe('success!');
		});
		service.success('success!');
	});

	it('should create info notification', () => {
		service.notificationSubject$.subscribe(notification => {
			expect(notification.type).toBe('info');
			expect(notification.icon).toBe('info');
			expect(notification.message).toBe('info!');
		});
		service.info('info!');
	});

	it('should create warning notification', () => {
		service.notificationSubject$.subscribe(notification => {
			expect(notification.type).toBe('warning');
			expect(notification.icon).toBe('warning');
			expect(notification.message).toBe('warning!');
		});
		service.warning('warning!');
	});

	it('should create error notification', () => {
		service.notificationSubject$.subscribe(notification => {
			expect(notification.type).toBe('error');
			expect(notification.icon).toBe('error');
			expect(notification.message).toBe('error!');
		});
		service.error('error!');
	});

});
