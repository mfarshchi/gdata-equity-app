//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity-detail.component.ts.e.vm
//
import {Component, OnInit, OnDestroy, Input, Output, EventEmitter} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectItem } from 'primeng/primeng';
import { MessageService} from '../../service/message.service';
import {EquityPeriodical} from './equityPeriodical';
import {EquityPeriodicalService} from './equityPeriodical.service';

@Component({
    moduleId: module.id,
	templateUrl: 'equityPeriodical-detail.component.html',
	selector: 'equityPeriodical-detail',
})
export class EquityPeriodicalDetailComponent implements OnInit, OnDestroy {
    equityPeriodical : EquityPeriodical;

    private params_subscription: any;


    @Input() sub : boolean = false;
    @Output() onSaveClicked = new EventEmitter<EquityPeriodical>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private equityPeriodicalService: EquityPeriodicalService) {
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for equityPeriodical-detail ' + id);

            if (id === 'new') {
                this.equityPeriodical = new EquityPeriodical();
            } else {
                this.equityPeriodicalService.getEquityPeriodical(id)
                    .subscribe(equityPeriodical => {
                            this.equityPeriodical = equityPeriodical;
                        },
                        error =>  this.messageService.error('ngOnInit error', error)
                    );
            }
        });
    }

    ngOnDestroy() {
        if (!this.sub) {
            this.params_subscription.unsubscribe();
        }
    }

    onSave() {
        this.equityPeriodicalService.update(this.equityPeriodical).
            subscribe(
                equityPeriodical => {
                    this.equityPeriodical = equityPeriodical;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.equityPeriodical);
                        this.messageService.info('Saved OK and msg emitted', 'Angular Rocks!')
                    } else {
                        this.messageService.info('Saved OK', 'Angular Rocks!')
                    }
                },
                error =>  this.messageService.error('Could not save', error)
            );
    }

    onCancel() {
        if (this.sub) {
            this.onCancelClicked.emit("cancel");
            this.messageService.info('Cancel clicked and msg emitted', 'Angular Rocks!')
        }
    }

}
