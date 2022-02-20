import { DEFAULT_CURRENCY_CODE, LOCALE_ID,NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'
import { rootRouterConfig } from './app.routes';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { ProfileComponent } from './profile/profile.component';
import { PostDetailComponent } from './post-detail/post-detail.component';
import { BuyComponent } from './buy/buy.component';
import { LoginComponent } from './login/login.component';
import ptBr from '@angular/common/locales/pt';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import { NgRatingBarModule } from 'ng-rating-bar';
import { NewPostComponent } from './new-post/new-post.component';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { MatMenuModule } from '@angular/material/menu'

registerLocaleData(ptBr);

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    ProfileComponent,
    PostDetailComponent,
    BuyComponent,
    LoginComponent,
    ForgotPasswordComponent,
    RegisterComponent,
    NewPostComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(rootRouterConfig),
    NgbModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatButtonModule,
    HttpClientModule,
    NgRatingBarModule,
    CurrencyMaskModule,
    MatMenuModule
  ],
  exports:[
 
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'pt' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
