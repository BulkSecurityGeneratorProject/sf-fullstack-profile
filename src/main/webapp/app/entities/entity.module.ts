import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ApispotifyArtistModule } from './artist/artist.module';
import { ApispotifyAlbumModule } from './album/album.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ApispotifyArtistModule,
        ApispotifyAlbumModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ApispotifyEntityModule {}
