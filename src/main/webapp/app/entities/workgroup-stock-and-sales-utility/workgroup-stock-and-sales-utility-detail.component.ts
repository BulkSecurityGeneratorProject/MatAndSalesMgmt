import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { WorkgroupStockAndSalesUtility } from './workgroup-stock-and-sales-utility.model';
import { WorkgroupStockAndSalesUtilityService } from './workgroup-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-workgroup-stock-and-sales-utility-detail',
    templateUrl: './workgroup-stock-and-sales-utility-detail.component.html'
})
export class WorkgroupStockAndSalesUtilityDetailComponent implements OnInit, OnDestroy {

    workgroup: WorkgroupStockAndSalesUtility;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private workgroupService: WorkgroupStockAndSalesUtilityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWorkgroups();
    }

    load(id) {
        this.workgroupService.find(id)
            .subscribe((workgroupResponse: HttpResponse<WorkgroupStockAndSalesUtility>) => {
                this.workgroup = workgroupResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWorkgroups() {
        this.eventSubscriber = this.eventManager.subscribe(
            'workgroupListModification',
            (response) => this.load(this.workgroup.id)
        );
    }
}
