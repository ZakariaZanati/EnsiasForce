import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignupRequestPayload } from './signup-request.payload';
import { AuthService } from '../shared/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestPayload : SignupRequestPayload = new SignupRequestPayload();
  isError : boolean = false;
  error : string = null;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit(form : NgForm){

    if (form.value.confirmation != form.value.password) {
      this.isError = true;
      this.error = "Password confirmation error ! Please try again";
      return;
    }

    this.signupRequestPayload.email = form.value.email;
    this.signupRequestPayload.password = form.value.password;
    this.signupRequestPayload.status = form.value.status;
    this.signupRequestPayload.fullName = form.value.fullName;
    this.signupRequestPayload.completed = false;


    this.authService.signup(this.signupRequestPayload)
      .subscribe(()=>{

        this.authService.addRoleToUser(form.value.email,form.value.userType)
          .subscribe((response => {
            console.log(response);
          }))

        this.router.navigate(['/signin'],
          {queryParams:{registered:'true'}})
          
      },()=>{
        this.isError = true;
        this.error = 'Email or Username already used';
      })

  }

}
