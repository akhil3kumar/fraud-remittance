import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export class UserForm {
  static generateRegisterationForm(fb: FormBuilder): FormGroup {
    return fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$'),
        ],
      ],
      confirmPassword: ['', Validators.required],
    });
  }

  static generateLoginForm(fb: FormBuilder): FormGroup {
    return fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }
}
