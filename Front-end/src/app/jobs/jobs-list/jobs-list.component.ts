import { Component, OnInit } from '@angular/core';
import { JobPayload } from '../shared/job.payload';
import { JobsResponse } from '../shared/jobs-response';
import { JobService } from '../shared/job.service';
import { throwError } from 'rxjs';
import { AuthService } from '../../auth/shared/auth.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-jobs-list',
  templateUrl: './jobs-list.component.html',
  styleUrls: ['./jobs-list.component.css']
})
export class JobsListComponent implements OnInit {

  userType : string ;
  checked = false;
  hideForm = true;
  jobs : Array<JobPayload> = [];
  currentSelectedPage : number = 0;
  pageNumber : number = 0;
  totalPages : number = 0;
  pageIndexes : Array<number> = [];
  location : string = "";
  search : string = "";

  constructor(private jobService : JobService, 
              private authService : AuthService,
              private router : Router) { }

  ngOnInit(): void {
    this.getPage(0,6);
    this.userType = this.authService.getUserType();
  }

  onDisplayForm(){
    this.hideForm = false;
  }

  onFindJobs(form : NgForm){
    this.router.navigateByUrl('/jobs');
    this.location = form.value.location;
    this.search = form.value.search;
    console.log(this.search)

    if (this.search != "" || this.location != "") {
      this.getFilteredPage(0,6,this.location,this.search);
    } else {
      this.getPage(0,6);
    }
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

  getFilteredPage(page : number,size : number,location : string, name : string){
    this.jobService.filterJobsPage(page,size,name,location).subscribe((response : JobsResponse) => {
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
      if (this.search != "" || this.location != "") {
        this.getFilteredPage(++this.currentSelectedPage,6,this.location,this.search);
      } else {
        this.getPage(++this.currentSelectedPage,6);
      }
    }  
  }

  previousClick(){
    if(this.currentSelectedPage > 0){
      if (this.search != "" || this.location != "") {
        this.getFilteredPage(--this.currentSelectedPage,6,this.location,this.search);
      } else {
        this.getPage(--this.currentSelectedPage,6);
      }
    }  
  }

  getPaginationWithIndex(index: number) {
    if (this.search != "" || this.location != "") {
      this.getFilteredPage(index,6,this.location,this.search);
    } else {
      this.getPage(index,6);
    }
  }

  active(index: number) {
    if(this.currentSelectedPage == index ){
      return {
        active: true
      };
    }
  }



}
