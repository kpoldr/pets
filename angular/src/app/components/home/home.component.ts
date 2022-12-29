import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { IPet } from 'src/app/domain/IPet';
import { PetService } from 'src/app/service/pet.service';
import { decodeJwtToUser } from 'src/app/jwt/jwtDecoder';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/users.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(
    private petService: PetService,
    private userService: UserService,
    private router: Router
  ) {}

  pets: IPet[] = [];
  loading: boolean = true;

  deletePet(id: number, orderId: number): void {
    this.petService.deletePet(id).subscribe({
      complete: () => {
        this.pets.splice(orderId, 1);
      },
    });
  }

  checkWithRefresh(repeat: boolean) {
    this.petService.getUserPets().subscribe({
      next: (data) => {
        this.pets = data;
      },
      error: () => {
        if(this.userService.refreshToken() && repeat) {
          this.checkWithRefresh(false);
        } else {
          this.router.navigate(['login']);
        }
      },
      complete: () => {
        this.loading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loading = true;

    this.checkWithRefresh(true);
    
  }
}
