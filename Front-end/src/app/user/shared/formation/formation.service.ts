import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormationPayload } from './formation.payload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormationService {

  url : string = "http://localhost:8888/USER-SERVICE/api/formation";
  url2 : string = "http://localhost:8081/api/formation";

  constructor(private http : HttpClient) { }

  addUserFormation(formationPayload : FormationPayload){
    return this.http.post(this.url2,formationPayload);
  }

  getCurrentUserFormations():Observable<FormationPayload[]>{
    return this.http.get<FormationPayload[]>(this.url2);
  }

  getUserFormations(id : number):Observable<FormationPayload[]>{
    return this.http.get<FormationPayload[]>(this.url2+"/"+id);
  }



}
