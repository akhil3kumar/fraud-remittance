import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AuthService } from '../../core/services/auth/auth-service';
import { Router } from '@angular/router';
import { UserForm } from '../../shared/forms/auth/user-form';
import { LoginRequest } from '../../shared/models/auth/login-request';

@Component({
  selector: 'app-login-form',
  standalone: false,
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm {
  loginForm!: FormGroup;
  showPassword = false;

  constructor(private userService: AuthService, private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = UserForm.generateLoginForm(this.fb);
  }

  submitForm() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }
    console.log(this.loginForm.value);
    const request: LoginRequest = this.loginForm.value;
    this.userService.loginUser(request).subscribe({
      next: (response) => {
        console.log(response);
        localStorage.setItem('token', response.accessToken);
        localStorage.setItem('refreshToken', response.refreshToken);
        this.router.navigateByUrl('admin/dashboard');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  get email(): FormControl {
    return this.loginForm.get('email') as FormControl;
  }

  get password(): FormControl {
    return this.loginForm.get('password') as FormControl;
  }
}
