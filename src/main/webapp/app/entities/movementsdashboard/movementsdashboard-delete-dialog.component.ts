import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Movementsdashboard } from './movementsdashboard.model';
import { MovementsdashboardPopupService } from './movementsdashboard-popup.service';
import { MovementsdashboardService } from './movementsdashboard.service';

@Component({
    selector: 'jhi-movementsdashboard-delete-dialog',
    templateUrl: './movementsdashboard-delete-dialog.component.html'
})
export class MovementsdashboardDeleteDialogComponent {

    movementsdashboard: Movementsdashboard;

    constructor(
        private movementsdashboardService: MovementsdashboardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.movementsdashboardService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'movementsdashboardListModification',
                content: 'Deleted an movementsdashboard'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-movementsdashboard-delete-popup',
    template: ''
})
export class MovementsdashboardDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private movementsdashboardPopupService: MovementsdashboardPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.movementsdashboardPopupService
                .open(MovementsdashboardDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
