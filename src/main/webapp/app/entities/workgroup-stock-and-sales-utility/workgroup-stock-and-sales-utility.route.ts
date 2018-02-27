import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { WorkgroupStockAndSalesUtilityComponent } from './workgroup-stock-and-sales-utility.component';
import { WorkgroupStockAndSalesUtilityDetailComponent } from './workgroup-stock-and-sales-utility-detail.component';
import { WorkgroupStockAndSalesUtilityPopupComponent } from './workgroup-stock-and-sales-utility-dialog.component';
import {
    WorkgroupStockAndSalesUtilityDeletePopupComponent
} from './workgroup-stock-and-sales-utility-delete-dialog.component';

export const workgroupRoute: Routes = [
    {
        path: 'workgroup-stock-and-sales-utility',
        component: WorkgroupStockAndSalesUtilityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.workgroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'workgroup-stock-and-sales-utility/:id',
        component: WorkgroupStockAndSalesUtilityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.workgroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workgroupPopupRoute: Routes = [
    {
        path: 'workgroup-stock-and-sales-utility-new',
        component: WorkgroupStockAndSalesUtilityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.workgroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'workgroup-stock-and-sales-utility/:id/edit',
        component: WorkgroupStockAndSalesUtilityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.workgroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'workgroup-stock-and-sales-utility/:id/delete',
        component: WorkgroupStockAndSalesUtilityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.workgroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
