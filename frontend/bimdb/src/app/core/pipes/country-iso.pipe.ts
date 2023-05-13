import { Pipe, PipeTransform } from '@angular/core';
import { ConfigService } from '../services/config.service';

export const NO_COUNTRY_PLACEHOLDER = '-';

@Pipe({
	name: 'countryIso',
	standalone: true
})
export class CountryIsoPipe implements PipeTransform {

	public constructor(private configService: ConfigService){

	}

	public transform(isoCode: string | null | undefined): string {
		const country = this.configService.getCountries().find(country => country.iso === isoCode);
		if (country?.englishName && country.englishName !== '') {
			return country.englishName;
		}
		return isoCode && isoCode !== '' ? isoCode : NO_COUNTRY_PLACEHOLDER;
	}

}
