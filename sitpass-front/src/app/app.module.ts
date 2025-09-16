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

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    ForbiddenComponent,
    NavbarComponent,
    WorkdayComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginComponent
  ],
  providers: [ provideHttpClient(withInterceptors([authInterceptor])) ],
  bootstrap: [AppComponent]
})
export class AppModule { }
