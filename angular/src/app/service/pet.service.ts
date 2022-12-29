import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { IPet } from '../domain/IPet';
import { IPetExtra } from '../domain/IPetExtra';
import { decodeJwtToUser } from '../jwt/jwtDecoder';

const headerDict = {
  'Content-Type': 'application/json',
  Accept: 'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
};

const requestOptions = {
  headers: new Headers(headerDict),
};

@Injectable({ providedIn: 'root' })
export class PetService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {}

  getAuthHeader(): HttpHeaders {
    let accessToken = this.cookieService.get('accessToken');

    return new HttpHeaders({
      Authorization: `Bearer ${accessToken}`,
    });
  }

  getUserPets(): Observable<IPet[]> {
    let accessToken = this.cookieService.get('accessToken');

    let user = decodeJwtToUser(accessToken);

    return this.http.get<IPet[]>(
      `http://localhost:8080/api/pets/${user?.username}`,
      {
        headers: this.getAuthHeader(),
      }
    );
  }

  getPet(id: number): Observable<IPet> {
    
    return this.http.get<IPet>(
      `http://localhost:8080/api/pet/${id}`,
      {
        headers: this.getAuthHeader(),
      }
    );
  }

  getPetExtraInfo(): Observable<IPetExtra> {
    return this.http.get<IPetExtra>(`http://localhost:8080/api/pet/extra`, {
      headers: this.getAuthHeader(),
    });
  }

  insertPet(pet: IPet): Observable<IPet> {
    let accessToken = this.cookieService.get('accessToken');

    let user = decodeJwtToUser(accessToken);

    return this.http.post<IPet>(
      `http://localhost:8080/api/pet/${user?.username}`,
      pet,
      {
        headers: this.getAuthHeader(),
      }
    );
  }


  updatePet(pet: IPet, id: number): Observable<IPet> {

    return this.http.put<IPet>(
      `http://localhost:8080/api/pet/${id}`,
      pet,
      {
        headers: this.getAuthHeader(),
      }
    );
  }


  deletePet(id: number) {
    let accessToken = this.cookieService.get('accessToken');

    let user = decodeJwtToUser(accessToken);

    return this.http.delete<IPet>(
      `http://localhost:8080/api/pet/${id}`,
      {
        headers: this.getAuthHeader(),
      }
    );
  }
}
