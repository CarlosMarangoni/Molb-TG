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
  public uploadedImageUrl:String = "/assets/img/no-image.png";
  public categories:string[] = [];
  addPostSuccess:boolean = false;

  constructor(
    private tokenStorage: TokenStorageService,
    private postService: PostService
  ) {}

  ngOnInit(): void {
    this.modelagens = [new PostItem()];
    this.obterCategorias();
  }

  onSubmit(f: NgForm) {
    console.log(this.form.category)
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
        .cadastrarPostComArquivo(this.postForm, this.file!)
        .subscribe(
          (a) => {
            console.log(a);
          },
          (error) => console.log(error)
        ); //LEMBRAR DE ZERAR ARRAY APÓS ENVIO DO FORM
    } else {
      this.postForm.userId = Number(this.tokenStorage.getUserId());
      this.postForm.title = this.form.title;
      this.postForm.description = this.form.description;
      this.postForm.category = this.form.category;
      this.postService.cadastrarPost(this.postForm).subscribe(
        (a) => {
          console.log(a);
        },
        (error) => console.log(error)
      );
    }
    this.addPostSuccess = true;
    f.resetForm()
  }

  adicionarModelagem() {
    this.modelagens.push(new PostItem());
  }

  removerItem(i: number) {
    this.modelagens.splice(i, 1);
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

  obterCategorias(){
    this.postService.obterTodasCategorias().subscribe(categories =>{
      this.categories = categories;
    },error => console.log(error))
  }

  eventSelection(event:any){
    this.form.category = (<HTMLInputElement>event).value
  }
}
