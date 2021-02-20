import { Injectable } from '@angular/core';
import { ProfilePayload } from './profile.payload';
import { ProflesResponse } from './profiles-reponse';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService {

  constructor(private http : HttpClient) { }

  getUsersPage(page : number,size : number,name : string, location : string):Observable<ProflesResponse>{

    let params = new HttpParams();
    params = params.append('page',page.toString());
    params = params.append('size',size.toString());
    params = params.append('name',name);
    params = params.append('location',location);

    return this.http.get<ProflesResponse>('http://localhost:8081/api/user/list',{params : params});
  }
}
