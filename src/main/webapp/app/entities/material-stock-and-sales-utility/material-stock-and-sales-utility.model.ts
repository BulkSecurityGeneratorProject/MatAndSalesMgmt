import { BaseEntity } from './../../shared';

export class MaterialStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public description?: string,
        public creationDate?: any,
        public comments?: string,
        public materialclassification6Id?: number,
        public buycurrencyId?: number,
        public sellcurrencyId?: number,
        public lot5Id?: number,
        public materialclassification1Id?: number,
    ) {
    }
}
