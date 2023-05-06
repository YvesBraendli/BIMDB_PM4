import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ImageSize, ImageType, ImgSizeConfig, TmdbImgConfig } from '../core/models/tmdb-img-config';
import { ConfigService } from '../core/services/config.service';
import { TmdbImgSrcPipe } from './tmdb-img-src.pipe';

describe('TmdbImageSrcPipe', () => {
	let pipe: TmdbImgSrcPipe;
	let mockConfigService: ConfigService;
	const baseUrl = 'https://tmdb-images.com/';
	const original = 'original';
	// backdrop
	const smallBackdrop = 'w300';
	const mediumBackdrop = 'w780';
	const largeBackdrop = 'w1280';
	// logo
	const smallLogo = 'w45';
	const mediumLogo = 'w185';
	const largeLogo = 'w500';
	// poster
	const smallPoster = 'w92';
	const mediumPoster = 'w342';
	const largePoster = 'w780';
	// profile
	const smallProfile = 'w45';
	const mediumProfile = 'w185';
	const largeProfile = 'h632';
	// still
	const smallStill = 'w92';
	const mediumStill = 'w185';
	const largeStill = 'w300';
	const src = '/img.jpg';
	beforeEach(async () => {
		await TestBed.configureTestingModule({
			imports: [HttpClientTestingModule],
			providers: [TmdbImgSrcPipe]
		})
			.compileComponents();

		mockConfigService = TestBed.inject(ConfigService);
		spyOn(mockConfigService, 'getTmbdImgConfig').and.returnValue(new TmdbImgConfig({
			baseUrl,
			backdropSizes: new ImgSizeConfig({ small: smallBackdrop, medium: mediumBackdrop, large: largeBackdrop, originial: original }),
			logoSizes: new ImgSizeConfig({ small: smallLogo, medium: mediumLogo, large: largeLogo, originial: original }),
			posterSizes: new ImgSizeConfig({ small: smallPoster, medium: mediumPoster, large: largePoster, originial: original }),
			profileSizes: new ImgSizeConfig({ small: smallProfile, medium: mediumProfile, large: largeProfile, originial: original }),
			stillSizes: new ImgSizeConfig({ small: smallStill, medium: mediumStill, large: largeStill, originial: original }),
		} as TmdbImgConfig));

		pipe = TestBed.inject(TmdbImgSrcPipe);
		spyOn(mockConfigService, 'getImageBaseUrl').and.returnValue(baseUrl);
	});

	it('create an instance', () => {
		expect(pipe).toBeTruthy();
	});

	it('should return custom size', () => {
		let url = pipe.transform(src, ImageType.Backdrop, ImageSize.Custom, 'w900');
		expect(url).toBe(`${baseUrl}w900${src}`);

		url = pipe.transform(src, ImageType.Backdrop, ImageSize.Small, 'w900');
		expect(url).toBe(`${baseUrl}w900${src}`);

		url = pipe.transform(src, ImageType.Backdrop, ImageSize.Custom);
		expect(url).toBe(`${baseUrl}${original}${src}`);
	});

	describe('Backdrop', () => {
		it('should return small image src', () => {
			const url = pipe.transform(src, ImageType.Backdrop, ImageSize.Small);
			expect(url).toBe(`${baseUrl}${smallBackdrop}${src}`);
		});

		it('should return medium image src', () => {
			const url = pipe.transform(src, ImageType.Backdrop, ImageSize.Medium);
			expect(url).toBe(`${baseUrl}${mediumBackdrop}${src}`);
		});

		it('should return large image src', () => {
			const url = pipe.transform(src, ImageType.Backdrop, ImageSize.Large);
			expect(url).toBe(`${baseUrl}${largeBackdrop}${src}`);
		});

		it('should return original image src', () => {
			const url = pipe.transform(src, ImageType.Backdrop, ImageSize.Original);
			expect(url).toBe(`${baseUrl}${original}${src}`);
		});
	});

	describe('Logo', () => {
		it('should return small image src', () => {
			const url = pipe.transform(src, ImageType.Logo, ImageSize.Small);
			expect(url).toBe(`${baseUrl}${smallLogo}${src}`);
		});

		it('should return medium image src', () => {
			const url = pipe.transform(src, ImageType.Logo, ImageSize.Medium);
			expect(url).toBe(`${baseUrl}${mediumLogo}${src}`);
		});

		it('should return large image src', () => {
			const url = pipe.transform(src, ImageType.Logo, ImageSize.Large);
			expect(url).toBe(`${baseUrl}${largeLogo}${src}`);
		});

		it('should return original image src', () => {
			const url = pipe.transform(src, ImageType.Logo, ImageSize.Original);
			expect(url).toBe(`${baseUrl}${original}${src}`);
		});
	});

	describe('Poster', () => {
		it('should return small image src', () => {
			const url = pipe.transform(src, ImageType.Poster, ImageSize.Small);
			expect(url).toBe(`${baseUrl}${smallPoster}${src}`);
		});

		it('should return medium image src', () => {
			const url = pipe.transform(src, ImageType.Poster, ImageSize.Medium);
			expect(url).toBe(`${baseUrl}${mediumPoster}${src}`);
		});

		it('should return large image src', () => {
			const url = pipe.transform(src, ImageType.Poster, ImageSize.Large);
			expect(url).toBe(`${baseUrl}${largePoster}${src}`);
		});

		it('should return original image src', () => {
			const url = pipe.transform(src, ImageType.Poster, ImageSize.Original);
			expect(url).toBe(`${baseUrl}${original}${src}`);
		});
	});

	describe('Profile', () => {
		it('should return small image src', () => {
			const url = pipe.transform(src, ImageType.Profile, ImageSize.Small);
			expect(url).toBe(`${baseUrl}${smallProfile}${src}`);
		});

		it('should return medium image src', () => {
			const url = pipe.transform(src, ImageType.Profile, ImageSize.Medium);
			expect(url).toBe(`${baseUrl}${mediumProfile}${src}`);
		});

		it('should return large image src', () => {
			const url = pipe.transform(src, ImageType.Profile, ImageSize.Large);
			expect(url).toBe(`${baseUrl}${largeProfile}${src}`);
		});

		it('should return original image src', () => {
			const url = pipe.transform(src, ImageType.Profile, ImageSize.Original);
			expect(url).toBe(`${baseUrl}${original}${src}`);
		});
	});

	describe('Still', () => {
		it('should return small image src', () => {
			const url = pipe.transform(src, ImageType.Still, ImageSize.Small);
			expect(url).toBe(`${baseUrl}${smallStill}${src}`);
		});

		it('should return medium image src', () => {
			const url = pipe.transform(src, ImageType.Still, ImageSize.Medium);
			expect(url).toBe(`${baseUrl}${mediumStill}${src}`);
		});

		it('should return large image src', () => {
			const url = pipe.transform(src, ImageType.Still, ImageSize.Large);
			expect(url).toBe(`${baseUrl}${largeStill}${src}`);
		});

		it('should return original image src', () => {
			const url = pipe.transform(src, ImageType.Still, ImageSize.Original);
			expect(url).toBe(`${baseUrl}${original}${src}`);
		});
	});

});
