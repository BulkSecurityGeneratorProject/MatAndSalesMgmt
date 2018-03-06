import { BaseEntity } from './../../shared';

export class Movementsdashboard implements BaseEntity {
    constructor(
        public id?: number,
        public movementDate?: any,
        public materialClass?: string,
        public initialLocation?: string,
        public finalLocation?: string,
        public profitOrLoss?: number,
        public numberOfItems?: number,
    ) {
    }
}
