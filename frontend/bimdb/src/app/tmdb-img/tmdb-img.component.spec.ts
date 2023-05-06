import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { TmdbImgComponent } from './tmdb-img.component';

describe('TmdbImageComponent', () => {
	let component: TmdbImgComponent;
	let fixture: ComponentFixture<TmdbImgComponent>;
	let mockConfigService: ConfigService;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [TmdbImgComponent, HttpClientTestingModule]
		})
			.compileComponents();


		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

		fixture = TestBed.createComponent(TmdbImgComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
