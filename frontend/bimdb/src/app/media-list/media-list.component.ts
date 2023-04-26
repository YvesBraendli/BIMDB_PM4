import { CommonModule, DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { RouterModule } from '@angular/router';
import { PaginatorComponent } from '../paginator/paginator.component';
import { LocaleDatePipe } from '../shared/pipes/locale-date.pipe';
import { ConfigService } from '../shared/services/config.service';
import { Media } from './media';


@Component({
	selector: 'app-media-list',
	standalone: true,
	imports: [CommonModule, PaginatorComponent, LocaleDatePipe, MatPaginatorModule, RouterModule],
	providers: [DatePipe],
	templateUrl: './media-list.component.html',
	styleUrls: ['./media-list.component.scss']
})
export class MediaListComponent {
	@Input() public page = 1;
	@Input() public totalPages = 10;
	@Input() public media: Media[] = [];
	@Output() public pageChange = new EventEmitter<number>();
	public posterBaseUrl?: string;

	public constructor(private configService: ConfigService) {
		this.configService.getImageBaseUrl().subscribe(baseUrl => this.posterBaseUrl = baseUrl);
	}


	public onPageChange(page: number): void {
		this.pageChange.emit(page);
	}
}
