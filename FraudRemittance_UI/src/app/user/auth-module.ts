import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing-module';
import { LoginForm } from './login-form/login-form';
import { RegisterationForm } from './registeration-form/registeration-form';
import { AngularMaterialModule } from '../angular-material-module';
@NgModule({
  declarations: [LoginForm, RegisterationForm],
  imports: [CommonModule, AuthRoutingModule, AngularMaterialModule],
})
export class AuthModule {}
