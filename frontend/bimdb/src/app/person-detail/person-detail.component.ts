import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { PersonDetailService } from './person-detail.service';
import { Person } from '../generated/contract';
import { TmdbImgComponent } from '../tmdb-img/tmdb-img.component';
import { LocaleDatePipe } from '../core/pipes/locale-date.pipe';
import { CreditListComponent } from '../credit-list/credit-list.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MediaListComponent } from '../media-list/media-list.component';
import { TranslateModule } from '@ngx-translate/core';
import { WatchProviderListComponent } from '../watch-provider-list/watch-provider-list.component';
import { ActionBarComponent } from '../action-bar/action-bar.component';

export const PARAM_PERSON_ID = 'person-id';

@Component({
	selector: 'app-person-detail',
	standalone: true,
	providers: [DatePipe],
	imports: [CommonModule, TmdbImgComponent, LocaleDatePipe, CreditListComponent, MatTabsModule, MediaListComponent, TranslateModule, WatchProviderListComponent, ActionBarComponent],
	templateUrl: './person-detail.component.html',
	styleUrls: ['./person-detail.component.scss']
})
export class PersonDetailComponent implements OnInit {
	public person?: Person;

	public constructor(private route: ActivatedRoute, private personDetailService: PersonDetailService) {
	}

	public ngOnInit(): void {
		this.route.params.subscribe(params => {
			const id = params[PARAM_PERSON_ID];
			if (id) {
				this.personDetailService
					.getPerson(id)
					.subscribe(person => this.person = person);
			}
		});
	}
}
