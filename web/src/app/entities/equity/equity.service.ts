//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.service.ts.e.vm
//
import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse, HttpErrorResponse, HttpParams} from '@angular/common/http';
import { LazyLoadEvent } from 'primeng/primeng';
import { MessageService } from '../../service/message.service';
import { PageResponse, PageRequestByExample } from '../../support/paging';
import { Equity } from './equity';
import { Observable } from 'rxjs/Rx';
import { catchError, map } from 'rxjs/operators';
import 'rxjs/add/observable/throw';

@Injectable()
export class EquityService {

    constructor(private http: HttpClient, private messageService : MessageService) {}

    /*
    //findEquityQuery(symbolTicker: string, fromDate: string, toDate: string) : Observable<Equity[]> {
    findEquityQuery0() : Observable<Equity[]> {

      //let body = {'symbolTicker': symbolTicker, 'fromDate': fromDate, 'toDate': toDate};
      let body = {'symbolTicker': 'VIC'};
      console.log('VIC');
      //let body = {'symbolTicker': symbolTicker};
      //return this.http.get<any[]>('/api/equities/equityQuery' + '?symbolTicker=VIC')
      return this.http.get<any[]>('/api/equities/equityQuery' + {params: {body}})
      //return this.http.get<any[]>('/api/equities/equityQuery') //correct
          .pipe(
          map(response => Equity.toArray(response)),
          catchError(this.handleError)
        );
    }
    */

    findEquityQuery() : Observable<Equity[]> {
      console.log('equity.services.ts.findEquityQuery() executed');
      let params = new HttpParams()
        .set('symbolTicker', 'SDV')
        .set('fromDate', '2017-10-01 12:00:00')
        .set('toDate', '2017-12-01 12:00:00');
      console.log(params.toString()); //return parameters of the url
      return this.http.get<any[]>('/api/equities/equityQuery', {params})
        .pipe(
          map(response => Equity.toArray(response)),
          catchError(this.handleError)
        );
    }

  directChartDate() : any {
    console.log("Direct Char Data...................");
    //console.log(this.http.get('https://cdn.rawgit.com/highcharts/highcharts/v6.0.5/samples/data/usdeur.json').subscribe());
    return this.http.get<any[]>('api/equities/equityChart');
      /*
      .map(response => {
          console.log("mock data..........." + response.json());
          return response.json();
        }
      )
      .catch(this.handleError);
      */
  }
    /*
  findEquityChart(){
    this.findEquityQuery().subscribe();
      console.log("Hey I am executed! --------------------------------------///////////");
      console.log(this.eqlist[1].symbolTicker);
      this.findEquityQuery().subscribe(response => this.equityList = response);
      //let observed = this.findEquityQuery();
      console.log("my output: " + this.equityList[1].symbolTicker.toString());
    }
    */

    /**
     * Get a Equity by id.
     */
    getEquity(id : any) : Observable<Equity> {
        return this.http.get('/api/equities/' + id)
            .pipe(
                map(response => new Equity(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Update the passed equity.
     */
    update(equity : Equity) : Observable<Equity> {
        let body = equity;

        return this.http.put('/api/equities/', body)
            .pipe(
                map(response => new Equity(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Load a page (for paginated datatable) of Equity using the passed
     * equity as an example for the search by example facility.
     */
    getPage(equity : Equity, event : LazyLoadEvent) : Observable<PageResponse<Equity>> {
        let req = new PageRequestByExample(equity, event);
        let body = req;

        return this.http.post<PageResponse<any>>('/api/equities/page', body)
            .pipe(
                map(pr =>  new PageResponse<Equity>(pr.totalPages, pr.totalElements, Equity.toArray(pr.content))),
                catchError(this.handleError)
            );
    }

    /**
     * Performs a search by example on 1 attribute (defined on server side) and returns at most 10 results.
     * Used by EquityCompleteComponent.
     */
    complete(query : string) : Observable<Equity[]> {
        let body = {'query': query, 'maxResults': 10};
        return this.http.post<any[]>('/api/equities/complete', body)
            .pipe(
                map(response => Equity.toArray(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Delete an Equity by id.
     */
    delete(id : any) {
        return this.http.delete('/api/equities/' + id)
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
