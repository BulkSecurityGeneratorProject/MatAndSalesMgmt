/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { WorkgroupStockAndSalesUtilityDialogComponent } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility-dialog.component';
import { WorkgroupStockAndSalesUtilityService } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.service';
import { WorkgroupStockAndSalesUtility } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.model';

describe('Component Tests', () => {

    describe('WorkgroupStockAndSalesUtility Management Dialog Component', () => {
        let comp: WorkgroupStockAndSalesUtilityDialogComponent;
        let fixture: ComponentFixture<WorkgroupStockAndSalesUtilityDialogComponent>;
        let service: WorkgroupStockAndSalesUtilityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [WorkgroupStockAndSalesUtilityDialogComponent],
                providers: [
                    WorkgroupStockAndSalesUtilityService
                ]
            })
            .overrideTemplate(WorkgroupStockAndSalesUtilityDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkgroupStockAndSalesUtilityDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkgroupStockAndSalesUtilityService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WorkgroupStockAndSalesUtility(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.workgroup = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'workgroupListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WorkgroupStockAndSalesUtility();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.workgroup = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'workgroupListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
