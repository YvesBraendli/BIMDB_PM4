import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { MediaListComponent } from './media-list.component';

describe('MediaListComponent', () => {
	let component: MediaListComponent;
	let fixture: ComponentFixture<MediaListComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [ MediaListComponent, TranslateTestingModule.withTranslations({}) ]
		})
			.compileComponents();

		fixture = TestBed.createComponent(MediaListComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should emit page change', () => {
		const spy = spyOn(component.pageChange, 'emit');
		component.onPageChange(1);
		expect(spy).toHaveBeenCalledWith(1);
	});

});
