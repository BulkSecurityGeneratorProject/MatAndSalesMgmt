/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { UsrStockAndSalesUtilityDetailComponent } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility-detail.component';
import { UsrStockAndSalesUtilityService } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility.service';
import { UsrStockAndSalesUtility } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility.model';

describe('Component Tests', () => {

    describe('UsrStockAndSalesUtility Management Detail Component', () => {
        let comp: UsrStockAndSalesUtilityDetailComponent;
        let fixture: ComponentFixture<UsrStockAndSalesUtilityDetailComponent>;
        let service: UsrStockAndSalesUtilityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [UsrStockAndSalesUtilityDetailComponent],
                providers: [
                    UsrStockAndSalesUtilityService
                ]
            })
            .overrideTemplate(UsrStockAndSalesUtilityDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UsrStockAndSalesUtilityDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsrStockAndSalesUtilityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UsrStockAndSalesUtility(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.usr).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
