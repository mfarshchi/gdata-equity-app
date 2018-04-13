//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity-line.component.ts.e.vm
//
import {Component, Input} from '@angular/core';
import {User} from './user';

@Component({
	template: `
        {{ user?.login }} {{ user?.firstName }} {{ user?.lastName }} 	`,
	selector: 'user-line',
})
export class UserLineComponent {
    @Input() user : User;
}
