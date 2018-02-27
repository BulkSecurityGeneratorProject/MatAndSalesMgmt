import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UsrStockAndSalesUtility } from './usr-stock-and-sales-utility.model';
import { UsrStockAndSalesUtilityPopupService } from './usr-stock-and-sales-utility-popup.service';
import { UsrStockAndSalesUtilityService } from './usr-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-usr-stock-and-sales-utility-delete-dialog',
    templateUrl: './usr-stock-and-sales-utility-delete-dialog.component.html'
})
export class UsrStockAndSalesUtilityDeleteDialogComponent {

    usr: UsrStockAndSalesUtility;

    constructor(
        private usrService: UsrStockAndSalesUtilityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.usrService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'usrListModification',
                content: 'Deleted an usr'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-usr-stock-and-sales-utility-delete-popup',
    template: ''
})
export class UsrStockAndSalesUtilityDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usrPopupService: UsrStockAndSalesUtilityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.usrPopupService
                .open(UsrStockAndSalesUtilityDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
