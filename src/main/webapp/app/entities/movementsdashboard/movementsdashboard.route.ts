import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MovementsdashboardComponent } from './movementsdashboard.component';
import { MovementsdashboardDetailComponent } from './movementsdashboard-detail.component';
import { MovementsdashboardPopupComponent } from './movementsdashboard-dialog.component';
import { MovementsdashboardDeletePopupComponent } from './movementsdashboard-delete-dialog.component';

export const movementsdashboardRoute: Routes = [
    {
        path: 'movementsdashboard',
        component: MovementsdashboardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.movementsdashboard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'movementsdashboard/:id',
        component: MovementsdashboardDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.movementsdashboard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const movementsdashboardPopupRoute: Routes = [
    {
        path: 'movementsdashboard-new',
        component: MovementsdashboardPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.movementsdashboard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'movementsdashboard/:id/edit',
        component: MovementsdashboardPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.movementsdashboard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'movementsdashboard/:id/delete',
        component: MovementsdashboardDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'materialAndStockManagementApp.movementsdashboard.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
