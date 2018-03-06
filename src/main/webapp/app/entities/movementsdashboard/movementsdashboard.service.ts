import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Movementsdashboard } from './movementsdashboard.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Movementsdashboard>;

@Injectable()
export class MovementsdashboardService {

    private resourceUrl =  SERVER_API_URL + 'api/movementsdashboards';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/movementsdashboards';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(movementsdashboard: Movementsdashboard): Observable<EntityResponseType> {
        const copy = this.convert(movementsdashboard);
        return this.http.post<Movementsdashboard>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(movementsdashboard: Movementsdashboard): Observable<EntityResponseType> {
        const copy = this.convert(movementsdashboard);
        return this.http.put<Movementsdashboard>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Movementsdashboard>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Movementsdashboard[]>> {
        const options = createRequestOption(req);
        return this.http.get<Movementsdashboard[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Movementsdashboard[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Movementsdashboard[]>> {
        const options = createRequestOption(req);
        return this.http.get<Movementsdashboard[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Movementsdashboard[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Movementsdashboard = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Movementsdashboard[]>): HttpResponse<Movementsdashboard[]> {
        const jsonResponse: Movementsdashboard[] = res.body;
        const body: Movementsdashboard[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Movementsdashboard.
     */
    private convertItemFromServer(movementsdashboard: Movementsdashboard): Movementsdashboard {
        const copy: Movementsdashboard = Object.assign({}, movementsdashboard);
        copy.movementDate = this.dateUtils
            .convertLocalDateFromServer(movementsdashboard.movementDate);
        return copy;
    }

    /**
     * Convert a Movementsdashboard to a JSON which can be sent to the server.
     */
    private convert(movementsdashboard: Movementsdashboard): Movementsdashboard {
        const copy: Movementsdashboard = Object.assign({}, movementsdashboard);
        copy.movementDate = this.dateUtils
            .convertLocalDateToServer(movementsdashboard.movementDate);
        return copy;
    }
}
