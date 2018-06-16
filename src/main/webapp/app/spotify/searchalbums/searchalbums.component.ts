import {Component, OnDestroy, OnInit} from '@angular/core';
import {SearchalbumsService} from './searchalbums.service';
import {Albums} from './albums-modal-service';
import {Subscription} from 'rxjs/Subscription';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-searchalbums',
    templateUrl: './searchalbums.component.html',
    styleUrls: [
        'searchalbums.css'
    ]
})
export class SearchalbumsComponent implements OnInit, OnDestroy {

    muse = '';
    albums: Albums[];
    eventSubscriber: Subscription;

    constructor(private  searchService: SearchalbumsService,
                private jhiAlertService: JhiAlertService) {
    }

    ngOnInit() {
    }

    loadAll() {
        this.searchService.query(this.muse).subscribe(
            (res: HttpResponse<Albums[]>) => {
                this.albums = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy() {
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

}
