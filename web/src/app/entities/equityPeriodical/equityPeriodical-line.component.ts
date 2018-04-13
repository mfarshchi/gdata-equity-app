//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity-line.component.ts.e.vm
//
import {Component, Input} from '@angular/core';
import {EquityPeriodical} from './equityPeriodical';

@Component({
	template: `
        {{ equityPeriodical?.symbolTicker }} {{ equityPeriodical?.timePeriod }} 	`,
	selector: 'equityPeriodical-line',
})
export class EquityPeriodicalLineComponent {
    @Input() equityPeriodical : EquityPeriodical;
}
