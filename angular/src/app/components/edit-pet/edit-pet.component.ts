import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IPet } from 'src/app/domain/IPet';
import { IPetColor } from 'src/app/domain/IPetColor';
import { IPetExtra } from 'src/app/domain/IPetExtra';
import { PetService } from 'src/app/service/pet.service';
import { UserService } from 'src/app/service/users.service';

@Component({
  selector: 'app-edit-pet',
  templateUrl: './edit-pet.component.html',
  styleUrls: ['./edit-pet.component.css'],
})
export class EditPetComponent {
  constructor(
    private petService: PetService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  loading: boolean = true;

  petId: number = 0;
  types: IPetColor[] = [];
  colors: IPetColor[] = [];
  countries: IPetColor[] = [];

  pet: IPet = { name: '', idCode: '', type: '', color: '', country: '' };
  nameValidation: number = 1;
  idCodeValidation: number = 1;
  typeValidation: number = 1;
  colorValidation: number = 1;
  countryValidation: number = 1;

  validated: boolean = false;

  petForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(32)]),
    idCode: new FormControl('', [
      Validators.required,
      Validators.maxLength(16),
    ]),
    type: new FormControl('', [Validators.required]),
    color: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
  });


  checkWithRefresh(repeat: boolean) {
    console.log("lamar fail")
    this.petService.getPetExtraInfo().subscribe({
      next: (data: IPetExtra) => {
        this.types = data.types;
        this.colors = data.colors;
        this.countries = data.countries;
      },
      error: () => {
        if(this.userService.refreshToken() && repeat) {
          this.checkWithRefresh(false);
        } 
      },
      complete: () => {
        this.loading = false;
      },
    });
  }


  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.petId = params['id'];
    });
    
    this.checkWithRefresh(true);

    this.petService.getPet(this.petId).subscribe({
      next: (data: IPet) => {
        if (data === null) {
          console.log("did not found")
          this.router.navigate(['pet/notFound']);
        }
        this.name.setValue(data.name);
        this.idCode.setValue(data.idCode);
        this.type.setValue(data.type);
        this.color.setValue(data.color);
        this.country.setValue(data.country);
      },
      error: () => {
        this.router.navigate(['pet/notFound']);
      },
      complete: () => {
        this.loading = false;
      },
    });
  }

  tryAdd() {
    this.validated = false;

    this.nameValidation = 2;
    this.idCodeValidation = 2;
    this.typeValidation = 2;
    this.colorValidation = 2;
    this.countryValidation = 2;

    if (this.name.valid) {
      this.nameValidation = 3;
    }
    if (this.idCode.valid) {
      this.idCodeValidation = 3;
    }
    if (this.type.valid) {
      this.typeValidation = 3;
    }
    if (this.color.valid) {
      this.colorValidation = 3;
    }
    if (this.country.valid) {
      this.countryValidation = 3;
    }

    if (!this.petForm.valid) {
      return;
    }

    this.petService
      .updatePet(
        {
          name: this.name.value,
          idCode: this.idCode.value,
          type: this.type.value,
          color: this.color.value,
          country: this.country.value,
        },
        this.petId
      )
      .subscribe({
        error: (e) => console.log(e),
        complete: () => this.router.navigate(['']),
      });

    console.log(this.name.value);
    console.log(this.idCode.value);
    console.log(this.type.value);
    console.log(this.color.value);
    console.log(this.country.value);
  }

  get name() {
    return this.petForm.get('name') as FormControl;
  }

  get idCode() {
    return this.petForm.get('idCode') as FormControl;
  }
  get type() {
    return this.petForm.get('type') as FormControl;
  }
  get color() {
    return this.petForm.get('color') as FormControl;
  }
  get country() {
    return this.petForm.get('country') as FormControl;
  }
}
