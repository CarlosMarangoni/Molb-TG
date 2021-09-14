import { ProfileComponent } from './profile/profile.component';
import { Routes } from "@angular/router"
import { HomeComponent } from "./home/home.component"
export const rootRouterConfig: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'profile', component: ProfileComponent }

]