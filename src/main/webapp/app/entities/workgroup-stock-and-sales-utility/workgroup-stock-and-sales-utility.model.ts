import { BaseEntity } from './../../shared';

export class WorkgroupStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public description?: string,
        public comments?: string,
    ) {
    }
}
