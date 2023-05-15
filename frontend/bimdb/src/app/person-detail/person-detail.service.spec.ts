import { PersonDetailService } from './person-detail.service';
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Person } from '../generated/contract';
import { Observable } from 'rxjs';

describe('PersonDetailService', () => {
	let service: PersonDetailService;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [
				HttpClientTestingModule
			]
		});
		service = TestBed.inject(PersonDetailService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});

	describe('when getPerson is called', () => {
		let result: Observable<Person>;

		beforeEach(() => {
			result = service.getPerson(100);
		});

		it('should return an observable', () => {
			expect(result).toBeTruthy();
		});
	});
});
