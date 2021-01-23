import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { HomeComponent } from './home/home.component';
import { JobDetailsComponent } from './jobs/job-details/job-details.component';
import { JobsListComponent } from './jobs/jobs-list/jobs-list.component';
import { ProfileComponent } from './user/profile/profile.component';

const routes: Routes = [
  {path : '',component : HomeComponent},
  {path : 'signup',component : SignupComponent},
  {path : 'signin',component : LoginComponent},
  {path : 'profile',component : ProfileComponent},
  {path : 'jobs', component : JobsListComponent,children : [
    {path : 'id', component : JobDetailsComponent}
  ]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
