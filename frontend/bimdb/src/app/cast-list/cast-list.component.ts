import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export class Cast {
	public id?: number;
	public name?: string;
	public role?: string;
	public imagePath?: string;
}
@Component({
	selector: 'app-cast-list',
	standalone: true,
	imports: [CommonModule],
	templateUrl: './cast-list.component.html',
	styleUrls: ['./cast-list.component.scss']
})
export class CastListComponent {

	@Input()
	public cast?: Cast[];

}
