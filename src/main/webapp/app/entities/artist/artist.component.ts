import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Artist } from './artist.model';
import { ArtistService } from './artist.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-artist',
    templateUrl: './artist.component.html'
})
export class ArtistComponent implements OnInit, OnDestroy {
artists: Artist[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private artistService: ArtistService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.artistService.query().subscribe(
            (res: HttpResponse<Artist[]>) => {
                this.artists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInArtists();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Artist) {
        return item.id;
    }
    registerChangeInArtists() {
        this.eventSubscriber = this.eventManager.subscribe('artistListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
