import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { IJWTResponse } from '../domain/IJWTResponse';

const headerDict = {
  'Content-Type': 'application/json',
  Accept: 'application/json',
  'Access-Control-Allow-Headers': 'Content-Type',
};

const requestOptions = {
  headers: new Headers(headerDict),
};

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient, private cookieService: CookieService,  private router: Router) {}

  loginUser(username: string, password: string): Observable<IJWTResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);


    return this.http
      .post<IJWTResponse>('http://localhost:8080/login', body, {
        headers: headers,
      });
    
  }

  refreshToken() {

    if(!this.cookieService.check('refreshToken')) {
      this.router.navigate(['login'])
      return false;
    }

    let refreshed = false;

    let refreshToken = this.cookieService.get('refreshToken');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${refreshToken}`,
    });

    this.http
      .get<IJWTResponse>('http://localhost:8080/api/token/refresh', {
        headers: headers,
      })
      .subscribe({
        next: (data) => {
          let jwt = data as IJWTResponse;
          this.cookieService.set('username', jwt.username);
          this.cookieService.set('accessToken', jwt.accessToken);
          this.cookieService.set('refreshToken', jwt.refreshToken);
          
        },
        
        complete: () => {refreshed = true}
      });

      return refreshed;
  }
}
