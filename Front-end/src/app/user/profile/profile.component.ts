import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { UserDetailsPayload } from '../shared/user-details.payload';

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

  constructor(private userService : UserService) { }

  ngOnInit(): void {

    this.userService.getCurrentUserDetails().subscribe(data => {
      this.userDetailsPayload = data;
      this.base64data = this.userDetailsPayload.image;
      this.profileImage = 'data:image/jpeg;base64,' + this.base64data;
    })

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

}
