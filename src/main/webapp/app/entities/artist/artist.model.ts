import { BaseEntity } from './../../shared';

export class Artist implements BaseEntity {
    constructor(
        public id?: string,
        public name?: string,
    ) {
    }
}
