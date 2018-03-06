/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { MovementsdashboardDialogComponent } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard-dialog.component';
import { MovementsdashboardService } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.service';
import { Movementsdashboard } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.model';

describe('Component Tests', () => {

    describe('Movementsdashboard Management Dialog Component', () => {
        let comp: MovementsdashboardDialogComponent;
        let fixture: ComponentFixture<MovementsdashboardDialogComponent>;
        let service: MovementsdashboardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [MovementsdashboardDialogComponent],
                providers: [
                    MovementsdashboardService
                ]
            })
            .overrideTemplate(MovementsdashboardDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MovementsdashboardDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MovementsdashboardService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Movementsdashboard(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.movementsdashboard = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'movementsdashboardListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Movementsdashboard();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.movementsdashboard = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'movementsdashboardListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
