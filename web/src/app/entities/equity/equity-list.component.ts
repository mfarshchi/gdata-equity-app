//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity-list.component.ts.e.vm
//
import { Component, Input, Output, OnChanges, EventEmitter, SimpleChanges} from '@angular/core';
import { Router } from '@angular/router';
import { DataTable, LazyLoadEvent } from 'primeng/primeng';
import { PageResponse } from "../../support/paging";
import { MessageService } from '../../service/message.service';
import { MatDialog } from '@angular/material';
import { ConfirmDeleteDialogComponent } from "../../support/confirm-delete-dialog.component";
import { Equity } from './equity';
import { EquityDetailComponent } from './equity-detail.component';
import { EquityService } from './equity.service';

@Component({
    moduleId: module.id,
	templateUrl: 'equity-list.component.html',
	selector: 'equity-list'
})
export class EquityListComponent {

    @Input() header = "Equities...";

    // When 'sub' is true, it means this list is used as a one-to-many list.
    // It belongs to a parent entity, as a result the addNew operation
    // must prefill the parent entity. The prefill is not done here, instead we
    // emit an event.
    // When 'sub' is false, we display basic search criterias
    @Input() sub : boolean;
    @Output() onAddNewClicked = new EventEmitter();

    equityToDelete : Equity;

    // basic search criterias (visible if not in 'sub' mode)a
    example : Equity = new Equity();

    // list is paginated
    currentPage : PageResponse<Equity> = new PageResponse<Equity>(0,0,[]);


    constructor(private router : Router,
        private equityService : EquityService,
        private messageService : MessageService,
        private confirmDeleteDialog: MatDialog) {
    }

    /**
     * When used as a 'sub' component (to display one-to-many list), refreshes the table
     * content when the input changes.
     */
    ngOnChanges(changes: SimpleChanges) {
        this.loadPage({ first: 0, rows: 10, sortField: null, sortOrder: null, filters: null, multiSortMeta: null });
    }

    /**
     * Invoked when user presses the search button.
     */
    search(dt : DataTable) {
        if (!this.sub) {
            dt.reset();
            this.loadPage({ first: 0, rows: dt.rows, sortField: dt.sortField, sortOrder: dt.sortOrder, filters: null, multiSortMeta: dt.multiSortMeta });
        }
    }

    /**
     * Invoked automatically by primeng datatable.
     */
    loadPage(event : LazyLoadEvent) {
        this.equityService.getPage(this.example, event).
            subscribe(
                pageResponse => this.currentPage = pageResponse,
                error => this.messageService.error('Could not get the results', error)
            );
    }

    onRowSelect(event : any) {
        let id =  event.data.id;
        //this.router.navigate(['/equity', id]);
    }

    addNew() {
        if (this.sub) {
            this.onAddNewClicked.emit("addNew");
        } else {
            this.router.navigate(['/equity', 'new']);
        }
    }

  /*
    showDeleteDialog(rowData : any) {
        let equityToDelete : Equity = <Equity> rowData;

        let dialogRef = this.confirmDeleteDialog.open(ConfirmDeleteDialogComponent);
        dialogRef.afterClosed().subscribe(result => {
            if (result === 'delete') {
                this.delete(equityToDelete);
            }
        });
    }
   */

    private delete(equityToDelete : Equity) {
        let id =  equityToDelete.id;

        this.equityService.delete(id).
            subscribe(
                response => {
                    this.currentPage.remove(equityToDelete);
                    this.messageService.info('Deleted OK', 'Angular Rocks!');
                },
                error => this.messageService.error('Could not delete!', error)
            );
    }
}
