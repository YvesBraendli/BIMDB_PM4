import { MissingTranslationHandler, MissingTranslationHandlerParams } from '@ngx-translate/core';

export class BimdbMissingTranslationHandler implements MissingTranslationHandler {
	public handle(params: MissingTranslationHandlerParams): string {
		if (params.key.startsWith('error.http.')) {
			return params.translateService.instant('error.unknown');
		}
		return params.key;
	}
}
