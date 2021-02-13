import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormationPayload } from '../shared/formation/formation.payload';
import { FormationService } from '../shared/formation/formation.service';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  displayForm : boolean = false;
  formationPayloadRequest = new FormationPayload();
  formations : Array<FormationPayload> = [];

  constructor(private formationService : FormationService) { }

  ngOnInit(): void {
    this.formationService.getCurrentUserFormations().subscribe(reponse => {
        this.formations = reponse;
    })
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
