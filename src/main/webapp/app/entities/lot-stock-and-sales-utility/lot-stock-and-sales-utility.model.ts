import { BaseEntity } from './../../shared';

export class LotStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public description?: string,
        public creationDate?: any,
        public numberOfItems?: number,
        public comments?: string,
        public material4S?: BaseEntity[],
        public buycurrency1Id?: number,
        public sellcurrency1Id?: number,
    ) {
    }
}
