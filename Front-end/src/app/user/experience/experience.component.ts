import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ExperiencePayload } from '../shared/experience/experience.payload';
import { ExperienceService } from '../shared/experience/experience.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  displayForm : boolean = false;
  experiencePayloadRequest = new ExperiencePayload();
  experiences : Array<ExperiencePayload> = [];
  current : boolean = true;
  userId : number;

  constructor(private experienceService : ExperienceService,private activatedRoute : ActivatedRoute, private authService : AuthService) { }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.params.id;
    if(this.userId && this.userId != this.authService.userID){
      this.current = false;
      this.experienceService.getUserExperiences(this.userId).subscribe(response => {
        this.experiences = response;
      })
    } else {
      this.current = true;
      this.experienceService.getCurrentUserExperiences().subscribe(response => {
        this.experiences = response;
      })
    }

    
  }

  showForm(){
    this.displayForm = !this.displayForm;
  }

  onSubmit(form : NgForm){

    if (!form.valid) {
      return;
    }

    this.experiencePayloadRequest.enterprise = form.value.enterprise;
    this.experiencePayloadRequest.position = form.value.post;
    this.experiencePayloadRequest.location = form.value.location;
    this.experiencePayloadRequest.startDate = form.value.debut_month+" "+form.value.debut_year;
    this.experiencePayloadRequest.endDate = form.value.end_month+" "+form.value.end_year;

    this.experienceService.addUserExperience(this.experiencePayloadRequest).subscribe(data => {
      this.ngOnInit();
    })
  }

}
