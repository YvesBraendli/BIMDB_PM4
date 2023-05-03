import { DatePipe } from '@angular/common';
import { TestBed } from '@angular/core/testing';
import { TranslateService } from '@ngx-translate/core';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { LocaleDatePipe } from './locale-date.pipe';

describe('LocaleDatePipe', () => {
	let localeDatePipe: LocaleDatePipe;
	let translateService: TranslateService;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [TranslateTestingModule.withTranslations({})],
			providers: [DatePipe, LocaleDatePipe]
		})
			.compileComponents();
		localeDatePipe = TestBed.inject(LocaleDatePipe);
		translateService = TestBed.inject(TranslateService);
	});

	it('create an instance', () => {
		expect(localeDatePipe).toBeTruthy();
	});

	it('should return date in default format', () => {
		const date = new Date('2021-12-31');
		const localeDate = localeDatePipe.transform(date);
		expect(localeDate).toEqual('12/31/2021');
	});

	it('should return date in en format', () => {
		const date = new Date('2021-12-31');
		translateService.use('en');
		const localeDate = localeDatePipe.transform(date);
		expect(localeDate).toEqual('12/31/2021');
	});

	it('should return date in de format', () => {
		const date = new Date('2021-12-31');
		translateService.use('de');
		const localeDate = localeDatePipe.transform(date);
		expect(localeDate).toEqual('31.12.2021');
	});

});
