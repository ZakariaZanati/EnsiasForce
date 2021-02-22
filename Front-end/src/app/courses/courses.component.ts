import { Component, OnInit } from '@angular/core';
import { FormGroup ,FormBuilder, Validators} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {DomSanitizer} from '@angular/platform-browser';
import { throwError } from 'rxjs';
import { NgbDate, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterLink } from '@angular/router';
import { CourseModel, CoursesResponse } from './courseModel';
import { CoursesService } from './shared/courses.service';
import { CreateCourseComponent } from './create-course/create-course.component';
@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  categories = ['Computer science','History','Communication','Data science','Cloud computing'
                                ,'Economics','Biology','Art','Physics','Psychiatry','Fiance','Medicine','Philosophy']
  
  courses : Array<CourseModel> = [];
  userType : string;
  currentSelectedPage : number = 0;
  pageNumber : number = 0;
  totalPages : number = 0;
  pageIndexes : Array<number> = [];
  name : string = "";
  categoryFilter : string = "";
  dateFilter : NgbDate;
  dateFormat : Date;

  constructor(private formBuilder : FormBuilder,private http:HttpClient,
    private courseService : CoursesService, public _DomSanitizationService: DomSanitizer
    , private router : Router, private modalService: NgbModal ) { }

  ngOnInit(): void {
    this.getPage(0,8,this.name,this.categoryFilter,this.dateFormat);
  }

  addCourse(){
    // this.router.navigateByUrl('/create-course')
  }

  getPage(page : number,size : number,name : string,category : string, date : Date){
    this.courseService.getCoursesPage(page,size,name,category,date).subscribe((response : CoursesResponse) => {
      this.courses = response.courses;
      console.log(response)
      this.courses.map(course => {
        course.show = false;
        course.dateTime = new Date(course.dateTime).toLocaleDateString()
        const img = course.imageBytes;
        if (img) {
          course.imageBytes = "data:image/jpeg;base64,"+course.imageBytes
        }
        this.totalPages = response.totalPages;
        this.pageIndexes = Array(this.totalPages).fill(0).map((x,i)=>i);
        this.currentSelectedPage = response.pageNumber;
      },err => throwError(err));
    })
  }

  nextClick(){
    if(this.currentSelectedPage < this.totalPages-1){
      this.getPage(++this.currentSelectedPage,8,this.name,this.categoryFilter,this.dateFormat);
    }  
  }

  previousClick(){
    if(this.currentSelectedPage > 0){
      this.getPage(--this.currentSelectedPage,8,this.name,this.categoryFilter,this.dateFormat);
    }  
  }

  getPaginationWithIndex(index: number) {
    this.getPage(index, 8,this.name,this.categoryFilter,this.dateFormat);
  }

  active(index: number) {
    if(this.currentSelectedPage == index ){
      return {
        active: true
      };
    }
  }

  onSearchChange(searchValue: string): void { 
    console.log(searchValue) 
    this.name = searchValue;
    this.getPage(0,8,this.name,this.categoryFilter,null);
  }

  filterByDate(){
    this.dateFormat = new Date(this.dateFilter.year,this.dateFilter.month-1,this.dateFilter.day+1);
    this.getPage(0,8,"","",this.dateFormat);
  }

  filterByCategory(){
    this.getPage(0,8,"",this.categoryFilter,null);
  }

  public toogleCourse(course){
    course.show = !course.show;
  }


  openModalPostCreation() {
    const modalRef = this.modalService.open(CreateCourseComponent,{size:"lg"});
    modalRef.result.then(
      (result) => {
        console.log("result is");
        console.log(result);
        this.getPage(0,8,this.name,this.categoryFilter,this.dateFormat);
      }
    )
  }

}
