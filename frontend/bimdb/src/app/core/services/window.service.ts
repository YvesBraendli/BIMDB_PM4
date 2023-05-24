import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class WindowService {
	public reload(): void {
		this.location.reload();
	}

	public get location(): Location {
		return window.location;
	}

	public get navigator(): Navigator {
		return navigator;
	}

}
