import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MaterialAndStockManagementSharedModule } from '../../shared';
import {
    MovementsdashboardService,
    MovementsdashboardPopupService,
    MovementsdashboardComponent,
    MovementsdashboardDetailComponent,
    MovementsdashboardDialogComponent,
    MovementsdashboardPopupComponent,
    MovementsdashboardDeletePopupComponent,
    MovementsdashboardDeleteDialogComponent,
    movementsdashboardRoute,
    movementsdashboardPopupRoute,
} from './';

const ENTITY_STATES = [
    ...movementsdashboardRoute,
    ...movementsdashboardPopupRoute,
];

@NgModule({
    imports: [
        MaterialAndStockManagementSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MovementsdashboardComponent,
        MovementsdashboardDetailComponent,
        MovementsdashboardDialogComponent,
        MovementsdashboardDeleteDialogComponent,
        MovementsdashboardPopupComponent,
        MovementsdashboardDeletePopupComponent,
    ],
    entryComponents: [
        MovementsdashboardComponent,
        MovementsdashboardDialogComponent,
        MovementsdashboardPopupComponent,
        MovementsdashboardDeleteDialogComponent,
        MovementsdashboardDeletePopupComponent,
    ],
    providers: [
        MovementsdashboardService,
        MovementsdashboardPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MaterialAndStockManagementMovementsdashboardModule {}
