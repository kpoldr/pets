import { Component } from '@angular/core';
import { NavigationEnd, Route, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { filter } from 'rxjs';
import { decodeJwtToUser } from 'src/app/jwt/jwtDecoder';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  public loggedIn: boolean = false;
  public username: string = '';

  constructor(private cookieService: CookieService, private router: Router) {
    router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event) => {
        let route = event as NavigationEnd;
        
        if (route.url === '/login' || route.url === '/register') {
          console.log('it was this');
          this.loggedIn = false;
        } else {
          if (cookieService.check("refreshToken")) {
            let decodedJwt = decodeJwtToUser(cookieService.get("refreshToken"))
            if (decodedJwt?.username !== undefined) {
              this.username = decodedJwt.username
            }
            
          }
          this.loggedIn = true;
        }
      });

  }

  ngOnInit(): void {
    if (this.router.url === 'login' || this.router.url === '/register') {
      console.log('im in login or register');
    } else {
      console.log(this.router.url);
      console.log('not in them');
    }
  }

  public logout() {
    this.router.navigate(["login"])
    this.cookieService.delete("accessToken")
    this.cookieService.delete("refreshToken")
  }
}
