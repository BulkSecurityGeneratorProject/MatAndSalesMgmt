import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UsrStockAndSalesUtility } from './usr-stock-and-sales-utility.model';
import { UsrStockAndSalesUtilityPopupService } from './usr-stock-and-sales-utility-popup.service';
import { UsrStockAndSalesUtilityService } from './usr-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-usr-stock-and-sales-utility-dialog',
    templateUrl: './usr-stock-and-sales-utility-dialog.component.html'
})
export class UsrStockAndSalesUtilityDialogComponent implements OnInit {

    usr: UsrStockAndSalesUtility;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private usrService: UsrStockAndSalesUtilityService,
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
        if (this.usr.id !== undefined) {
            this.subscribeToSaveResponse(
                this.usrService.update(this.usr));
        } else {
            this.subscribeToSaveResponse(
                this.usrService.create(this.usr));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UsrStockAndSalesUtility>>) {
        result.subscribe((res: HttpResponse<UsrStockAndSalesUtility>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UsrStockAndSalesUtility) {
        this.eventManager.broadcast({ name: 'usrListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-usr-stock-and-sales-utility-popup',
    template: ''
})
export class UsrStockAndSalesUtilityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usrPopupService: UsrStockAndSalesUtilityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.usrPopupService
                    .open(UsrStockAndSalesUtilityDialogComponent as Component, params['id']);
            } else {
                this.usrPopupService
                    .open(UsrStockAndSalesUtilityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
