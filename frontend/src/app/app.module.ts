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
import { MatSidenavModule } from '@angular/material/sidenav'
import { MatToolbarModule } from '@angular/material/toolbar';
import { HeaderComponent } from './header/header.component'
import {FlexLayoutModule} from "@angular/flex-layout";
import { MatListModule } from '@angular/material/list';
import { SidenavListComponent } from './sidenav-list/sidenav-list.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { PortalAdminComponent } from './portal-admin/portal-admin.component';
import { AdminUsersComponent } from './portal-admin/admin-users/admin-users.component';
import { AdminCategoryComponent } from './portal-admin/admin-category/admin-category.component';
import { EditUserComponent } from './portal-admin/edit-user/edit-user.component';
import { CreateCategoryComponent } from './portal-admin/create-category/create-category.component';
import { ContactUsComponent } from './contact-us/contact-us.component';

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
    NewPostComponent,
    HeaderComponent,
    SidenavListComponent,
    PortalAdminComponent,
    AdminUsersComponent,
    AdminCategoryComponent,
    EditUserComponent,
    CreateCategoryComponent,
    ContactUsComponent
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
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatListModule,
    MatExpansionModule
  ],
  exports:[
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    FlexLayoutModule,
    MatListModule
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'pt' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
