import { Injectable } from '@angular/core';
import { PostModel } from '../post.model';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'
import { CommentModel, commentRequestModel } from '../comment/comment.model';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  SERVER_URL = "http://localhost:8888/COURSE-SERVICE/api/posts/"
  SERVER_URL_test = "http://localhost:8083/api/posts/"

  constructor(private http:HttpClient) {console.log("posts Service"); }


  getPosts() : Observable<PostModel[]>{
    return this.http.get<PostModel[]>(this.SERVER_URL_test);
  }

  addPost(post: any) : Observable<any>{
    return this.http.post(this.SERVER_URL_test,post);
  }

  clickLike(idPost : number) : Observable<any>{
    return this.http.post(this.SERVER_URL_test + idPost + "/likes",null);
  }

  addComment(idPost : number,comment: commentRequestModel) : Observable<any>{
    return this.http.post(this.SERVER_URL_test + idPost + "/comments",comment);
  }

  getAllCommentsForPost(idPost: number): Observable<CommentModel[]> {
    return this.http.get<CommentModel[]>(this.SERVER_URL_test + idPost  + "/comments");
  }
}
