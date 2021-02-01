import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/shared/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  isLoggedIn : boolean;
  fullName : String;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit(): void {

    this.authService.loggedIn.subscribe((data : boolean) => this.isLoggedIn = data);
    this.authService.fullName.subscribe((data : String) => this.fullName = data);

    this.isLoggedIn = this.authService.isLoggedIn();
    this.fullName = this.authService.getFullName();
  }

  logout(){
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('');
  }

  homePage(){
    this.router.navigateByUrl('');
  }

}
