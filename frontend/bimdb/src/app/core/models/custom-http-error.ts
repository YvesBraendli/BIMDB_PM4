import { HttpErrorResponse } from '@angular/common/http';

export class CustomHttpErrorResponse extends HttpErrorResponse {
	public alreadyHandled: boolean;
	public constructor(error: HttpErrorResponse, alreadyHandled: boolean) {
		super({ error });
		this.alreadyHandled = alreadyHandled;
	}
}
