import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jobs-list',
  templateUrl: './jobs-list.component.html',
  styleUrls: ['./jobs-list.component.css']
})
export class JobsListComponent implements OnInit {

  userType : string = 'GRADUATE';
  checked = false;
  hideForm = true;

  constructor() { }

  ngOnInit(): void {
  }

  onDisplayForm(){
    this.hideForm = false;
  }

  onFindJobs(){
    
  }



}
