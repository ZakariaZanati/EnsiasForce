import { Component, OnInit, Input } from '@angular/core';
import {  PostsService } from '../shared/posts.service';
import { CommentModel, commentRequestModel } from './comment.model';
import { PostModel } from '../post.model';
import { throwError } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() post : PostModel;
  id : number;
  commentForm : FormGroup;
  comment : commentRequestModel;

  comments: CommentModel[];
  //  = [
  //   { id : 2,
  //     text : "test",
  //     createdDate : null,
  //     fullName : "sefiane"},
  //     { id : 3,
  //       text : "test2",
  //       createdDate : null,
  //       fullName : "anas"}
  // ];


  constructor(private postService :  PostsService) { }

  ngOnInit(): void {
    this.commentForm = new FormGroup({
      text: new FormControl('')
    });
    this.comment = {
      text : ''
    }

    this.getCommentsForPost();
  }

  
  private getCommentsForPost() {
    this.postService.getAllCommentsForPost(this.post.id).subscribe(
      response => {
        this.comments = response;
      }, error => {
        throwError(error);
      }
    );
  }

  postComment(){
    this.comment.text = this.commentForm.get('text').value;
    if (this.comment.text == '') {
      return;
    }
    this.post.commentCount = this.post.commentCount+1;
    this.postService.addComment(this.post.id, this.comment).subscribe(
      response => {
        this.commentForm.get('text').setValue('');
        this.getCommentsForPost();
      },error => {
        throwError(error);
      }
    )
  }
}
