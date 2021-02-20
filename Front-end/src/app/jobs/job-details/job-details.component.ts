import { Component, OnInit } from '@angular/core';
import { JobPayload } from '../shared/job.payload';
import { JobService } from '../shared/job.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../user/shared/user.service';
import { UserDetailsPayload } from '../../user/shared/user-details.payload';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  id : number;
  jobOffer : JobPayload;
  author : UserDetailsPayload

  constructor(private route : ActivatedRoute, 
              private jobsService : JobService,
              private router : Router,
              private userService : UserService ) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.id = +param.id;
      this.jobsService.getJobByID(this.id).subscribe(data => {
        this.jobOffer = data;

        this.userService.getUserDetails(this.jobOffer.userID).subscribe(response => {
          
          this.author = response;
          this.author.image = 'data:image/jpeg;base64,' + response.image;
        });

      })
    })


  }

  applyLink(){
    if (this.jobOffer.link) {
      window.location.href = 'http://'+this.jobOffer.link

    }
  }

}
