import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { IJWTResponse } from 'src/app/domain/IJWTResponse';
import { UserService } from 'src/app/service/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(
    private userService: UserService,
    private cookieService: CookieService,
    private router: Router,
  ) {}

  validated: boolean = false;
  usernameValidation: number = 1;
  passwordValidation: number = 1;
  loginError: boolean = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  login() {
    this.usernameValidation = 2;
    this.passwordValidation = 2;
    this.validated = false;
    this.loginError = false;

    if (this.loginForm.get('username')?.valid) {
      this.usernameValidation = 3;
    }

    if (this.loginForm.get('password')?.valid) {
      this.passwordValidation = 3;
    }

    if (!this.loginForm.valid) {
      return;
    }

    let username = this.username.value;
    let password = this.password.value;

    this.userService.loginUser(username, password).subscribe({
      next: (data) => {
        let jwt = data as IJWTResponse;
        this.cookieService.set('username', jwt.username);
        this.cookieService.set('accessToken', jwt.accessToken);
        this.cookieService.set('refreshToken', jwt.refreshToken);
        this.router.navigate(['']);
      },
      error: (e) => {
        this.loginError = true;
        this.usernameValidation = 2;
        this.passwordValidation = 2;
      },
    });
  }

  get username() {
    return this.loginForm.get('username') as FormControl;
  }

  get password() {
    return this.loginForm.get('password') as FormControl;
  }
}
