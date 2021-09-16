import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'
import { rootRouterConfig } from './app.routes';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';

import {MenubarModule} from 'primeng/menubar';
import { FooterComponent } from './footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {InputTextModule} from 'primeng/inputtext';
import { ProfileComponent } from './profile/profile.component';
import { PostDetailComponent } from './post-detail/post-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    ProfileComponent,
    PostDetailComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(rootRouterConfig),
    MenubarModule,
    NgbModule,
    InputTextModule
  ],
  exports:[
 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
