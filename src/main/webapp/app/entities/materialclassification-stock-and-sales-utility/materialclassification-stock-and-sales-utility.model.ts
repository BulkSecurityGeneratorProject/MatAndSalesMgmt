import { BaseEntity } from './../../shared';

export class MaterialclassificationStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public comments?: string,
        public material1S?: BaseEntity[],
        public material4S?: BaseEntity[],
    ) {
    }
}
