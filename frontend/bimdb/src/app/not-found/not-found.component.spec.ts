import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotFoundComponent } from './not-found.component';
import { TranslateTestingModule } from 'ngx-translate-testing';

describe('NotFoundComponent', () => {
	let component: NotFoundComponent;
	let fixture: ComponentFixture<NotFoundComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [NotFoundComponent, TranslateTestingModule.withTranslations({})]
		})
			.compileComponents();

		fixture = TestBed.createComponent(NotFoundComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
