import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaterialAndStockManagementSharedModule } from '../../shared';
import {
    WorkgroupStockAndSalesUtilityService,
    WorkgroupStockAndSalesUtilityPopupService,
    WorkgroupStockAndSalesUtilityComponent,
    WorkgroupStockAndSalesUtilityDetailComponent,
    WorkgroupStockAndSalesUtilityDialogComponent,
    WorkgroupStockAndSalesUtilityPopupComponent,
    WorkgroupStockAndSalesUtilityDeletePopupComponent,
    WorkgroupStockAndSalesUtilityDeleteDialogComponent,
    workgroupRoute,
    workgroupPopupRoute,
} from './';

const ENTITY_STATES = [
    ...workgroupRoute,
    ...workgroupPopupRoute,
];

@NgModule({
    imports: [
        MaterialAndStockManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WorkgroupStockAndSalesUtilityComponent,
        WorkgroupStockAndSalesUtilityDetailComponent,
        WorkgroupStockAndSalesUtilityDialogComponent,
        WorkgroupStockAndSalesUtilityDeleteDialogComponent,
        WorkgroupStockAndSalesUtilityPopupComponent,
        WorkgroupStockAndSalesUtilityDeletePopupComponent,
    ],
    entryComponents: [
        WorkgroupStockAndSalesUtilityComponent,
        WorkgroupStockAndSalesUtilityDialogComponent,
        WorkgroupStockAndSalesUtilityPopupComponent,
        WorkgroupStockAndSalesUtilityDeleteDialogComponent,
        WorkgroupStockAndSalesUtilityDeletePopupComponent,
    ],
    providers: [
        WorkgroupStockAndSalesUtilityService,
        WorkgroupStockAndSalesUtilityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementWorkgroupStockAndSalesUtilityModule {}
