import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  displayForm : boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  showForm(){
    this.displayForm = !this.displayForm;
  }

}
