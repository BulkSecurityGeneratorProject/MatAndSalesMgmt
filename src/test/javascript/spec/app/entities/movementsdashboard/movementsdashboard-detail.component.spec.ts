/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { MovementsdashboardDetailComponent } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard-detail.component';
import { MovementsdashboardService } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.service';
import { Movementsdashboard } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.model';

describe('Component Tests', () => {

    describe('Movementsdashboard Management Detail Component', () => {
        let comp: MovementsdashboardDetailComponent;
        let fixture: ComponentFixture<MovementsdashboardDetailComponent>;
        let service: MovementsdashboardService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [MovementsdashboardDetailComponent],
                providers: [
                    MovementsdashboardService
                ]
            })
            .overrideTemplate(MovementsdashboardDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MovementsdashboardDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MovementsdashboardService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Movementsdashboard(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.movementsdashboard).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
