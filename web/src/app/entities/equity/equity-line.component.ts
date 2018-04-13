//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity-line.component.ts.e.vm
//
import {Component, Input} from '@angular/core';
import {Equity} from './equity';

@Component({
	template: `
        {{ equity?.symbolTicker }} {{ equity?.symbolExchange }} 	`,
	selector: 'equity-line',
})
export class EquityLineComponent {
    @Input() equity : Equity;
}
