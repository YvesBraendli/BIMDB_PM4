import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';
import { RouterTestingModule } from '@angular/router/testing';
import { WindowService } from '../shared/services/window.service';

import { HeaderComponent } from './header.component';

describe('HeaderComponent', () => {
	let component: HeaderComponent;
	let fixture: ComponentFixture<HeaderComponent>;
	let reloadSpy: jasmine.Spy;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [
				HeaderComponent,
				TranslateTestingModule.withTranslations({}),
				RouterTestingModule
			]
		})
			.compileComponents();

		reloadSpy = spyOn(TestBed.inject(WindowService), 'reload').and.callFake(() => undefined);
		fixture = TestBed.createComponent(HeaderComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should change language', () => {
		const element = fixture.nativeElement as HTMLElement;
		const select = element.querySelector('select') as HTMLSelectElement;
		select.value = 'de';
		select.dispatchEvent(new Event('change'));
		expect(component.language).toEqual('de');
		expect(reloadSpy).toHaveBeenCalled();
	});

});
