import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { CourseModel, CoursesResponse } from '../courseModel';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  SERVER_URL = "http://localhost:8888/COURSE-SERVICE/api/courses"
  SERVER_URL_test = "http://localhost:8083/api/courses/"

  constructor(private http:HttpClient) { }

  createCourse(course : FormData){
    return this.http.post(this.SERVER_URL_test, course).subscribe(data => {
      console.log(data);
      console.log("Course created");
    },err => {
      throwError(err);
    });
  }

getCourse(id : number):Observable<CourseModel>{
  return this.http.get<CourseModel>(this.SERVER_URL_test + id)
}

getCoursesPage(pageNumber : number,pageSize : number,name : string,category : string,date : Date):Observable<CoursesResponse>{
  let params = new HttpParams();
  if (!date) {
    console.log(date)
    params = params.append('date',"");
  }
  else {
    console.log(date)
    params = params.append('date',date.toISOString());
  }
  params = params.append('page',pageNumber.toString());
  params = params.append('size', pageSize.toString());
  params = params.append('name',name);
  params = params.append('category',category);

  return this.http.get<CoursesResponse>(this.SERVER_URL_test, {params:params});
}
}
