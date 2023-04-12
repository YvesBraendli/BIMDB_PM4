import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { PaginatorComponent } from '../paginator/paginator.component';
import { DiscoverService } from './discover.service';
import { RouterModule } from '@angular/router';

@Component({
	standalone: true,
	imports: [CommonModule, PaginatorComponent, RouterModule],
	selector: 'app-discover',
	templateUrl: './discover.component.html',
	styleUrls: ['./discover.component.scss']
})
export class DiscoverComponent {
	public page = 1;
	public totalPages = 10;
	public movies: Movie[] = [];

	public constructor(private discoverService: DiscoverService) {
		this.discover();
	}

	public discover(): void {
		this.discoverService.discover(this.page).subscribe({
			next: result => {
				this.movies = result.movies;
				this.totalPages = result.totalPages;
			}
		});
	}

	public onPageChange(page: number): void {
		this.page = page;
		this.discover();
	}
}

export class Movie {
	public id?: number;
	public name?: string;
	public date?: Date;
	public imgUrl?: string;
}
