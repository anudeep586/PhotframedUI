import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterationComponent } from './registeration/registeration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PostComponent } from './post/post.component';



const routes: Routes = [
  {
    path:"register",component:RegisterationComponent
    
  },
  {
    path:"",component:LoginComponent
  },
  {
    path:"home",component:HomeComponent
  },
  {
    path:"posted",component:PostComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],


exports: [RouterModule]
})
export class AppRoutingModule { }
