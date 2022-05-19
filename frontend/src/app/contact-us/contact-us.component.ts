import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageDto } from 'src/model/message-dto';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styles: [
  ]
})
export class ContactUsComponent implements OnInit {

  public form:any = {};
  public msg:string = "";
  public message:string = ""

  constructor(private modalService: NgbModal,private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(f: NgForm,content:any){
    this.msg = this.form.message
    let messageDto = new MessageDto();
    messageDto.message = this.msg
    this.userService.contatarTime(messageDto).subscribe(data =>
      console.log(data));
    this.modalService.open(content,
      {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
         
       });
       f.resetForm()
  }

}
