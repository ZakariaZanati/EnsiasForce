import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SkillService } from '../shared/skill/skill.service';


@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  displayForm : boolean = true;
  skills = ['jAVA','JEE','Spring boot','Angular','C/C++','Node.js','MySql','MongoDB','Python']
  
  skills2 = [];
  
  constructor(private skillService : SkillService) { }

  ngOnInit(): void {
    this.skillService.getCurrentUserSkills().subscribe(response => {
      this.skills2 = response;
      console.log(this.skills2)
    })
  }

  showForm(){
    this.displayForm = !this.displayForm;
  }

  onSubmit(form : NgForm){
    if (!form.valid) {
      return;
    }

    this.skillService.addUserSkill(form.value.name).subscribe(()=>{
      this.ngOnInit();
    })

  }

}
