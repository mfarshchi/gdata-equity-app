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
import { EquityPeriodical } from './equityPeriodical';
import { EquityPeriodicalDetailComponent } from './equityPeriodical-detail.component';
import { EquityPeriodicalService } from './equityPeriodical.service';

@Component({
  moduleId: module.id,
  templateUrl: 'equityPeriodical-signal.component.html',
  selector: 'equityPeriodical-signal'
})
export class EquityPeriodicalSignalComponent {

  @Input() header = "EquityPeriodicals...";

  // When 'sub' is true, it means this list is used as a one-to-many list.
  // It belongs to a parent entity, as a result the addNew operation
  // must prefill the parent entity. The prefill is not done here, instead we
  // emit an event.
  // When 'sub' is false, we display basic search criterias
  @Input() sub : boolean;
  @Output() onAddNewClicked = new EventEmitter();

  equityPeriodicalToDelete : EquityPeriodical;

  // signal query parameters
  timePeriod :  string = '2017-Feb';
  fromPriceDiffPercent : number = 100.00;
  toPriceDiffPercent : number = 9999.00;
  observedRecords : number = 15;


  // basic search criterias (visible if not in 'sub' mode)
  example : EquityPeriodical = new EquityPeriodical();
  equityPeriodicalList : EquityPeriodical[];


  // list is paginated
  currentPage : PageResponse<EquityPeriodical> = new PageResponse<EquityPeriodical>(0,0,[]);


  constructor(private router : Router,
              private equityPeriodicalService : EquityPeriodicalService,
              private messageService : MessageService,
              private confirmDeleteDialog: MatDialog) {
  }

  /**
   * When used as a 'sub' component (to display one-to-many list), refreshes the table
   * content when the input changes.
   */
  ngOnChanges(changes: SimpleChanges) {
    this.findEquityPeriodicalSignal({ first: 0, rows: 10, sortField: null, sortOrder: null, filters: null, multiSortMeta: null });
  }

  /**
   * Invoked when user presses the search button.
   */
  search(dt : DataTable) {
    if (!this.sub) {
      dt.reset();
      this.findEquityPeriodicalSignal({ first: 0, rows: dt.rows, sortField: dt.sortField, sortOrder: dt.sortOrder, filters: null, multiSortMeta: dt.multiSortMeta });
    }
  }

  findEquityPeriodicalSignal(event : LazyLoadEvent) {
    this.equityPeriodicalService.findEquityPeriodicalSignal(
      this.timePeriod,
      this.fromPriceDiffPercent,
      this.toPriceDiffPercent,
      this.observedRecords
    ).subscribe(
      response => this.equityPeriodicalList = response
    );
  }

  /**
   * Invoked automatically by primeng datatable.
   */
  loadPage(event : LazyLoadEvent) {
    this.equityPeriodicalService.getPage(this.example, event).
    subscribe(
      pageResponse => this.currentPage = pageResponse,
      error => this.messageService.error('Could not get the results', error)
    );
  }

  onRowSelect(event : any) {
    let id =  event.data.id;
    //this.router.navigate(['/equityPeriodical', id]);
  }

  addNew() {
    if (this.sub) {
      this.onAddNewClicked.emit("addNew");
    } else {
      this.router.navigate(['/equityPeriodical', 'new']);
    }
  }

  showDeleteDialog(rowData : any) {
    let equityPeriodicalToDelete : EquityPeriodical = <EquityPeriodical> rowData;

    let dialogRef = this.confirmDeleteDialog.open(ConfirmDeleteDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'delete') {
        this.delete(equityPeriodicalToDelete);
      }
    });
  }

  private delete(equityPeriodicalToDelete : EquityPeriodical) {
    let id =  equityPeriodicalToDelete.id;

    this.equityPeriodicalService.delete(id).
    subscribe(
      response => {
        this.currentPage.remove(equityPeriodicalToDelete);
        this.messageService.info('Deleted OK', 'Angular Rocks!');
      },
      error => this.messageService.error('Could not delete!', error)
    );
  }
}
