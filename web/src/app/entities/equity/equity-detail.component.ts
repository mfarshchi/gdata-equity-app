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
import {Equity} from './equity';
import {EquityService} from './equity.service';

@Component({
    moduleId: module.id,
	templateUrl: 'equity-detail.component.html',
	selector: 'equity-detail',
})
export class EquityDetailComponent implements OnInit, OnDestroy {
    equity : Equity;

    private params_subscription: any;


    @Input() sub : boolean = false;
    @Output() onSaveClicked = new EventEmitter<Equity>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private equityService: EquityService) {
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for equity-detail ' + id);

            if (id === 'new') {
                this.equity = new Equity();
            } else {
                this.equityService.getEquity(id)
                    .subscribe(equity => {
                            this.equity = equity;
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
        this.equityService.update(this.equity).
            subscribe(
                equity => {
                    this.equity = equity;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.equity);
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
