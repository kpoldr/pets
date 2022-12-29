import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { IJWTResponse } from '../domain/IJWTResponse';
import { IPet } from '../domain/IPet';
import jwt_decode from 'jwt-decode';

const headerDict = {
  'Content-Type': 'application/json',
  Accept: 'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
};

const requestOptions = {
  headers: new Headers(headerDict),
};

interface decodedJwt {

    sub: string
    roles: string[]

}


@Injectable({ providedIn: 'root' })
export class JwtService {
  constructor(private http: HttpClient, private cookieService: CookieService) {}


jwtDecoder(token: string)  {
  
    try {
      let jwt = jwt_decode(token) as decodedJwt;
        
      return { username: jwt.sub, roles: jwt.roles };
    } catch (Error) {
      return null;
    }
  }



  getUserPets(username : String, token: string) : Observable<IPet[]> {
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<IPet[]>(`http://localhost:8080/api/pets/${username}`,  {headers: headers});
  
  }

  loginUser(username: string, password: string) : Observable<IJWTResponse> {
    
    let jwt;
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post<IJWTResponse>('http://localhost:8080/login', body, { headers: headers });

      
  }

  refreshToken() {}
}
