import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaterialAndStockManagementSharedModule } from '../../shared';
import {
    UsrStockAndSalesUtilityService,
    UsrStockAndSalesUtilityPopupService,
    UsrStockAndSalesUtilityComponent,
    UsrStockAndSalesUtilityDetailComponent,
    UsrStockAndSalesUtilityDialogComponent,
    UsrStockAndSalesUtilityPopupComponent,
    UsrStockAndSalesUtilityDeletePopupComponent,
    UsrStockAndSalesUtilityDeleteDialogComponent,
    usrRoute,
    usrPopupRoute,
} from './';

const ENTITY_STATES = [
    ...usrRoute,
    ...usrPopupRoute,
];

@NgModule({
    imports: [
        MaterialAndStockManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UsrStockAndSalesUtilityComponent,
        UsrStockAndSalesUtilityDetailComponent,
        UsrStockAndSalesUtilityDialogComponent,
        UsrStockAndSalesUtilityDeleteDialogComponent,
        UsrStockAndSalesUtilityPopupComponent,
        UsrStockAndSalesUtilityDeletePopupComponent,
    ],
    entryComponents: [
        UsrStockAndSalesUtilityComponent,
        UsrStockAndSalesUtilityDialogComponent,
        UsrStockAndSalesUtilityPopupComponent,
        UsrStockAndSalesUtilityDeleteDialogComponent,
        UsrStockAndSalesUtilityDeletePopupComponent,
    ],
    providers: [
        UsrStockAndSalesUtilityService,
        UsrStockAndSalesUtilityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementUsrStockAndSalesUtilityModule {}
