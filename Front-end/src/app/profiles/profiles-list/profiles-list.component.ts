import { Component, OnInit } from '@angular/core';
import { ProfilePayload } from '../shared/profile.payload';
import { ProflesResponse } from '../shared/profiles-reponse';
import { ProfilesService } from '../shared/profiles.service';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-profiles-list',
  templateUrl: './profiles-list.component.html',
  styleUrls: ['./profiles-list.component.css']
})
export class ProfilesListComponent implements OnInit {


  name : string = "";
  location : string = "";
  currentJob : string = "";

  profiles : Array<ProfilePayload> = [];

  currentSelectedPage : number = 0;
  pageNumber : number = 0;
  totalPages : number = 0;
  pageIndexes : Array<number> = [];

  constructor(private profileService : ProfilesService, private router : Router) { }

  ngOnInit(): void {
    this.getPage(0,10,this.name,this.location);
  }

  getPage(page : number,size : number,name : string,location : string){
    this.profileService.getUsersPage(page,size,name,location).subscribe((response : ProflesResponse) => {
      console.log(response)
      this.profiles = response.users;
      this.profiles.map(user => {
        
        const img = user.image;
        if (img) {
          user.image = "data:image/jpeg;base64,"+user.image;
        }
        this.totalPages = response.totalPages;
        this.pageIndexes = Array(this.totalPages).fill(0).map((x,i)=>i);
        this.currentSelectedPage = response.pageNumber;
      },err => throwError(err));
    })
  }


  nextClick(){
    if(this.currentSelectedPage < this.totalPages-1){
      this.getPage(++this.currentSelectedPage,10,this.name,this.location);
    }  
  }

  previousClick(){
    if(this.currentSelectedPage > 0){
      this.getPage(--this.currentSelectedPage,10,this.name,this.location);
    }  
  }

  getPaginationWithIndex(index: number) {
    this.getPage(index, 10,this.name,this.location);
  }

  active(index: number) {
    if(this.currentSelectedPage == index ){
      return {
        active: true
      };
    }
  }

  onNameChange(name : string){
    this.name = name;
    this.getPage(0,10,this.name,this.location);
  }

  onLocationChange(location : string){
    this.location = location;
    this.getPage(0,10,this.name,this.location);
  }

  viewProfile(id : number){
    this.router.navigateByUrl('/profile/'+id);
  }

}
