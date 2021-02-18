import { Component, OnInit } from '@angular/core';
import { JobPayload } from '../shared/job.payload';
import { JobService } from '../shared/job.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  id : number;
  jobOffer : JobPayload;

  constructor(private route : ActivatedRoute, private jobsService : JobService,private router : Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.id = +param.id;
      this.jobsService.getJobByID(this.id).subscribe(data => {
        this.jobOffer = data;
      })
    })
  }

  applyLink(){
    if (this.jobOffer.link) {
      window.location.href = 'http://'+this.jobOffer.link

    }
  }

}
