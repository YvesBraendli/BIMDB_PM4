import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';

@Component({
	selector: 'app-paginator',
	standalone: true,
	imports: [CommonModule, TranslateModule],
	templateUrl: './paginator.component.html',
	styleUrls: ['./paginator.component.scss']
})
export class PaginatorComponent {
	@Input()
	public page = 1;
	@Input()
	public totalPages = 10;
	@Output()
	public pageChange = new EventEmitter<number>();

	public goToFirstPage(): void {
		this.pageChange.emit(this.page = 1);
	}

	public goToLastPage(): void {
		this.page = this.totalPages;
		this.pageChange.emit(this.page);
	}

	public goToPreviousPage(): void {
		if (!this.firstPage) {
			this.pageChange.emit(--this.page);
		}
	}

	public goToNextPage(): void {
		if (!this.lastPage) {
			this.pageChange.emit(++this.page);
		}
	}

	public goToPage(event: Event): void {
		const page =  +(event.target as HTMLInputElement).value;
		if (page >= 1 && page <= this.totalPages) {
			this.pageChange.emit(this.page = page);
		}
	}

	public get firstPage(): boolean {
		return this.page === 1;
	}

	public get lastPage(): boolean {
		return this.page === this.totalPages;
	}
}
