import { Pipe, PipeTransform } from '@angular/core';
import { ConfigService } from '../services/config.service';

export const NO_LANGUAGE_PLACEHOLDER = '-';

@Pipe({
	name: 'languageIso',
	standalone: true
})
export class LanguageIsoPipe implements PipeTransform {

	public constructor(private configService: ConfigService) {

	}

	public transform(isoCode: string | null | undefined): string {
		const language = this.configService.getLanguages().find(language => language.iso_639_1 === isoCode);
		if (language?.name && language.name !== '') {
			return language.name;
		}
		if (language?.english_name && language.english_name !== '') {
			return language.english_name;
		}
		return isoCode && isoCode !== '' ? isoCode : NO_LANGUAGE_PLACEHOLDER;
	}
}
