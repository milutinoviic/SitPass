import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/service/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(private router: Router,private authService:AuthService) {}

  logout() {
   
    console.log('User logged out');
    this.authService.logout()
    this.router.navigate(['/login']); 
  }

}
