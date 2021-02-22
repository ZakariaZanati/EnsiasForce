export class CourseModel {
    id : number;
    name : string;
    description : string;
    publisherFullName : string;
    dateTime : any;
    imageBytes : any;
    location : string;
    link : string;
    time : string
    duration : string;
    category : string;
    show? : boolean;
}

export class CoursesResponse {
    
    courses : CourseModel[];
    totalPages: number;
    pageNumber: number;
    pageSize: number;
}