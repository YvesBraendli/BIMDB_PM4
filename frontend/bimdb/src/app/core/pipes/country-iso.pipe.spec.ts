import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ConfigService } from '../services/config.service';
import { CountryIsoPipe, NO_COUNTRY_PLACEHOLDER } from './country-iso.pipe';

describe('CountryPipePipe', () => {
	let pipe: CountryIsoPipe;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [HttpClientTestingModule],
			providers: [CountryIsoPipe]
		})
			.compileComponents();

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getCountries').and.returnValue([
			{
				iso_3166_1: 'CH',
				english_name: 'Switzerland'
			},
			{
				iso_3166_1: 'EN',
				english_name: ''
			}
		]);

		pipe = TestBed.inject(CountryIsoPipe);
	});

	it('create an instance', () => {
		expect(pipe).toBeTruthy();
	});

	it('should return english name', () => {
		expect(pipe.transform('CH')).toBe('Switzerland');
	});

	it('should return input', () => {
		expect(pipe.transform('abc')).toBe('abc');
	});

	it('should return empty string', () => {
		expect(pipe.transform('')).toBe(NO_COUNTRY_PLACEHOLDER);
		expect(pipe.transform(null)).toBe(NO_COUNTRY_PLACEHOLDER);
		expect(pipe.transform(undefined)).toBe(NO_COUNTRY_PLACEHOLDER);
	});
});
