/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { MovementsdashboardComponent } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.component';
import { MovementsdashboardService } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.service';
import { Movementsdashboard } from '../../../../../../main/webapp/app/entities/movementsdashboard/movementsdashboard.model';

describe('Component Tests', () => {

    describe('Movementsdashboard Management Component', () => {
        let comp: MovementsdashboardComponent;
        let fixture: ComponentFixture<MovementsdashboardComponent>;
        let service: MovementsdashboardService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [MovementsdashboardComponent],
                providers: [
                    MovementsdashboardService
                ]
            })
            .overrideTemplate(MovementsdashboardComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MovementsdashboardComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MovementsdashboardService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Movementsdashboard(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.movementsdashboards[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
