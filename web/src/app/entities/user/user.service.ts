//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.service.ts.e.vm
//
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { LazyLoadEvent } from 'primeng/primeng';
import { MessageService } from '../../service/message.service';
import { PageResponse, PageRequestByExample } from '../../support/paging';
import { User } from './user';
import { Observable } from 'rxjs/Rx';
import { catchError, map } from 'rxjs/operators';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {

    constructor(private http: HttpClient, private messageService : MessageService) {}

    /**
     * Get a User by id.
     */
    getUser(id : any) : Observable<User> {
        return this.http.get('/api/users/' + id)
            .pipe(
                map(response => new User(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Update the passed user.
     */
    update(user : User) : Observable<User> {
        let body = user;

        return this.http.put('/api/users/', body)
            .pipe(
                map(response => new User(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Load a page (for paginated datatable) of User using the passed
     * user as an example for the search by example facility.
     */
    getPage(user : User, event : LazyLoadEvent) : Observable<PageResponse<User>> {
        let req = new PageRequestByExample(user, event);
        let body = req;

        return this.http.post<PageResponse<any>>('/api/users/page', body)
            .pipe(
                map(pr =>  new PageResponse<User>(pr.totalPages, pr.totalElements, User.toArray(pr.content))),
                catchError(this.handleError)
            );
    }

    /**
     * Performs a search by example on 1 attribute (defined on server side) and returns at most 10 results.
     * Used by UserCompleteComponent.
     */
    complete(query : string) : Observable<User[]> {
        let body = {'query': query, 'maxResults': 10};
        return this.http.post<any[]>('/api/users/complete', body)
            .pipe(
                map(response => User.toArray(response)),
                catchError(this.handleError)
            );
    }

    /**
     * Delete an User by id.
     */
    delete(id : any) {
        return this.http.delete('/api/users/' + id)
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
