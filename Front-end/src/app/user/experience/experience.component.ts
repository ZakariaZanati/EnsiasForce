import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ExperiencePayload } from '../shared/experience/experience.payload';
import { ExperienceService } from '../shared/experience/experience.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  displayForm : boolean = false;
  experiencePayloadRequest = new ExperiencePayload();
  experiences : Array<ExperiencePayload> = []

  constructor(private experienceService : ExperienceService) { }

  ngOnInit(): void {
    this.experienceService.getCurrentUserExperiences().subscribe(response => {
      this.experiences = response;
    })
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
