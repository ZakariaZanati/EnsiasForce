import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetailsPayload } from './user-details.payload';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  url : string = "http://localhost:8888/USER-SERVICE/api/user";
  url2 : string = "http://localhost:8081/api/user";

  constructor(private http : HttpClient) { }

  saveUserDetails(userDetailsPayload : UserDetailsPayload){
    return this.http.post(this.url2,userDetailsPayload);
  }

  getCurrentUserDetails():Observable<UserDetailsPayload>{
    return this.http.get<UserDetailsPayload>(this.url2);
  }

  getUserDetails(id : number):Observable<UserDetailsPayload>{
    return this.http.get<UserDetailsPayload>(this.url2+"/"+id);
  }

  saveUserImage(imageFile : FormData){
    return this.http.post(this.url2+"/img",imageFile,{observe : 'response'});
  }

}
