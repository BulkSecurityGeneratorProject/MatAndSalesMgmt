import { BaseEntity } from './../../shared';

export class MaterialhistoryStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public creationDate?: any,
        public comments?: string,
        public itemTransfereds?: BaseEntity[],
        public transferclassification2Id?: number,
        public warehousefromId?: number,
        public warehousetoId?: number,
    ) {
    }
}
