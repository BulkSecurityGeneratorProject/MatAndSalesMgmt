/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { WorkgroupStockAndSalesUtilityDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility-delete-dialog.component';
import { WorkgroupStockAndSalesUtilityService } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.service';

describe('Component Tests', () => {

    describe('WorkgroupStockAndSalesUtility Management Delete Component', () => {
        let comp: WorkgroupStockAndSalesUtilityDeleteDialogComponent;
        let fixture: ComponentFixture<WorkgroupStockAndSalesUtilityDeleteDialogComponent>;
        let service: WorkgroupStockAndSalesUtilityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [WorkgroupStockAndSalesUtilityDeleteDialogComponent],
                providers: [
                    WorkgroupStockAndSalesUtilityService
                ]
            })
            .overrideTemplate(WorkgroupStockAndSalesUtilityDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkgroupStockAndSalesUtilityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkgroupStockAndSalesUtilityService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
