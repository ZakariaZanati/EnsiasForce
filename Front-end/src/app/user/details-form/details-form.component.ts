import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { AuthService } from '../../auth/shared/auth.service';
import { UserDetailsPayload } from '../shared/user-details.payload';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details-form',
  templateUrl: './details-form.component.html',
  styleUrls: ['./details-form.component.css']
})
export class DetailsFormComponent implements OnInit {

  userDetailsPayload : UserDetailsPayload = new UserDetailsPayload();
  completed : boolean;

  constructor(private userService : UserService, private router : Router, private authService : AuthService) { }

  ngOnInit(): void {

    this.userService.getCurrentUserDetails().subscribe(data => {
      this.userDetailsPayload = data;
    })

    this.completed = this.authService.isCompleted();
  }

  onSubmit(form : NgForm){

    if(!form.valid){
      return;
    }

    this.userDetailsPayload.fullName = form.value.fullName;
    this.userDetailsPayload.status = form.value.status;
    this.userDetailsPayload.city = form.value.city;
    this.userDetailsPayload.country = form.value.country;
    this.userDetailsPayload.phoneNumber = form.value.phoneNumber;
    this.userDetailsPayload.birthDate = form.value.birthDate;

    this.userService.saveUserDetails(this.userDetailsPayload).subscribe(data => {
      console.log(data)
      this.router.navigateByUrl('/profile');
    },err => {
      console.log(err);
    })
  }



}
