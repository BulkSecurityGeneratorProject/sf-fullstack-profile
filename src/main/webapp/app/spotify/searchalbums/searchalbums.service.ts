import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from '../../app.constants';
import {Albums} from './albums-modal-service';

@Injectable()
export class SearchalbumsService {

    private usersUrl: string = SERVER_API_URL + 'api/spotify/albums';

    constructor(private http: HttpClient) {
    }

    query(muse: string): Observable<HttpResponse<Albums[]>> {
        const url = `${this.usersUrl}/${muse}`;
        return this.http.get<Albums[]>(url, {observe: 'response'})
            .map((res: HttpResponse<Albums[]>) => this.convertArrayResponse(res));
    }

    private convertArrayResponse(res: HttpResponse<Albums[]>): HttpResponse<Albums[]> {
        const jsonResponse: Albums[] = res.body;
        const body: Albums[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Album.
     */
    private convertItemFromServer(album: Albums): Albums {
        const copy: Albums = Object.assign({}, album);
        return copy;
    }

    /**
     * Convert a Album to a JSON which can be sent to the server.
     */
    private convert(album: Albums): Albums {
        const copy: Albums = Object.assign({}, album);
        return copy;
    }

}
