import { BaseEntity } from './../../shared';

export class UsrStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public firstName?: string,
        public lastName?: string,
        public isactive?: boolean,
        public comments?: string,
    ) {
        this.isactive = false;
    }
}
