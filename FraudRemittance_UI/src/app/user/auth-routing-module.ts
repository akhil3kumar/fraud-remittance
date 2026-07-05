import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginForm } from './login-form/login-form';
import { RegisterationForm } from './registeration-form/registeration-form';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterationForm,
  },
  {
    path: 'login',
    component: LoginForm,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
