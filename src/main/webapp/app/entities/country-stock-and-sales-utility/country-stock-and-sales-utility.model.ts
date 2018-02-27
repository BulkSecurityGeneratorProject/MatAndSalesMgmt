import { BaseEntity } from './../../shared';

export class CountryStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public iSOCode?: string,
        public name?: string,
        public addresscs?: BaseEntity[],
    ) {
    }
}
