//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//

export class EquityPeriodical {
    // Raw attributes
    id : number;
    symbolTicker : string;
    timePeriod : string;
    firstPricingDate : Date;
    firstClosePrice : number;
    lastPricingDate : Date;
    lastClosePrice : number;
    priceDiff : number;
    priceDiffPercent : number;
    observedRecords : number;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.symbolTicker = json.symbolTicker;
            this.timePeriod = json.timePeriod;
            if (json.firstPricingDate != null) {
                this.firstPricingDate = new Date(json.firstPricingDate);
            }
            this.firstClosePrice = json.firstClosePrice;
            if (json.lastPricingDate != null) {
                this.lastPricingDate = new Date(json.lastPricingDate);
            }
            this.lastClosePrice = json.lastClosePrice;
            this.priceDiff = json.priceDiff;
            this.priceDiffPercent = json.priceDiffPercent;
            this.observedRecords = json.observedRecords;
        }
    }

    // Utils

    static toArray(jsons : any[]) : EquityPeriodical[] {
        let equityPeriodicals : EquityPeriodical[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                equityPeriodicals.push(new EquityPeriodical(json));
            }
        }
        return equityPeriodicals;
    }
}
