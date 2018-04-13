//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//

export class Equity {
    // Raw attributes
    id : number;
    pricingDate : Date;
    symbolTicker : string;
    symbolExchange : string;
    currencyISO : string;
    openPrice : number;
    closePrice : number;
    volume : number;
    dayPriceDiff : number;
    fiveDaysPriceDiff : number;
    monthPriceDiff : number;
    movAvg5Days : number;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            if (json.pricingDate != null) {
                this.pricingDate = new Date(json.pricingDate);
            }
            this.symbolTicker = json.symbolTicker;
            this.symbolExchange = json.symbolExchange;
            this.currencyISO = json.currencyISO;
            this.openPrice = json.openPrice;
            this.closePrice = json.closePrice;
            this.volume = json.volume;
            this.dayPriceDiff = json.dayPriceDiff;
            this.fiveDaysPriceDiff = json.fiveDaysPriceDiff;
            this.monthPriceDiff = json.monthPriceDiff;
            this.movAvg5Days = json.movAvg5Days;
        }
    }

    // Utils

    static toArray(jsons : any[]) : Equity[] {
        let equities : Equity[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                equities.push(new Equity(json));
            }
        }
        return equities;
    }

  static toChartData(equities : Equity[]) :[Date, number][] {
    let data : [Date, number][] = [];
    let i :number =0;
    for (let equity of equities){
      data[i] = [equity.pricingDate, equity.closePrice];
      i++;
      //data[0].push(equity.pricingDate, equity.closePrice);
    }
    return data;
  }

}
