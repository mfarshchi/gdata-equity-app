//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/app.module.ts.p.vm
//
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule  }  from '@angular/router';
import { MatDatepickerModule, MatNativeDateModule, MatDialogModule, MatCardModule, MatIconModule, MatSelectModule, MatTabsModule, MatInputModule, MatButtonModule } from '@angular/material';
import { ConfirmDialogModule, FileUploadModule, PanelModule, GrowlModule, MenubarModule, DialogModule, ButtonModule, AutoCompleteModule, DataTableModule, SharedModule, DropdownModule,PickListModule,CheckboxModule,TriStateCheckboxModule, InputTextModule,InputTextareaModule,CalendarModule,PasswordModule,TabViewModule } from 'primeng/primeng';
import { ConfirmationService } from 'primeng/primeng';
import { AppComponent }   from './app.component';
import { HomeComponent }  from './home.component';
import { AuthService } from './service/auth.service';
import { MessageService } from './service/message.service';
import { routing }        from './app.routes';
import { EmailValidator } from './support/email.validator';
import { ConfirmDeleteDialogComponent } from './support/confirm-delete-dialog.component';


// Equity ...
import { EquityService } from './entities/equity/equity.service';
import { EquityListComponent } from './entities/equity/equity-list.component';
import { EquityDetailComponent } from './entities/equity/equity-detail.component';
import { EquityLineComponent } from './entities/equity/equity-line.component';
import { EquityCompleteComponent } from './entities/equity/equity-auto-complete.component';
import { EquityDailyPriceComponent } from './entities/equity/equity-daily-price.component';


// EquityPeriodical ...
import { EquityPeriodicalService } from './entities/equityPeriodical/equityPeriodical.service';
import { EquityPeriodicalListComponent } from './entities/equityPeriodical/equityPeriodical-list.component';
import { EquityPeriodicalDetailComponent } from './entities/equityPeriodical/equityPeriodical-detail.component';
import { EquityPeriodicalLineComponent } from './entities/equityPeriodical/equityPeriodical-line.component';
import { EquityPeriodicalCompleteComponent } from './entities/equityPeriodical/equityPeriodical-auto-complete.component';
import {EquityPeriodicalSignalComponent} from './entities/equityPeriodical/equityPeriodical-signal.component';

// User ...
import { UserService } from './entities/user/user.service';
import { UserListComponent } from './entities/user/user-list.component';
import { UserDetailComponent } from './entities/user/user-detail.component';
import { UserLineComponent } from './entities/user/user-line.component';
import { UserCompleteComponent } from './entities/user/user-auto-complete.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EmailValidator,
    ConfirmDeleteDialogComponent
    ,
    EquityListComponent,
    EquityDailyPriceComponent,
    EquityDetailComponent,
    EquityLineComponent,
    EquityCompleteComponent
    ,
    EquityPeriodicalListComponent,
    EquityPeriodicalSignalComponent,
    EquityPeriodicalDetailComponent,
    EquityPeriodicalLineComponent,
    EquityPeriodicalCompleteComponent
    ,
    UserListComponent,
    UserDetailComponent,
    UserLineComponent,
    UserCompleteComponent
    ],
    imports: [
// angular
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,

// angular material,
        MatDatepickerModule,
        MatNativeDateModule,
        MatDialogModule,
        MatCardModule,
        MatIconModule,
        MatSelectModule,
        MatTabsModule,
        MatInputModule,
        MatButtonModule,

// primeng
        ConfirmDialogModule,
        FileUploadModule,
        PanelModule,
        GrowlModule,
        MenubarModule,
        DialogModule,
        ButtonModule,
        AutoCompleteModule,
        DataTableModule,
        SharedModule,
        DropdownModule,
        PickListModule,
        CheckboxModule,
        TriStateCheckboxModule,
        InputTextModule,
        InputTextareaModule,
        CalendarModule,
        PasswordModule,
        TabViewModule,

// our application routes
        routing
    ],
    providers: [
// our application entity services
        EquityService,
        EquityPeriodicalService,
        UserService,

// our application services
        AuthService,
        MessageService,

// primeng service
        ConfirmationService
    ],
    entryComponents: [ConfirmDeleteDialogComponent],
    bootstrap: [ AppComponent ]
})
export class AppModule {}
