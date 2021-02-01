import { Component, OnInit } from '@angular/core';
import { LoginRequestPayload } from './login-request.payload';
import { LoginResponsePayload }from './login-response.payload';
import { AuthService} from '../shared/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequestPayload : LoginRequestPayload = new LoginRequestPayload();
  isError : Boolean = false;

  
  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit(form : NgForm){
    if (!form.valid) {
      return;
    }
    this.loginRequestPayload.email = form.value.email;
    this.loginRequestPayload.password = form.value.password;

    this.authService.login(this.loginRequestPayload).subscribe(data => {
      console.log(data)
      if (data) {
        if (this.authService.isCompleted()) {
          this.router.navigateByUrl('/');
        } else {
          this.router.navigateByUrl('/user-details');
        }
      } else {
        this.isError = true;
      }
    },error => {
      this.isError = true;
    })

    form.reset();

  }

}
