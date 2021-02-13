import { Injectable } from '@angular/core';
import { HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  url : string = "http://localhost:8888/USER-SERVICE/api/skill";
  url2 : string = "http://localhost:8081/api/skill";

  constructor(private http : HttpClient) { }

  addUserSkill(skillName : string){
    let param = new HttpParams();
    param = param.append('skillName',skillName);
    return this.http.get(this.url2+"/save",{params : param})
  }

  getCurrentUserSkills():Observable<string[]>{
    return this.http.get<string[]>(this.url2);
  }

  getUserSkills(id : number):Observable<string[]>{
    return this.http.get<string[]>(this.url2+"/"+id);
  }
}
