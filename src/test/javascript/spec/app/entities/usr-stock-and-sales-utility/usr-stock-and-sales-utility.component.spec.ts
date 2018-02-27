/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MaterialAndStockManagementTestModule } from '../../../test.module';
import { UsrStockAndSalesUtilityComponent } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility.component';
import { UsrStockAndSalesUtilityService } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility.service';
import { UsrStockAndSalesUtility } from '../../../../../../main/webapp/app/entities/usr-stock-and-sales-utility/usr-stock-and-sales-utility.model';

describe('Component Tests', () => {

    describe('UsrStockAndSalesUtility Management Component', () => {
        let comp: UsrStockAndSalesUtilityComponent;
        let fixture: ComponentFixture<UsrStockAndSalesUtilityComponent>;
        let service: UsrStockAndSalesUtilityService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MaterialAndStockManagementTestModule],
                declarations: [UsrStockAndSalesUtilityComponent],
                providers: [
                    UsrStockAndSalesUtilityService
                ]
            })
            .overrideTemplate(UsrStockAndSalesUtilityComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UsrStockAndSalesUtilityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsrStockAndSalesUtilityService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UsrStockAndSalesUtility(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.usrs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
