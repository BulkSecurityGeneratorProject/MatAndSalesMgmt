import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Movementsdashboard } from './movementsdashboard.model';
import { MovementsdashboardService } from './movementsdashboard.service';

@Component({
    selector: 'jhi-movementsdashboard-detail',
    templateUrl: './movementsdashboard-detail.component.html'
})
export class MovementsdashboardDetailComponent implements OnInit, OnDestroy {

    movementsdashboard: Movementsdashboard;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private movementsdashboardService: MovementsdashboardService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMovementsdashboards();
    }

    load(id) {
        this.movementsdashboardService.find(id)
            .subscribe((movementsdashboardResponse: HttpResponse<Movementsdashboard>) => {
                this.movementsdashboard = movementsdashboardResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMovementsdashboards() {
        this.eventSubscriber = this.eventManager.subscribe(
            'movementsdashboardListModification',
            (response) => this.load(this.movementsdashboard.id)
        );
    }
}
