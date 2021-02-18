import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { JobPayload } from '../shared/job.payload';
import { JobService } from '../shared/job.service';
import { AuthService } from '../../auth/shared/auth.service';

@Component({
  selector: 'app-job-form',
  templateUrl: './job-form.component.html',
  styleUrls: ['./job-form.component.css']
})
export class JobFormComponent implements OnInit {

  @Output() hideMe = new EventEmitter<any>();

  jobPayload = new JobPayload();

  constructor(private jobService : JobService,private authService : AuthService) { }

  ngOnInit(): void {
  }

  hideForm(){
    this.hideMe.emit();
  }

  onSubmit(form : NgForm){
    
    if (!form.valid) {
      return;
    }

    this.jobPayload.name = form.value.post;
    this.jobPayload.enterprise = form.value.enterprise;
    this.jobPayload.contractType = form.value.contractType;
    this.jobPayload.location = form.value.location;
    this.jobPayload.description = form.value.description;
    this.jobPayload.qualifications = form.value.qualifications;
    this.jobPayload.salary = form.value.salary;
    this.jobPayload.link = form.value.link;
    this.jobPayload.userID = this.authService.userID;
    this.jobPayload.creationDate = new Date().toISOString();
    this.jobPayload.expirationDate = new Date(form.value.expirationDate).toISOString();

    console.log(this.jobPayload);
    
    this.jobService.createJobOffer(this.jobPayload).subscribe(()=>{
      console.log("job offer added successfully");
    },err => {
      console.error(err);
    })
    
  }

}
