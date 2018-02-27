import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaterialAndStockManagementSharedModule } from '../../shared';
import {
    CivilityStockAndSalesUtilityService,
    CivilityStockAndSalesUtilityPopupService,
    CivilityStockAndSalesUtilityComponent,
    CivilityStockAndSalesUtilityDetailComponent,
    CivilityStockAndSalesUtilityDialogComponent,
    CivilityStockAndSalesUtilityPopupComponent,
    CivilityStockAndSalesUtilityDeletePopupComponent,
    CivilityStockAndSalesUtilityDeleteDialogComponent,
    civilityRoute,
    civilityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...civilityRoute,
    ...civilityPopupRoute,
];

@NgModule({
    imports: [
        MaterialAndStockManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CivilityStockAndSalesUtilityComponent,
        CivilityStockAndSalesUtilityDetailComponent,
        CivilityStockAndSalesUtilityDialogComponent,
        CivilityStockAndSalesUtilityDeleteDialogComponent,
        CivilityStockAndSalesUtilityPopupComponent,
        CivilityStockAndSalesUtilityDeletePopupComponent,
    ],
    entryComponents: [
        CivilityStockAndSalesUtilityComponent,
        CivilityStockAndSalesUtilityDialogComponent,
        CivilityStockAndSalesUtilityPopupComponent,
        CivilityStockAndSalesUtilityDeleteDialogComponent,
        CivilityStockAndSalesUtilityDeletePopupComponent,
    ],
    providers: [
        CivilityStockAndSalesUtilityService,
        CivilityStockAndSalesUtilityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementCivilityStockAndSalesUtilityModule {}
