//
// ANZ Project for an Interview
//
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/app.component.ts.p.vm
//
import { Component,OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import { Message, MenuItem } from 'primeng/primeng';
import { AuthService} from './service/auth.service';
import { MessageService} from './service/message.service';

/**
 * The Root component.
 * Defines the main layout and handles user login in a dialog.
 */
@Component({
    moduleId: module.id,
    selector: 'app-root',
    template: `
        <p-growl [value]="msgs"></p-growl>

        <div class="ui-g layout">
            <div class="ui-g-12 ui-md-1">ANZ Bank</div>
            <div class="ui-g-12 ui-md-11 ui-g-nopad">
                <div class="ui-g-12 ui-g-nopad" style="font-size: 14px;">
                    <p-menubar [model]="items"></p-menubar>
                </div>
                <div class="ui-g-12">
                    <router-outlet></router-outlet>
                </div>
            </div>
        </div>
        <p-dialog header="Please login" [visible]="displayLoginDialog" [responsive]="true" showEffect="fade" [modal]="true" [closable]="false" *ngIf="!authenticated">
            <p>Use admin/admin as default username and password</p>
            <div ngForm class="ui-g">
                <div class="ui-g-12" *ngIf="loginFailed">
                    <div class="ui-message ui-messages-error ui-corner-all">
                        Invalid login or password
                    </div>
                </div>
                <div class="ui-g-12">
                    <div class="ui-g-4">
                        <label for="j_username">Username</label>
                    </div>
                    <div class="ui-g-8">
                        <input pInputText id="j_username" [(ngModel)]="j_username" name="username"/>
                    </div>
                </div>
                <div class="ui-g-12">
                    <div class="ui-g-4">
                        <label for="j_password">Password</label>
                    </div>
                    <div class="ui-g-8">
                        <input type="password" pPassword id="j_password" [(ngModel)]="j_password" name="password"/>
                    </div>
                </div>
            </div>
            <footer>
                <div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                    <button pButton (click)="login()" icon="fa-sign-in" label="Login"></button>
                </div>
            </footer>
        </p-dialog>
               `,
    styles:[`
        .layout div {
            background-color: white;
            border: 1px solid #f5f7f8;
        }
    `]
})
export class AppComponent implements OnInit {
    public items : MenuItem[] = [{label: 'hello'}];
    msgs : Message[] = [];

    displayLoginDialog : boolean = false;
    loginFailed : boolean = false;
    authenticated : boolean = false;
    j_username : string = "admin";
    j_password : string = "admin";

    constructor(private authService: AuthService, private messageService: MessageService) {
        messageService.messageSource$.subscribe(
            msg => {
                this.msgs.push(msg);
            });
    }

    ngOnInit() {
        this.items = [
            { label: 'Home', routerLink: ['/'], icon: 'fa-home' },

            { label: 'Entities', icon: 'fa-search', items: [
                    {label: 'Equity General Search', routerLink: ['/equity-list']},
                    {label: 'EquityPeriodical General Search', routerLink: ['/equityPeriodical-list']},
                    {label: 'EquityPeriodical Signal', routerLink: ['/equityPeriodical-signal']},
                    //{label: 'Equity Daily Price', routerLink: ['/equity-daily-price']},
                     {label: 'User Search', routerLink: ['/user-list']},
                    // {label: 'User Create', routerLink: ['/user/new']}                ]
            }

        ];

        this.authService.isAuthenticated().
            subscribe(
                resp =>
                    {
                        this.authenticated = resp;
                        this.displayLoginDialog = !this.authenticated;
                        if (this.authenticated) {
                            this.items.push({label: 'Sign out', url: '/api/logout', icon: 'fa-sign-out' });
                            console.log('You are authenticated...', '');
                        } else {
                            console.log('You are NOT authenticated...', '');
                        }
                    },
                error =>  this.messageService.error('isAuthenticated Error', error)
            );
    }

    login() {
        this.authService.login(this.j_username, this.j_password).
            subscribe(
                loginOk => {
                    if (loginOk) {
                        this.displayLoginDialog = false;
                        this.authenticated = true;
                        this.items.push({label: 'Sign out', url: '/api/logout', icon: 'fa-sign-out' });
                        this.loginFailed = false;
                        this.messageService.info('You are now logged in.', '');
                    } else {
                        this.loginFailed = true;
                        this.displayLoginDialog = true;
                        this.authenticated = false;
                    }
                },
                error => {
                    this.messageService.error('Login error', error);
                    this.loginFailed = true;
                    this.displayLoginDialog = true;
                    this.authenticated = false;
                }
        );
    }
}
