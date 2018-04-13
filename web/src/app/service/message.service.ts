//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/service/message.service.ts.p.vm
//
import { Injectable } from '@angular/core'
import { Subject } from 'rxjs/Subject';
import { Message } from 'primeng/primeng';

@Injectable()
export class MessageService {
    private messageSource = new Subject<Message>();

    messageSource$ = this.messageSource.asObservable();

    info(summary : string, detail : string) {
        this.messageSource.next({severity:'info', summary: summary, detail: detail});
        console.log("INFO: " + summary + " DETAIL: " + detail);
    }

    error(summary : string, detail : string) {
        this.messageSource.next({severity:'error', summary: summary, detail: detail});
        console.log("ERROR: " + summary + " DETAIL: " + detail);
    }
}