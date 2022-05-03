

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
import { EditUserComponent } from "./portal-admin/edit-user/edit-user.component";
import { CreateCategoryComponent } from "./portal-admin/create-category/create-category.component";
import { ContactUsComponent } from "./contact-us/contact-us.component";
import { UserDetailComponent } from "./user-detail/user-detail.component";
import { PurchasesComponent } from "./user-detail/purchases/purchases.component";
import { SalesComponent } from "./user-detail/sales/sales.component";
import { PurchaseComponent } from "./purchase/purchase.component";
import { SaleComponent } from "./sale/sale.component";
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
      { path: 'users/edit/:id', component: EditUserComponent },
      { path: 'category/create', component: CreateCategoryComponent }
    ]
  },
  { path:'contact-us',component: ContactUsComponent},
  { path:'purchases/:id',component: PurchaseComponent},
  { path:'sales/:id',component: SaleComponent},
  {
    path: "users",
    component: UserDetailComponent,
    children: [
      { path: '', redirectTo: 'purchases',pathMatch:'prefix' },
      { path: 'purchases', component: PurchasesComponent },
      { path: 'sales', component: SalesComponent }
    ]
  },
];
