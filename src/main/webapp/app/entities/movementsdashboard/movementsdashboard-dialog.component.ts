import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Movementsdashboard } from './movementsdashboard.model';
import { MovementsdashboardPopupService } from './movementsdashboard-popup.service';
import { MovementsdashboardService } from './movementsdashboard.service';

@Component({
    selector: 'jhi-movementsdashboard-dialog',
    templateUrl: './movementsdashboard-dialog.component.html'
})
export class MovementsdashboardDialogComponent implements OnInit {

    movementsdashboard: Movementsdashboard;
    isSaving: boolean;
    movementDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private movementsdashboardService: MovementsdashboardService,
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
        if (this.movementsdashboard.id !== undefined) {
            this.subscribeToSaveResponse(
                this.movementsdashboardService.update(this.movementsdashboard));
        } else {
            this.subscribeToSaveResponse(
                this.movementsdashboardService.create(this.movementsdashboard));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Movementsdashboard>>) {
        result.subscribe((res: HttpResponse<Movementsdashboard>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Movementsdashboard) {
        this.eventManager.broadcast({ name: 'movementsdashboardListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-movementsdashboard-popup',
    template: ''
})
export class MovementsdashboardPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private movementsdashboardPopupService: MovementsdashboardPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.movementsdashboardPopupService
                    .open(MovementsdashboardDialogComponent as Component, params['id']);
            } else {
                this.movementsdashboardPopupService
                    .open(MovementsdashboardDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
