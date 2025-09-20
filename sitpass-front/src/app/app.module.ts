import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ForbiddenComponent } from './pages/forbidden/forbidden.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { authInterceptor } from './core/interceptor/auth.interceptor';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { WorkdayComponent } from './components/workday/workday.component';
import { DisciplineComponent } from './components/discipline/discipline.component';
import { FacilityDetailComponent } from './pages/facility-detail/facility-detail.component';
import { ReviewComponent } from './components/review/review.component';
import { FacilityInfoComponent } from './components/facility-info/facility-info.component';
import { ExerciseComponent } from './components/exercise/exercise.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DisciplineManagmentComponent } from './components/discipline-managment/discipline-managment.component';
import { CreateDisciplineComponent } from './components/create-discipline/create-discipline.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { ControlaAccountRequestComponent } from './components/controla-account-request/controla-account-request.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    ForbiddenComponent,
    NavbarComponent,
    WorkdayComponent,
    DisciplineComponent,
    FacilityDetailComponent,
    ReviewComponent,
    FacilityInfoComponent,
    ExerciseComponent,
    DisciplineManagmentComponent,
    CreateDisciplineComponent,
    CreateAccountComponent,
    ControlaAccountRequestComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginComponent,
    FormsModule,
    ReactiveFormsModule
],
  providers: [ provideHttpClient(withInterceptors([authInterceptor])) ],
  bootstrap: [AppComponent]
})
export class AppModule { }
