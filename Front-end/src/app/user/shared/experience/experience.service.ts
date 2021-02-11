import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExperiencePayload } from './experience.payload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {

  url : string = "http://localhost:8888/USER-SERVICE/api/experience";
  url2 : string = "http://localhost:8081/api/experience";

  constructor(private http : HttpClient) { }

  addUserExperience(experiencePayload : ExperiencePayload){
    return this.http.post(this.url2,experiencePayload);
  }

  getCurrentUserExperiences():Observable<ExperiencePayload[]>{
    return this.http.get<ExperiencePayload[]>(this.url2);
  }

  getUserExperiences(id : number):Observable<ExperiencePayload[]>{
    return this.http.get<ExperiencePayload[]>(this.url2+"/"+id);
  }
}
