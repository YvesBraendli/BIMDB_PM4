import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonDetailComponent } from './person-detail.component';
import { of, Subject } from 'rxjs';
import { ActivatedRoute, Params } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { PersonDetailService } from './person-detail.service';
import { ConfigService } from '../core/services/config.service';
import { TmdbImgConfig } from '../core/models/tmdb-img-config';

describe('PersonDetailComponent', () => {
	let params: Subject<Params>;
	let mockPersonDetailService: PersonDetailService;
	let mockConfigService: ConfigService;
	let component: PersonDetailComponent;
	let fixture: ComponentFixture<PersonDetailComponent>;

	beforeEach(async () => {
		params = new Subject<Params>();

		await TestBed.configureTestingModule({
			imports: [
				PersonDetailComponent,
				HttpClientTestingModule,
				TranslateTestingModule.withTranslations({})
			],
			providers: [
				{
					provide: ActivatedRoute,
					useValue: {
						params
					}
				}
			]
		}).compileComponents();

		mockPersonDetailService = TestBed.inject(PersonDetailService);
		mockPersonDetailService.getPerson = jasmine.createSpy().and.returnValue(of({
			name: 'Max Muster'
		}));

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue('');
		spyOn(mockConfigService, 'getCountries').and.returnValue([]);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig());

		fixture = TestBed.createComponent(PersonDetailComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	describe('When a person id is passed', () => {
		const personId = 100;

		beforeEach((done) => {
			params.next({ 'person-id': personId });
			done();
		});

		it(`should call getPerson with ${personId}`, () => {
			expect(mockPersonDetailService.getPerson).toHaveBeenCalledOnceWith(personId);
		});
	});

	describe('When no person id is passed', () => {

		beforeEach((done) => {
			params.next({});
			done();
		});

		it('should not call getPerson', () => {
			expect(mockPersonDetailService.getPerson).not.toHaveBeenCalled();
		});
	});
});
