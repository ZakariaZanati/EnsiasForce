import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormationPayload } from '../shared/formation/formation.payload';
import { FormationService } from '../shared/formation/formation.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/shared/auth.service';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  displayForm : boolean = false;
  formationPayloadRequest = new FormationPayload();
  formations : Array<FormationPayload> = [];
  current : boolean = true;
  userId : number;

  constructor(private formationService : FormationService,private activatedRoute : ActivatedRoute, private authService : AuthService) { }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.params.id;
    if(this.userId && this.userId != this.authService.userID){
      this.current = false;
      this.formationService.getUserFormations(this.userId).subscribe(reponse => {
        this.formations = reponse;
      })
    } else {
      this.current = true;
      this.formationService.getCurrentUserFormations().subscribe(reponse => {
        this.formations = reponse;
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

    this.formationPayloadRequest.degree = form.value.degree;
    this.formationPayloadRequest.school = form.value.school;
    this.formationPayloadRequest.location = form.value.location;
    this.formationPayloadRequest.startDate = form.value.debut_month+" "+form.value.debut_year;
    this.formationPayloadRequest.endDate = form.value.end_month+" "+form.value.end_year;

    this.formationService.addUserFormation(this.formationPayloadRequest).subscribe(data => {
      this.ngOnInit();
    })
  }

}
