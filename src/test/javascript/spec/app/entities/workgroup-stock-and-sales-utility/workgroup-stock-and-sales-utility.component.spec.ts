/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { WorkgroupStockAndSalesUtilityComponent } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.component';
import { WorkgroupStockAndSalesUtilityService } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.service';
import { WorkgroupStockAndSalesUtility } from '../../../../../../main/webapp/app/entities/workgroup-stock-and-sales-utility/workgroup-stock-and-sales-utility.model';

describe('Component Tests', () => {

    describe('WorkgroupStockAndSalesUtility Management Component', () => {
        let comp: WorkgroupStockAndSalesUtilityComponent;
        let fixture: ComponentFixture<WorkgroupStockAndSalesUtilityComponent>;
        let service: WorkgroupStockAndSalesUtilityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [WorkgroupStockAndSalesUtilityComponent],
                providers: [
                    WorkgroupStockAndSalesUtilityService
                ]
            })
            .overrideTemplate(WorkgroupStockAndSalesUtilityComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkgroupStockAndSalesUtilityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkgroupStockAndSalesUtilityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new WorkgroupStockAndSalesUtility(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.workgroups[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
