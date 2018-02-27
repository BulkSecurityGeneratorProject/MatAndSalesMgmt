import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WorkgroupStockAndSalesUtility } from './workgroup-stock-and-sales-utility.model';
import { WorkgroupStockAndSalesUtilityPopupService } from './workgroup-stock-and-sales-utility-popup.service';
import { WorkgroupStockAndSalesUtilityService } from './workgroup-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-workgroup-stock-and-sales-utility-delete-dialog',
    templateUrl: './workgroup-stock-and-sales-utility-delete-dialog.component.html'
})
export class WorkgroupStockAndSalesUtilityDeleteDialogComponent {

    workgroup: WorkgroupStockAndSalesUtility;

    constructor(
        private workgroupService: WorkgroupStockAndSalesUtilityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workgroupService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'workgroupListModification',
                content: 'Deleted an workgroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-workgroup-stock-and-sales-utility-delete-popup',
    template: ''
})
export class WorkgroupStockAndSalesUtilityDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workgroupPopupService: WorkgroupStockAndSalesUtilityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.workgroupPopupService
                .open(WorkgroupStockAndSalesUtilityDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
