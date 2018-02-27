import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaterialAndStockManagementSharedModule } from '../../shared';
import {
    ThirdclassificationStockAndSalesUtilityService,
    ThirdclassificationStockAndSalesUtilityPopupService,
    ThirdclassificationStockAndSalesUtilityComponent,
    ThirdclassificationStockAndSalesUtilityDetailComponent,
    ThirdclassificationStockAndSalesUtilityDialogComponent,
    ThirdclassificationStockAndSalesUtilityPopupComponent,
    ThirdclassificationStockAndSalesUtilityDeletePopupComponent,
    ThirdclassificationStockAndSalesUtilityDeleteDialogComponent,
    thirdclassificationRoute,
    thirdclassificationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...thirdclassificationRoute,
    ...thirdclassificationPopupRoute,
];

@NgModule({
    imports: [
        MaterialAndStockManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ThirdclassificationStockAndSalesUtilityComponent,
        ThirdclassificationStockAndSalesUtilityDetailComponent,
        ThirdclassificationStockAndSalesUtilityDialogComponent,
        ThirdclassificationStockAndSalesUtilityDeleteDialogComponent,
        ThirdclassificationStockAndSalesUtilityPopupComponent,
        ThirdclassificationStockAndSalesUtilityDeletePopupComponent,
    ],
    entryComponents: [
        ThirdclassificationStockAndSalesUtilityComponent,
        ThirdclassificationStockAndSalesUtilityDialogComponent,
        ThirdclassificationStockAndSalesUtilityPopupComponent,
        ThirdclassificationStockAndSalesUtilityDeleteDialogComponent,
        ThirdclassificationStockAndSalesUtilityDeletePopupComponent,
    ],
    providers: [
        ThirdclassificationStockAndSalesUtilityService,
        ThirdclassificationStockAndSalesUtilityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementThirdclassificationStockAndSalesUtilityModule {}
