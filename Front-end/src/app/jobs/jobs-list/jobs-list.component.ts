import { Component, OnInit } from '@angular/core';
import { JobPayload } from '../shared/job.payload';
import { JobsResponse } from '../shared/jobs-response';
import { JobService } from '../shared/job.service';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-jobs-list',
  templateUrl: './jobs-list.component.html',
  styleUrls: ['./jobs-list.component.css']
})
export class JobsListComponent implements OnInit {

  userType : string = 'GRADUATE';
  checked = false;
  hideForm = true;
  jobs : Array<JobPayload> = [];
  currentSelectedPage : number = 0;
  pageNumber : number = 0;
  totalPages : number = 0;
  pageIndexes : Array<number> = [];

  constructor(private jobService : JobService) { }

  ngOnInit(): void {
    this.getPage(0,6);
  }

  onDisplayForm(){
    this.hideForm = false;
  }

  onFindJobs(){
    
  }

  getPage(page : number,size : number){
    this.jobService.getJobsPage(page,size).subscribe((response : JobsResponse) => {
      this.jobs = response.jobs;
      console.log(response)
      this.jobs.map(job => {
        
        this.totalPages = response.totalPages;
        this.pageIndexes = Array(this.totalPages).fill(0).map((x,i)=>i);
        this.currentSelectedPage = response.pageNumber;
      },err => throwError(err));
    })
  }

  nextClick(){
    if(this.currentSelectedPage < this.totalPages-1){
      this.getPage(++this.currentSelectedPage,8);
    }  
  }

  previousClick(){
    if(this.currentSelectedPage > 0){
      this.getPage(--this.currentSelectedPage,8);
    }  
  }

  getPaginationWithIndex(index: number) {
    this.getPage(index, 8);
  }

  active(index: number) {
    if(this.currentSelectedPage == index ){
      return {
        active: true
      };
    }
  }



}
