import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class HeaderService {

	public focusSearch$ = new Subject<void>();

	public focusSearch(): void {
		this.focusSearch$.next();
	}

}
