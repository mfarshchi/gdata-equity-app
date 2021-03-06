//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/service/auth.service.ts.p.vm
//

import { Injectable } from '@angular/core'
import { HttpParams, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import { catchError, map } from 'rxjs/operators';
import 'rxjs/add/observable/throw';

@Injectable()
export class AuthService {

    constructor(private http: HttpClient) {}

    isAuthenticated() : Observable<boolean> {
        return this.http.get('/api/authenticated')
            .pipe(
                catchError(this.handleError)
            );
    }

    login(j_username : string, j_password : string) : Observable<boolean> {
        console.log("login for " + j_username);
        const params = {
                j_username: j_username,
                j_password: j_password,
                submit: 'Login'
        };
		
        let body = new HttpParams({fromObject: params});
       

        return this.http.post('/api/login', body, { observe: 'response' })
            .pipe(
                map(res => res.status == 200),
                catchError(this.handleError)
            );
    }

    private handleError (err: HttpErrorResponse) {
        if (err.error instanceof Error) {
            // A client-side or network error occurred. Handle it accordingly.
            let errMsg = `An error occurred: ${err.error.message}`;
            console.error(errMsg);
            return Observable.throw(errMsg);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            let errMsg = `Backend returned code ${err.status}, body was: ${err.error}`;
            console.error(errMsg);
            return Observable.throw(errMsg);
        }
    }
}
