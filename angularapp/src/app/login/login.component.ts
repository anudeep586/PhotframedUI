import { Component, OnInit } from '@angular/core';

import { RegistrationService } from './../registration.service';
import {NgForm} from '@angular/forms';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user=new User()
 msg=""
 constructor(private _service: RegistrationService, private _router:Router) { }

  ngOnInit(): void {
  }
  loginUser(){
    if(this.user.email=="admin" && this.user.password=="admin"){
      this._router.navigate(['/admin'])
    }
    this._service.loginUserfromRemote(this.user).subscribe(
      data =>{ 
        console.log("response recieved");
      this._router.navigate(['/home'])
    },
      error => {
        console.log("exception occured");
        this.msg="bad credentials please enter valid email and password";
  }
    )
  }
  gotoregistration(){
    this._router.navigate(['/register'])
  }

}
