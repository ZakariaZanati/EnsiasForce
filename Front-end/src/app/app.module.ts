import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox'; 
import {MatSelectModule} from '@angular/material/select'; 
import {MatDatepickerModule} from '@angular/material/datepicker';

import {NgxWebstorageModule} from 'ngx-webstorage';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './user/profile/profile.component';
import { ExperienceComponent } from './user/experience/experience.component';
import { FormationComponent } from './user/formation/formation.component';
import { SkillsComponent } from './user/skills/skills.component';
import { JobsListComponent } from './jobs/jobs-list/jobs-list.component';
import { JobDescriptionComponent } from './jobs/job-description/job-description.component';
import { JobDetailsComponent } from './jobs/job-details/job-details.component';
import { JobFormComponent } from './jobs/job-form/job-form.component';
import { PostsComponent } from './posts/posts-list/posts.component';
import { DetailsFormComponent } from './user/details-form/details-form.component';
import {TokenInterceptor} from './token-interceptor';
import { CommentComponent } from './posts/comment/comment.component';
import { CreatePostComponent } from './posts/create-post/create-post.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SidebarComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    ExperienceComponent,
    FormationComponent,
    SkillsComponent,
    JobsListComponent,
    JobDescriptionComponent,
    JobDetailsComponent,
    JobFormComponent,
    PostsComponent,
    DetailsFormComponent,
    CommentComponent,
    CreatePostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatRadioModule,
    MatCheckboxModule,
    MatSelectModule,
    MatDatepickerModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide : HTTP_INTERCEPTORS,
      useClass : TokenInterceptor,
      multi : true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
