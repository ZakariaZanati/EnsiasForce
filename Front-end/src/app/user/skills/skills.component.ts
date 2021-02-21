import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SkillService } from '../shared/skill/skill.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  displayForm : boolean = false;
  skills = ['jAVA','JEE','Spring boot','Angular','C/C++','Node.js','MySql','MongoDB','Python']
  
  skills2 = [];
  current : boolean = true;
  userId : number;
  
  constructor(private skillService : SkillService,private activatedRoute : ActivatedRoute, private authService : AuthService) { }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.params.id;
    if(this.userId && this.userId != this.authService.userID){
      this.current = false;
      this.skillService.getUserSkills(this.userId).subscribe(response => {
        this.skills2 = response;
        console.log(this.skills2)
      })
    } else {
      this.current = true;
      this.skillService.getCurrentUserSkills().subscribe(response => {
        this.skills2 = response;
        console.log(this.skills2)
      })
    }
    
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
