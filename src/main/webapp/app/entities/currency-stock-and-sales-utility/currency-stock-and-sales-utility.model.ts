import { BaseEntity } from './../../shared';

export class CurrencyStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public iSOCode?: string,
        public name?: string,
        public company1S?: BaseEntity[],
        public material2S?: BaseEntity[],
        public material3S?: BaseEntity[],
        public lot1S?: BaseEntity[],
        public lot2S?: BaseEntity[],
    ) {
    }
}
