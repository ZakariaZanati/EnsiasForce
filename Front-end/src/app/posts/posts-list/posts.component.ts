import { Component, OnInit, ViewChild } from '@angular/core';
import { PostModel } from '../post.model';
import {  PostsService } from '../shared/posts.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CreatePostComponent } from '../create-post/create-post.component';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  @ViewChild("commentForm") form: any = {};

  isCommentsShown : boolean = false;
  // commentFormIsShown :boolean = false;
  idPostTocomment : number = -1;
  
  posts : PostModel[] = [];

  constructor(private postsService :  PostsService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadAllPosts();
  }


  private loadAllPosts() {
    this.postsService.getPosts().subscribe(
      posts => {
        console.log("posts fetched");
        console.log(posts);
        // let tempPostsArray:PostModel[] = [];
        // posts.forEach(v => {
        //   let post : PostModel = {id: v.id,description : v.description, publisherFullName: v.publisherFullName
        //     , profileImage : v.profileImage, image : v.image, likeCount : v.likeCount
        //     , commentCount : v.commentCount, liked :  v.liked, dateCreation : v.dateCreation
        //     , iscommentSectionShowed : false};
        //   tempPostsArray.push(post)
        // })
        // this.posts = tempPostsArray;

        posts.map(post => {
          post.iscommentSectionShowed = false;
          const img = post.image;
          if (img) {
            post.image = "data:image/jpeg;base64,"+post.image;
          }
        })
        this.posts = posts;
        console.log(this.posts);
      }
    );
  }

  clickLike(idPost : number): void {
    this.postsService.clickLike(idPost).subscribe(
      message => {
        console.log("like clicked");
        let postIndex = this.posts.findIndex(v => v.id == idPost);
        if(this.posts[postIndex].liked == false) {
          this.posts[postIndex].likeCount++;
          this.posts[postIndex].liked = true;
        }
        else {
          this.posts[postIndex].likeCount--;
          this.posts[postIndex].liked = false;
        }
      }
    )
  }

  startCommentOnPost(post: PostModel) {
    // this.commentFormIsShown = !this.commentFormIsShown;
    // this.idPostTocomment = idPost;
    post.iscommentSectionShowed = true;

  }


  onSubmit(): void {
    console.log("comment Submitted");
    console.log(this.form);
    let comment = {text : this.form.comment};
    let postId = this.idPostTocomment;
    
    console.log(postId);
    this.postsService.addComment(postId, comment).subscribe(
      response => {
        console.log(response);
      }
    )

  }

  showCommentSection(post: PostModel) {
    post.iscommentSectionShowed = !post.iscommentSectionShowed;
  }



  open() {
    const modalRef = this.modalService.open(CreatePostComponent);
    modalRef.result.then(
      (result) => {
        console.log("result is");
        console.log(result);
        this.loadAllPosts();
      }
    )
  }
}
