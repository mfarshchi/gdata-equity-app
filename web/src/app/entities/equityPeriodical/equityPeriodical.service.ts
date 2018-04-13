//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.service.ts.e.vm
//
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { LazyLoadEvent } from 'primeng/primeng';
import { MessageService } from '../../service/message.service';
import { PageResponse, PageRequestByExample } from '../../support/paging';
import { EquityPeriodical } from './equityPeriodical';
import { Observable } from 'rxjs/Rx';
import { catchError, map } from 'rxjs/operators';
import 'rxjs/add/observable/throw';

@Injectable()
export class EquityPeriodicalService {

    constructor(private http: HttpClient, private messageService : MessageService) {}

  /*
   symbolTicker : string;
   timePeriod : string;
   firstPricingDate : Date;
   firstClosePrice : number;
   lastPricingDate : Date;
   lastClosePrice : number;
   priceDiff : number;
   priceDiffPercent : number;
   observedRecords : number;
   */

  findEquityPeriodicalSignal(
    timePeriod : string,
    fromPriceDiffPercent : number,
    toPriceDiffPercent : number,
    observedRecords : number) : Observable<EquityPeriodical[]> {

    let params = new HttpParams()
        .set('timePeriod', timePeriod.toString())
        .set('fromPricePercent', fromPriceDiffPercent.toString())
        .set('toPricePercent', toPriceDiffPercent.toString())
        .set('observedRecords', observedRecords.toString());
    console.log(params.toString()); //return parameters of the url

    return this.http.get<any[]>('/api/equityPeriodicals/signal', {params})
      .pipe(
        map(response => EquityPeriodical.toArray(response)),
        catchError(this.handleError)
      );
  }

    /**
     * Get a EquityPeriodical by id.
     */
    getEquityPeriodical(id : any) : Observable<EquityPeriodical> {
        return this.http.get('/api/equityPeriodicals/' + id)
            .pipe(
                map(response => new EquityPeriodical(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Update the passed equityPeriodical.
     */
    update(equityPeriodical : EquityPeriodical) : Observable<EquityPeriodical> {
        let body = equityPeriodical;

        return this.http.put('/api/equityPeriodicals/', body)
            .pipe(
                map(response => new EquityPeriodical(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Load a page (for paginated datatable) of EquityPeriodical using the passed
     * equityPeriodical as an example for the search by example facility.
     */
    getPage(equityPeriodical : EquityPeriodical, event : LazyLoadEvent) : Observable<PageResponse<EquityPeriodical>> {
        let req = new PageRequestByExample(equityPeriodical, event);
        let body = req;

        return this.http.post<PageResponse<any>>('/api/equityPeriodicals/page', body)
            .pipe(
                map(pr =>  new PageResponse<EquityPeriodical>(pr.totalPages, pr.totalElements, EquityPeriodical.toArray(pr.content))),
                catchError(this.handleError)
            );
    }

    /**
     * Performs a search by example on 1 attribute (defined on server side) and returns at most 10 results.
     * Used by EquityPeriodicalCompleteComponent.
     */
    complete(query : string) : Observable<EquityPeriodical[]> {
        let body = {'query': query, 'maxResults': 10};
        return this.http.post<any[]>('/api/equityPeriodicals/complete', body)
            .pipe(
                map(response => EquityPeriodical.toArray(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Delete an EquityPeriodical by id.
     */
    delete(id : any) {
        return this.http.delete('/api/equityPeriodicals/' + id)
            .pipe(catchError(this.handleError));
    }

    // sample method from angular doc
    private handleError (error: HttpErrorResponse) {
        // TODO: seems we cannot use messageService from here...
        let errMsg = (error.message) ? error.message : 'Server error';
        console.error(errMsg);
        if (error.status === 401 ) {
            window.location.href = '/';
        }
        return Observable.throw(errMsg);
    }
}
