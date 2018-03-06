import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Movementsdashboard } from './movementsdashboard.model';
import { MovementsdashboardService } from './movementsdashboard.service';

@Injectable()
export class MovementsdashboardPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private movementsdashboardService: MovementsdashboardService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.movementsdashboardService.find(id)
                    .subscribe((movementsdashboardResponse: HttpResponse<Movementsdashboard>) => {
                        const movementsdashboard: Movementsdashboard = movementsdashboardResponse.body;
                        if (movementsdashboard.movementDate) {
                            movementsdashboard.movementDate = {
                                year: movementsdashboard.movementDate.getFullYear(),
                                month: movementsdashboard.movementDate.getMonth() + 1,
                                day: movementsdashboard.movementDate.getDate()
                            };
                        }
                        this.ngbModalRef = this.movementsdashboardModalRef(component, movementsdashboard);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.movementsdashboardModalRef(component, new Movementsdashboard());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    movementsdashboardModalRef(component: Component, movementsdashboard: Movementsdashboard): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.movementsdashboard = movementsdashboard;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
