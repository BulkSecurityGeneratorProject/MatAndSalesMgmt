import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { WorkgroupStockAndSalesUtility } from './workgroup-stock-and-sales-utility.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<WorkgroupStockAndSalesUtility>;

@Injectable()
export class WorkgroupStockAndSalesUtilityService {

    private resourceUrl =  SERVER_API_URL + 'api/workgroups';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/workgroups';

    constructor(private http: HttpClient) { }

    create(workgroup: WorkgroupStockAndSalesUtility): Observable<EntityResponseType> {
        const copy = this.convert(workgroup);
        return this.http.post<WorkgroupStockAndSalesUtility>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(workgroup: WorkgroupStockAndSalesUtility): Observable<EntityResponseType> {
        const copy = this.convert(workgroup);
        return this.http.put<WorkgroupStockAndSalesUtility>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<WorkgroupStockAndSalesUtility>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<WorkgroupStockAndSalesUtility[]>> {
        const options = createRequestOption(req);
        return this.http.get<WorkgroupStockAndSalesUtility[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WorkgroupStockAndSalesUtility[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<WorkgroupStockAndSalesUtility[]>> {
        const options = createRequestOption(req);
        return this.http.get<WorkgroupStockAndSalesUtility[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WorkgroupStockAndSalesUtility[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: WorkgroupStockAndSalesUtility = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<WorkgroupStockAndSalesUtility[]>): HttpResponse<WorkgroupStockAndSalesUtility[]> {
        const jsonResponse: WorkgroupStockAndSalesUtility[] = res.body;
        const body: WorkgroupStockAndSalesUtility[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to WorkgroupStockAndSalesUtility.
     */
    private convertItemFromServer(workgroup: WorkgroupStockAndSalesUtility): WorkgroupStockAndSalesUtility {
        const copy: WorkgroupStockAndSalesUtility = Object.assign({}, workgroup);
        return copy;
    }

    /**
     * Convert a WorkgroupStockAndSalesUtility to a JSON which can be sent to the server.
     */
    private convert(workgroup: WorkgroupStockAndSalesUtility): WorkgroupStockAndSalesUtility {
        const copy: WorkgroupStockAndSalesUtility = Object.assign({}, workgroup);
        return copy;
    }
}
