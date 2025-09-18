import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginGuard } from './core/guard/login.guard';
import { RoleGuard } from './core/guard/role.guard';
import { ForbiddenComponent } from './pages/forbidden/forbidden.component';
import { WorkdayComponent } from './components/workday/workday.component';
import { DisciplineComponent } from './components/discipline/discipline.component';
import { FacilityDetailComponent } from './pages/facility-detail/facility-detail.component';
import { ReviewComponent } from './components/review/review.component';
import { FacilityInfoComponent } from './components/facility-info/facility-info.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent,canActivate:[LoginGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  { path: 'forbidden', component: ForbiddenComponent},
  { path: 'workday', component: WorkdayComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  { path: 'discipline', component:DisciplineComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  { path: 'facilityDetail/:id', component: FacilityDetailComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  { path: 'review', component: ReviewComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  { path: 'facilityInfo', component: FacilityInfoComponent,canActivate:[RoleGuard],data: { expectedRoles: ['Admin', 'User'] }},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
