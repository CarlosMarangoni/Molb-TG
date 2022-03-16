import { Categoria } from './../../model/category-dto';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostService } from "./../service/post.service";
import { PostForm } from "./../../model/post-form";
import { PostItem } from "./../../model/postItem-dto";
import { Component, OnInit } from "@angular/core";
import { TokenStorageService } from "../service/token-storage.service";
import { NgForm } from "@angular/forms";

@Component({
  selector: "app-new-post",
  templateUrl: "./new-post.component.html",
  styles: [],
})
export class NewPostComponent implements OnInit {
  public uploaded: boolean = false;
  public modelagens: Array<PostItem> = [];
  private postForm: PostForm = new PostForm(0, "","", "", []);
  form: any = {};
  public file: File | undefined;
  public moldesFiles:Array<File> = [];
  public fileTest:File | undefined;
  public uploadedImageUrl:String = "/assets/img/no-image.png";
  public categories:Categoria[] = [];
  fileValid: Array<boolean> = [false];

  constructor(
    private tokenStorage: TokenStorageService,
    private postService: PostService,
    private modalService: NgbModal,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.modelagens = [new PostItem()];
    this.obterCategorias();
  }

  onSubmit(f: NgForm,content:any) {    
    for (let i = 0; i < this.modelagens.length; i++) {
      let a = new PostItem();
      a.price = this.modelagens[i].price;
      a.description = this.modelagens[i].description;
      this.postForm.items.push(a);
    }
    if (this.file) {
      this.postForm.userId = Number(this.tokenStorage.getUserId());
      this.postForm.title = this.form.title;
      this.postForm.description = this.form.description;
      this.postForm.category = this.form.category;

      this.postService
        .cadastrarPostComArquivo(this.postForm, this.file!,this.moldesFiles!)
        .subscribe(
          (a) => {
            console.log(a);
          },
          (error) => console.log(error)
        );
    } else {
      this.postForm.userId = Number(this.tokenStorage.getUserId());
      this.postForm.title = this.form.title;
      this.postForm.description = this.form.description;
      this.postForm.category = this.form.category;
      console.log(this.moldesFiles);
      this.postService.cadastrarPost(this.postForm,this.moldesFiles!).subscribe(
        (a) => {
          console.log(a);
        },
        (error) => console.log(error)
      );
    }
    f.resetForm()
    this.modelagens = [];
    this.modalService.open(content,
      {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
         this.router.navigateByUrl(`/profile/${this.tokenStorage.getUserId()}`);
       });
  }

  adicionarModelagem() {
    this.modelagens.push(new PostItem());
    this.fileValid.push(false);
  }

  removerItem(i: number) {
    this.modelagens.splice(i, 1);
    this.moldesFiles.splice(i,1);
  }

  onChange(event: any) {
    const selectedFile = <FileList>event.srcElement.files;
    const fileLabel = document.getElementById("customFileLabel");
    if (!fileLabel) return;
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.uploadedImageUrl=reader.result!.toString();
    });
    reader.readAsDataURL(selectedFile[0]);
    fileLabel.innerHTML = selectedFile[0].name;
    this.file = selectedFile[0];
  }

  onFileAdd(event:any,i:any){
    console.log('i = ' + i);
    const selectedFile = <FileList>event.srcElement.files;
    const fileLabel = document.getElementById("moldeFileLabel"+i);
    if (!fileLabel) return;
    fileLabel.innerHTML = selectedFile[0].name;
    this.fileTest = selectedFile[0];
    this.moldesFiles.push(this.fileTest);
    this.fileValid[i] = true;
    console.log(this.moldesFiles)
  }

  obterCategorias(){
    this.postService.obterTodasCategorias().subscribe(categories =>{
      this.categories = categories;
    },error => console.log(error))
  }

  eventSelection(event:any){
    this.form.category = (<HTMLInputElement>event).value
  }

  isFileValid(): boolean {
    return this.fileValid.indexOf(false) == -1

  }
}
