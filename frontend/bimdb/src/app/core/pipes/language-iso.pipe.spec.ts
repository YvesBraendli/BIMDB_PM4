import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ConfigService } from '../services/config.service';
import { LanguageIsoPipe, NO_LANGUAGE_PLACEHOLDER } from './language-iso.pipe';

describe('LanguageIsoPipe', () => {
	let pipe: LanguageIsoPipe;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [HttpClientTestingModule],
			providers: [LanguageIsoPipe]
		})
			.compileComponents();

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getLanguages').and.returnValue([
			{
				iso: 'en',
				englishName: 'English',
				name: 'English'
			},
			{
				iso: 'aa',
				englishName: 'Afar',
				name: ''
			},
		]);

		pipe = TestBed.inject(LanguageIsoPipe);
	});

	it('create an instance', () => {
		expect(pipe).toBeTruthy();
	});

	it('should return name', () => {
		expect(pipe.transform('en')).toBe('English');
	});

	it('should return english name', () => {
		expect(pipe.transform('aa')).toBe('Afar');
	});

	it('should return input', () => {
		expect(pipe.transform('abc')).toBe('abc');
	});

	it('should return empty string', () => {
		expect(pipe.transform('')).toBe(NO_LANGUAGE_PLACEHOLDER);
		expect(pipe.transform(null)).toBe(NO_LANGUAGE_PLACEHOLDER);
		expect(pipe.transform(undefined)).toBe(NO_LANGUAGE_PLACEHOLDER);
	});

});
