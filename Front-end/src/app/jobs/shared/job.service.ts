import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobPayload } from './job.payload';
import { JobsResponse } from './jobs-response';
@Injectable({
  providedIn: 'root'
})
export class JobService {

  url : string = "http://localhost:8888/JOB-SERVICE/api/job";
  url2 : string = "http://localhost:8082/api/job";


  constructor(private http : HttpClient) { }

  createJobOffer(jobPayload : JobPayload){
    return this.http.post(this.url2,jobPayload);
  }

  getJobsPage(pageNumber : number,pageSize : number):Observable<JobsResponse>{
    let params = new HttpParams();
    params = params.append('page',pageNumber.toString());
    params = params.append('size', pageSize.toString());

    return this.http.get<JobsResponse>(this.url2,{params : params});
  }

  getJobByID(id : number):Observable<JobPayload>{
    return this.http.get<JobPayload>(this.url2+"/"+id);
  }

  filterJobsPage(pageNumber : number,pageSize : number,name : string, location : string):Observable<JobsResponse>{
    let params = new HttpParams();
    params = params.append('page',pageNumber.toString());
    params = params.append('size', pageSize.toString());
    params = params.append('name',name);
    params = params.append('location',location);

    return this.http.get<JobsResponse>(this.url2+"/filter",{params : params});
  }




}
