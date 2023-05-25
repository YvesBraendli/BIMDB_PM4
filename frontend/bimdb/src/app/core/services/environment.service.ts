import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Environment } from '../models/environment';
import { WindowService } from './window.service';

@Injectable({
	providedIn: 'root'
})
export class EnvironmentService {
	private environmentConfig?: Environment;

	public constructor(private httpClient: HttpClient, private windowService: WindowService) { }

	public getConfig(): Environment {
		if (!this.environmentConfig) {
			this.loadEnvironmentConfig();
		}
		return this.environmentConfig ?? {} as Environment;
	}

	public loadEnvironmentConfig(): Observable<void> {
		return new Observable(subscriber => {
			const env = this.getEnvironment();
			this.httpClient.get<Environment>(`/assets/environments/environment${env}.json`).subscribe({
				next: res => {
					this.environmentConfig = res;
					subscriber.next();
					subscriber.complete();
				}
			});
		});
	}

	private getEnvironment(): string {
		const base = this.windowService.location.host.split('.')[0]?.replace('bimdb-', '');
		if (base === 'stage' || base === 'prod') {
			return `.${base}`;
		}
		return '';
	}
}
