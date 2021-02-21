import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/shared/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../user/shared/user.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  isLoggedIn : boolean;
  fullName : String;

  base64data : any;
  profileImage : any;

  constructor(private authService : AuthService, private router : Router,private userService : UserService) { }

  ngOnInit(): void {

    this.authService.loggedIn.subscribe((data : boolean) => this.isLoggedIn = data);
    this.authService.fullName.subscribe((data : String) => this.fullName = data);

    this.isLoggedIn = this.authService.isLoggedIn();
    this.fullName = this.authService.getFullName();

    if (this.isLoggedIn) {
        this.userService.getCurrentUserDetails().subscribe(data => {
        this.base64data = data.image;
        if(this.base64data){
          this.profileImage = 'data:image/jpeg;base64,' + this.base64data;
        }
      })
    }


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
