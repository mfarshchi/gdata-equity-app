//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/support/paging.ts.p.vm
//
import {LazyLoadEvent} from 'primeng/primeng';

export class PageResponse<E> {
    constructor(public totalPages : number,
                public totalElements : number,
                public content : E[]) { }

    // remove the passed element from the content array.
    remove(element : E) {
        let indexToRemove : number = this.content.indexOf(element);
        this.content = this.content.filter((val,i) => i!=indexToRemove);
        this.totalElements = this.totalElements - 1;
    }
}

export class PageRequestByExample<E> {
    constructor(public example : E,
                public lazyLoadEvent : LazyLoadEvent) { }
}
