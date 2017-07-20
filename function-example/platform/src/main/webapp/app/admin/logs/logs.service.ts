import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Log } from './log.model';

@Injectable()
export class LogsService {
    constructor(private http: Http) { }

    changeLevel(log: Log): Observable<Response> {
        return this.http.put('resources/management/logs', log);
    }

    findAll(): Observable<Log[]> {
        return this.http.get('resources/management/logs').map((res: Response) => res.json());
    }
}
