import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { LocalStorageService } from 'ngx-webstorage';
import { SignupRequestPayload} from '../signup/signup-request.payload';
import { LoginRequestPayload } from '../login/login-request.payload';
import { LoginResponsePayload } from '../login/login-response.payload';
import { RoleUserForm } from '../shared/user-role.payload';
import { Observable, throwError } from 'rxjs';
import { map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn: EventEmitter<Boolean> = new EventEmitter<Boolean>();
  @Output() email: EventEmitter<String> = new EventEmitter<String>();
  @Output() fullName : EventEmitter<String> = new EventEmitter<String>();

  url : string = "http://localhost:8888/USER-SERVICE";
  url2 = "http://localhost:8081"

  constructor(private http : HttpClient, private localStorage : LocalStorageService) { }

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    email: this.getEmail()
  }

  roleUserForm : RoleUserForm = new RoleUserForm();

  signup(signupRequestPayload : SignupRequestPayload):Observable<any>{
    return this.http.post('http://localhost:8081/signup',signupRequestPayload,{responseType:'text'})
  }

  addRoleToUser(email : string,roleName : string){

    this.roleUserForm.email = email;
    this.roleUserForm.roleName = roleName;
    return this.http.post(this.url2+'/addRoleToUser',this.roleUserForm,{responseType:'text'});
  }

  login(loginRequestPayload : LoginRequestPayload):Observable<Boolean>{

    const body = new HttpParams()
      .set('email',loginRequestPayload.email)
      .set('password',loginRequestPayload.password);

    return this.http.post<LoginResponsePayload>(this.url2+'/login',
      body.toString(),
      {
        headers : new HttpHeaders()
          .set('Content-type','application/x-www-form-urlencoded')
      }
    ).pipe(map(data => {

        console.log(data);
        
        this.localStorage.store('authenticationToken',data.authenticationToken);
        this.localStorage.store('email',data.email);
        this.localStorage.store('refreshToken',data.refreshToken);
        this.localStorage.store('expiresAt',data.expiresAt);
        this.localStorage.store('completed',data.completed);
        this.localStorage.store('fullName',data.fullName);
        this.localStorage.store('userType',data.userType);

        this.loggedIn.emit(true);
        this.email.emit(data.email);
        this.fullName.emit(data.fullName);

        return true;
      }));
  }

  refreshToken() {
    return this.http.post<LoginResponsePayload>(this.url+'/refreshToken',
      this.refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');

        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  logout(){
    /*
    this.http.post('http://localhost:8181/api/auth/logout',this.refreshTokenPayload,
    {responseType:'text'})
    .subscribe(data => {
      console.log(data);
    },error => {
      throwError(error);
    });
    */
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
    this.localStorage.clear('completed');
    this.localStorage.clear('fullName');

  }

  getEmail(){
    return this.localStorage.retrieve('email');
  }

  getFullName(){
    return this.localStorage.retrieve('fullName');
  }

  getUserType(){
    return this.localStorage.retrieve('userType');
  }

  getJwtToken(){
    return this.localStorage.retrieve('authenticationToken');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }

  isCompleted(): boolean {
    return this.localStorage.retrieve('completed')==='true';
  }





}
