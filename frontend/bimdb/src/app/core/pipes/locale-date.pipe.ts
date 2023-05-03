import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
	name: 'localeDate',
	standalone: true
})
export class LocaleDatePipe implements PipeTransform {
	private EN = 'MM/dd/yyyy';
	private DE = 'dd.MM.YYYY';
	public constructor(private datePipe: DatePipe, private translateService: TranslateService) { }

	public transform(value: string | number | Date | undefined): string | null {
		return this.datePipe.transform(value, this.getDateFormat());
	}

	private getDateFormat(): string {
		const lang = this.translateService.currentLang;
		switch (lang) {
			case 'en':
				return this.EN;
			case 'de':
				return this.DE;
			default:
				return this.EN;
		}
	}

}
