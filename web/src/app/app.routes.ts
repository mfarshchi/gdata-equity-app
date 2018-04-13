//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/app.routes.ts.p.vm
//
import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';

import { EquityListComponent } from './entities/equity/equity-list.component';
import { EquityDailyPriceComponent } from "./entities/equity/equity-daily-price.component";
import { EquityDetailComponent } from './entities/equity/equity-detail.component';

import { EquityPeriodicalListComponent } from './entities/equityPeriodical/equityPeriodical-list.component';
import { EquityPeriodicalSignalComponent } from './entities/equityPeriodical/equityPeriodical-signal.component';
import { EquityPeriodicalDetailComponent } from './entities/equityPeriodical/equityPeriodical-detail.component';

import { UserListComponent } from './entities/user/user-list.component';
import { UserDetailComponent } from './entities/user/user-detail.component';

export const routes: Routes = [
    { path : '',  component: HomeComponent }
    ,
    {path: 'equity-list', component: EquityListComponent },
    {path: 'equity/:id', component: EquityDetailComponent },
    {path: 'equity-daily-price', component: EquityDailyPriceComponent }
    ,
    {path: 'equityPeriodical-list', component: EquityPeriodicalListComponent },
    {path: 'equityPeriodical/:id', component: EquityPeriodicalDetailComponent },
     {path: 'equityPeriodical-signal', component: EquityPeriodicalSignalComponent }
    ,
    {path: 'user-list', component: UserListComponent },
    {path: 'user/:id', component: UserDetailComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
