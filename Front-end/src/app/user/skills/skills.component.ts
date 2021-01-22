import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  displayForm : boolean = false;
  skills = ['jAVA','JEE','Spring boot','Angular','C/C++','Node.js','MySql','MongoDB','Python']
  constructor() { }

  ngOnInit(): void {
  }

  showForm(){
    this.displayForm = !this.displayForm;
  }

}
