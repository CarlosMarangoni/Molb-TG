import { AdminCategoryComponent } from "./portal-admin/admin-category/admin-category.component";
import { AdminUsersComponent } from "./portal-admin/admin-users/admin-users.component";
import { PortalAdminComponent } from "./portal-admin/portal-admin.component";
import { NewPostComponent } from "./new-post/new-post.component";
import { RegisterComponent } from "./register/register.component";
import { ForgotPasswordComponent } from "./forgot-password/forgot-password.component";
import { LoginComponent } from "./login/login.component";
import { BuyComponent } from "./buy/buy.component";
import { ProfileComponent } from "./profile/profile.component";
import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { PostDetailComponent } from "./post-detail/post-detail.component";
export const rootRouterConfig: Routes = [
  { path: "", redirectTo: "/home", pathMatch: "full" },
  { path: "home", component: HomeComponent },
  { path: "profile/:id", component: ProfileComponent },
  { path: "posts/:id", component: PostDetailComponent },
  { path: "new-post", component: NewPostComponent },
  { path: "cart", component: BuyComponent },
  { path: "login", component: LoginComponent },
  { path: "forgot-password", component: ForgotPasswordComponent },
  { path: "register", component: RegisterComponent },
  {
    path: "admin",
    component: PortalAdminComponent,
    children: [
      { path: '', redirectTo: 'users',pathMatch:'prefix' },
      { path: 'users', component: AdminUsersComponent },
      { path: 'category', component: AdminCategoryComponent },
    ]
  }
];
