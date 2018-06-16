import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchalbumsComponent} from './searchalbums.component';

const routes: Routes = [{
    path: 'searchalbums',
    component: SearchalbumsComponent,
    data: {
        authorities: [],
        pageTitle: 'Search Albums'
    }
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchalbumsRoutingModule { }
