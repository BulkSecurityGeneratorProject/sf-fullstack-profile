import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {SearchalbumsRoutingModule} from './searchalbums-routing.module';
import {SearchalbumsComponent} from './searchalbums.component';
import {SearchalbumsService} from './searchalbums.service';

@NgModule({
    imports: [
        CommonModule,
        SearchalbumsRoutingModule,
        FormsModule
    ],
    declarations: [SearchalbumsComponent],
    providers: [SearchalbumsService]
})
export class SearchalbumsModule {
}
