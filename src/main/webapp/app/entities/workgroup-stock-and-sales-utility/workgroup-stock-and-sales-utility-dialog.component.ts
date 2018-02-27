import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WorkgroupStockAndSalesUtility } from './workgroup-stock-and-sales-utility.model';
import { WorkgroupStockAndSalesUtilityPopupService } from './workgroup-stock-and-sales-utility-popup.service';
import { WorkgroupStockAndSalesUtilityService } from './workgroup-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-workgroup-stock-and-sales-utility-dialog',
    templateUrl: './workgroup-stock-and-sales-utility-dialog.component.html'
})
export class WorkgroupStockAndSalesUtilityDialogComponent implements OnInit {

    workgroup: WorkgroupStockAndSalesUtility;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private workgroupService: WorkgroupStockAndSalesUtilityService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.workgroup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.workgroupService.update(this.workgroup));
        } else {
            this.subscribeToSaveResponse(
                this.workgroupService.create(this.workgroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<WorkgroupStockAndSalesUtility>>) {
        result.subscribe((res: HttpResponse<WorkgroupStockAndSalesUtility>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: WorkgroupStockAndSalesUtility) {
        this.eventManager.broadcast({ name: 'workgroupListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-workgroup-stock-and-sales-utility-popup',
    template: ''
})
export class WorkgroupStockAndSalesUtilityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workgroupPopupService: WorkgroupStockAndSalesUtilityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.workgroupPopupService
                    .open(WorkgroupStockAndSalesUtilityDialogComponent as Component, params['id']);
            } else {
                this.workgroupPopupService
                    .open(WorkgroupStockAndSalesUtilityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
