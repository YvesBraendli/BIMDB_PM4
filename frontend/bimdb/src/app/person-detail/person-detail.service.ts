import { Injectable } from '@angular/core';
import { HttpService } from '../core/services/http.service';
import { Observable } from 'rxjs';
import { Person } from '../generated/contract';

@Injectable({
	providedIn: 'root'
})
export class PersonDetailService {

	public constructor(private httpService: HttpService) {
	}

	public getPerson(id: number): Observable<Person> {
		return this.httpService.get<Person>(`/person/${id}`);
	}
}
