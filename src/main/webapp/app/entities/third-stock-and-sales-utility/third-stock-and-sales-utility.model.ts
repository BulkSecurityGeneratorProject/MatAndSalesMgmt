import { BaseEntity } from './../../shared';

export class ThirdStockAndSalesUtility implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public comments?: string,
        public materialhistory1S?: BaseEntity[],
        public materialhistory2S?: BaseEntity[],
        public address3S?: BaseEntity[],
        public thirdClassification9Id?: number,
        public civility1Id?: number,
    ) {
    }
}
