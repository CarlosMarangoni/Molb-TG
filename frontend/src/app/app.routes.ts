import { RegisterComponent } from './register/register.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { LoginComponent } from './login/login.component';
import { BuyComponent } from './buy/buy.component';
import { ProfileComponent } from './profile/profile.component';
import { Routes } from "@angular/router"
import { HomeComponent } from "./home/home.component"
import { PostDetailComponent } from "./post-detail/post-detail.component"
export const rootRouterConfig: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'posts/:id', component: PostDetailComponent },
    { path: 'posts/:id/comprar', component: BuyComponent },
    { path: 'login', component: LoginComponent },
    { path: 'forgot-password', component: ForgotPasswordComponent },
    { path: 'register', component: RegisterComponent }

]