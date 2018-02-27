import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UsrStockAndSalesUtilityComponent } from './usr-stock-and-sales-utility.component';
import { UsrStockAndSalesUtilityDetailComponent } from './usr-stock-and-sales-utility-detail.component';
import { UsrStockAndSalesUtilityPopupComponent } from './usr-stock-and-sales-utility-dialog.component';
import { UsrStockAndSalesUtilityDeletePopupComponent } from './usr-stock-and-sales-utility-delete-dialog.component';

export const usrRoute: Routes = [
    {
        path: 'usr-stock-and-sales-utility',
        component: UsrStockAndSalesUtilityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.usr.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'usr-stock-and-sales-utility/:id',
        component: UsrStockAndSalesUtilityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.usr.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const usrPopupRoute: Routes = [
    {
        path: 'usr-stock-and-sales-utility-new',
        component: UsrStockAndSalesUtilityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.usr.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usr-stock-and-sales-utility/:id/edit',
        component: UsrStockAndSalesUtilityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.usr.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usr-stock-and-sales-utility/:id/delete',
        component: UsrStockAndSalesUtilityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.usr.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
