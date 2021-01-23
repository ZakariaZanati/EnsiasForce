import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-job-form',
  templateUrl: './job-form.component.html',
  styleUrls: ['./job-form.component.css']
})
export class JobFormComponent implements OnInit {

  @Output() hideMe = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  hideForm(){
    this.hideMe.emit();
  }

  onSubmit(){

  }

}
