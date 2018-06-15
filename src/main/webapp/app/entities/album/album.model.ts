import { BaseEntity } from './../../shared';

export class Album implements BaseEntity {
    constructor(
        public id?: string,
        public albumType?: string,
        public name?: string,
        public releaseDate?: string,
        public artistList?: string,
    ) {
    }
}
