import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MaterialAndStockManagementCompanyStockAndSalesUtilityModule } from './company-stock-and-sales-utility/company-stock-and-sales-utility.module';
import { MaterialAndStockManagementCountryStockAndSalesUtilityModule } from './country-stock-and-sales-utility/country-stock-and-sales-utility.module';
import { MaterialAndStockManagementCurrencyStockAndSalesUtilityModule } from './currency-stock-and-sales-utility/currency-stock-and-sales-utility.module';
import { MaterialAndStockManagementForexratesStockAndSalesUtilityModule } from './forexrates-stock-and-sales-utility/forexrates-stock-and-sales-utility.module';
import {
    MaterialAndStockManagementThirdclassificationStockAndSalesUtilityModule
} from './thirdclassification-stock-and-sales-utility/thirdclassification-stock-and-sales-utility.module';
import { MaterialAndStockManagementThirdStockAndSalesUtilityModule } from './third-stock-and-sales-utility/third-stock-and-sales-utility.module';
import {
    MaterialAndStockManagementAddressclassificationStockAndSalesUtilityModule
} from './addressclassification-stock-and-sales-utility/addressclassification-stock-and-sales-utility.module';
import { MaterialAndStockManagementAddressStockAndSalesUtilityModule } from './address-stock-and-sales-utility/address-stock-and-sales-utility.module';
import { MaterialAndStockManagementCivilityStockAndSalesUtilityModule } from './civility-stock-and-sales-utility/civility-stock-and-sales-utility.module';
import {
    MaterialAndStockManagementTransferclassificationStockAndSalesUtilityModule
} from './transferclassification-stock-and-sales-utility/transferclassification-stock-and-sales-utility.module';
import {
    MaterialAndStockManagementMaterialclassificationStockAndSalesUtilityModule
} from './materialclassification-stock-and-sales-utility/materialclassification-stock-and-sales-utility.module';
import { MaterialAndStockManagementLotStockAndSalesUtilityModule } from './lot-stock-and-sales-utility/lot-stock-and-sales-utility.module';
import { MaterialAndStockManagementMaterialStockAndSalesUtilityModule } from './material-stock-and-sales-utility/material-stock-and-sales-utility.module';
import { MaterialAndStockManagementWorkgroupStockAndSalesUtilityModule } from './workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.module';
import { MaterialAndStockManagementUsrStockAndSalesUtilityModule } from './usr-stock-and-sales-utility/usr-stock-and-sales-utility.module';
import { MaterialAndStockManagementMaterialhistoryStockAndSalesUtilityModule } from './materialhistory-stock-and-sales-utility/materialhistory-stock-and-sales-utility.module';
import { MaterialAndStockManagementMovementsdashboardModule } from './movementsdashboard/movementsdashboard.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MaterialAndStockManagementCompanyStockAndSalesUtilityModule,
        MaterialAndStockManagementCountryStockAndSalesUtilityModule,
        MaterialAndStockManagementCurrencyStockAndSalesUtilityModule,
        MaterialAndStockManagementForexratesStockAndSalesUtilityModule,
        MaterialAndStockManagementThirdclassificationStockAndSalesUtilityModule,
        MaterialAndStockManagementThirdStockAndSalesUtilityModule,
        MaterialAndStockManagementAddressclassificationStockAndSalesUtilityModule,
        MaterialAndStockManagementAddressStockAndSalesUtilityModule,
        MaterialAndStockManagementCivilityStockAndSalesUtilityModule,
        MaterialAndStockManagementTransferclassificationStockAndSalesUtilityModule,
        MaterialAndStockManagementMaterialclassificationStockAndSalesUtilityModule,
        MaterialAndStockManagementLotStockAndSalesUtilityModule,
        MaterialAndStockManagementMaterialStockAndSalesUtilityModule,
        MaterialAndStockManagementWorkgroupStockAndSalesUtilityModule,
        MaterialAndStockManagementUsrStockAndSalesUtilityModule,
        MaterialAndStockManagementMaterialhistoryStockAndSalesUtilityModule,
        MaterialAndStockManagementMovementsdashboardModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementEntityModule {}
