import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UsrStockAndSalesUtility } from './usr-stock-and-sales-utility.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UsrStockAndSalesUtility>;

@Injectable()
export class UsrStockAndSalesUtilityService {

    private resourceUrl =  SERVER_API_URL + 'api/usrs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/usrs';

    constructor(private http: HttpClient) { }

    create(usr: UsrStockAndSalesUtility): Observable<EntityResponseType> {
        const copy = this.convert(usr);
        return this.http.post<UsrStockAndSalesUtility>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(usr: UsrStockAndSalesUtility): Observable<EntityResponseType> {
        const copy = this.convert(usr);
        return this.http.put<UsrStockAndSalesUtility>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UsrStockAndSalesUtility>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UsrStockAndSalesUtility[]>> {
        const options = createRequestOption(req);
        return this.http.get<UsrStockAndSalesUtility[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UsrStockAndSalesUtility[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<UsrStockAndSalesUtility[]>> {
        const options = createRequestOption(req);
        return this.http.get<UsrStockAndSalesUtility[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UsrStockAndSalesUtility[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UsrStockAndSalesUtility = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UsrStockAndSalesUtility[]>): HttpResponse<UsrStockAndSalesUtility[]> {
        const jsonResponse: UsrStockAndSalesUtility[] = res.body;
        const body: UsrStockAndSalesUtility[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UsrStockAndSalesUtility.
     */
    private convertItemFromServer(usr: UsrStockAndSalesUtility): UsrStockAndSalesUtility {
        const copy: UsrStockAndSalesUtility = Object.assign({}, usr);
        return copy;
    }

    /**
     * Convert a UsrStockAndSalesUtility to a JSON which can be sent to the server.
     */
    private convert(usr: UsrStockAndSalesUtility): UsrStockAndSalesUtility {
        const copy: UsrStockAndSalesUtility = Object.assign({}, usr);
        return copy;
    }
}
