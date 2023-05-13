import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslateTestingModule } from 'ngx-translate-testing';

import { Media, MediaType } from '../generated/contract';
import { MediaListComponent } from './media-list.component';

describe('MediaListComponent', () => {
	let component: MediaListComponent;
	let fixture: ComponentFixture<MediaListComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [MediaListComponent, TranslateTestingModule.withTranslations({})]
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

	it('should return the correct link when getMediaLink is called with a movie', () => {
		const media = {
			id: 1,
			mediaType: MediaType.Movie
		} as Media;
		const link = component.getMediaLink(media);
		expect(link).toEqual('/movie/1');
	});

	it('should return the correct link when getMediaLink is called with a tv-show', () => {
		const media = {
			id: 1,
			mediaType: MediaType.TvShow
		} as Media;
		const link = component.getMediaLink(media);
		expect(link).toEqual('/tv-show/1');
	});

	it('should return an empty link when getMediaLink is called with an unknown media type', () => {
		const media = {
			id: 1
		} as Media;
		const link = component.getMediaLink(media);
		expect(link).toEqual('');
	});

	it('should return max pages value', () => {
		component.totalPages = 700;
		expect(component.maxPages).toBe(500);
		component.totalPages = 125;
		expect(component.maxPages).toBe(125);
	});

});
