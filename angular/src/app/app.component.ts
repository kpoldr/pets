import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title: string = 'this is a test';

  constructor(
    private modalService: NgbModal,
    private cookieService: CookieService
  ) {}

  public open(modal: any): void {
    this.modalService.open(modal);
  }

  private cookie_name = '';
  private all_cookies: any = '';

  setCookie() {
    this.cookieService.set('name', 'Tutorialswebsite');
  }

  deleteCookie() {
    this.cookieService.delete('name');
  }

  deleteAll() {
    this.cookieService.deleteAll();
  }

  ngOnInit(): void {
    this.cookie_name = this.cookieService.get('name');
    this.all_cookies = this.cookieService.getAll(); // get all cookies object
  }
}
