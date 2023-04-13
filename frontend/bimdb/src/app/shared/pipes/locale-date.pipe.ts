import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { DateFormats } from '../models/date-formats';

@Pipe({
	name: 'localeDate',
	standalone: true
})
export class LocaleDatePipe implements PipeTransform {

	public constructor(private datePipe: DatePipe, private translateService: TranslateService) { }

	public transform(value: string | number | Date | undefined): string | null {
		return this.datePipe.transform(value, this.getDateFormat());
	}

	private getDateFormat(): string {
		const lang = this.translateService.currentLang;
		switch (lang) {
		case 'en':
			return DateFormats.EN;
		case 'de':
			return DateFormats.DE;
		default:
			return DateFormats.EN;
		}
	}

}
