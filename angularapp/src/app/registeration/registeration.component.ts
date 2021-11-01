import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../registration.service';
import {NgForm} from '@angular/forms';
import { User } from '../user';
import { Router } from '@angular/router';
@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {
  user=new User();
  msg=""
  

  constructor(private _service: RegistrationService, private _router:Router) { }

  ngOnInit(): void {
  }
  registerUser(){
    this._service.registerUserfromRemote(this.user).subscribe(
      data =>{ 
        console.log("response recieved naa");
      this.msg='Registration successful';
      this._router.navigate(['/'])
    },
      error => {
        console.log("exception occured");
        this.msg="EmailId is already in Use";
  }
    )
  }
  gotoregistration(){
    this._router.navigate(['/'])
  }

}
