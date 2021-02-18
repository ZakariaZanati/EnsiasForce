import { Component, OnInit, Input } from '@angular/core';
import {JobPayload} from '../shared/job.payload';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-job-description',
  templateUrl: './job-description.component.html',
  styleUrls: ['./job-description.component.css']
})
export class JobDescriptionComponent implements OnInit {

  @Input() jobOffer : JobPayload;

  constructor(private router : Router,private route : ActivatedRoute) { }

  ngOnInit(): void {
  }

  viewJobDetails(){
    this.router.navigate([this.jobOffer.id],{relativeTo : this.route})
  }

}
