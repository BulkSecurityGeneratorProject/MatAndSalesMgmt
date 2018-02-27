import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UsrStockAndSalesUtility } from './usr-stock-and-sales-utility.model';
import { UsrStockAndSalesUtilityService } from './usr-stock-and-sales-utility.service';

@Component({
    selector: 'jhi-usr-stock-and-sales-utility-detail',
    templateUrl: './usr-stock-and-sales-utility-detail.component.html'
})
export class UsrStockAndSalesUtilityDetailComponent implements OnInit, OnDestroy {

    usr: UsrStockAndSalesUtility;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private usrService: UsrStockAndSalesUtilityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUsrs();
    }

    load(id) {
        this.usrService.find(id)
            .subscribe((usrResponse: HttpResponse<UsrStockAndSalesUtility>) => {
                this.usr = usrResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUsrs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'usrListModification',
            (response) => this.load(this.usr.id)
        );
    }
}
