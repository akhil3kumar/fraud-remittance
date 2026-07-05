import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../core/services/auth/auth-service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { UserForm } from '../../shared/forms/auth/user-form';
import { RegistrationRequest } from '../../shared/models/auth/registration-request';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registeration-form',
  standalone: false,
  templateUrl: './registeration-form.html',
  styleUrl: './registeration-form.css',
})
export class RegisterationForm implements OnInit {
  registerForm!: FormGroup;
  showPassword = false;
  showConfirmPassword = false;

  constructor(private userService: AuthService, private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.registerForm = UserForm.generateRegisterationForm(this.fb);
  }

  submitForm() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }
    console.log(this.registerForm.value);
    const request: RegistrationRequest = this.registerForm.value;
    this.userService.registerUser(request).subscribe({
      next: (response) => {
        console.log(response);
        this.router.navigateByUrl('/auth/login');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  get firstName(): FormControl {
    return this.registerForm.get('firstName') as FormControl;
  }

  get lastName(): FormControl {
    return this.registerForm.get('lastName') as FormControl;
  }

  get email(): FormControl {
    return this.registerForm.get('email') as FormControl;
  }

  get phoneNumber(): FormControl {
    return this.registerForm.get('phoneNumber') as FormControl;
  }

  get password(): FormControl {
    return this.registerForm.get('password') as FormControl;
  }

  get confirmPassword() {
    return this.registerForm.get('confirmPassword')!;
  }
}
