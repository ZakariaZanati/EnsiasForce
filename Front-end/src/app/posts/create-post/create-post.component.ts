import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { PostsService } from '../shared/posts.service';
import { postRequestModel } from '../post.model';
import { AuthService } from '../../auth/shared/auth.service';
@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  file : File;
  id : number;

  public imagePath;
  imgURL: any;
  public message: string;

  constructor(private postService:PostsService, private formBuilder : FormBuilder, private http:HttpClient
    , public activeModal: NgbActiveModal, private authService : AuthService) { }

  ngOnInit(): void {
    this.createPostForm = this.formBuilder.group({
      postImage : [''],
      description : ['']
    })
  }

  onFileChanged(event) {
    //Select File
    this.file = event.target.files[0];
    this.createPostForm.get('postImage').setValue(this.file);
  }

  createPost() {
    const uploadPost = new FormData();
    let post:postRequestModel = {description : this.createPostForm.get('description').value
    , image : this.createPostForm.get('postImage').value, userID : this.authService.userID}
    console.log("post a send is");
    console.log(post);
    uploadPost.append('postImage',post.image);
    uploadPost.append('postDescription',post.description);
    uploadPost.append('publisherID',String(post.userID));
    this.postService.addPost(uploadPost).subscribe(
      response => {
        console.log(response);
        let result = post;
        this.activeModal.close(result);
      }
    )

  }


  preview(files) {
    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
 
    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }
}
