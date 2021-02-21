import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { UserDetailsPayload } from '../shared/user-details.payload';
import { Router,ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  userDetailsPayload : UserDetailsPayload = new UserDetailsPayload();
  base64data : any;
  profileImage : any;
  selectedFile : File;
  userId : number;
  current : boolean = true;
  constructor(private userService : UserService, private router : Router,private activatedRoute : ActivatedRoute, private authService : AuthService) { }

  ngOnInit(): void {

    this.userId = this.activatedRoute.snapshot.params.id;
    console.log(this.userId +" "+this.authService.userID)
    if(this.userId && this.userId != this.authService.userID){
      this.current = false;
      this.userService.getUserDetails(this.userId).subscribe(data => {
        this.userDetailsPayload = data;
        this.base64data = this.userDetailsPayload.image;
        if(this.base64data){
          this.profileImage = 'data:image/jpeg;base64,' + this.base64data;
        }
        
      })
    } else {
      this.current = true;
      this.userService.getCurrentUserDetails().subscribe(data => {
        this.userDetailsPayload = data;
        this.base64data = this.userDetailsPayload.image;
        if(this.base64data){
          this.profileImage = 'data:image/jpeg;base64,' + this.base64data;
        }
        
      })
    }
    

  }

  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
    this.onUpload();

  }

  onUpload(){

    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.userService.saveUserImage(uploadImageData).subscribe(response => {
      if (response.status === 200) {
        console.log("image uploaded")
        window.location.reload()
        
      }
      
    })
  }

  updatePage(){
    this.router.navigateByUrl("/user-details")
  }

}
