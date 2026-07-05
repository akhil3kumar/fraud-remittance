import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainLayout } from './layout/main-layout/main-layout';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./user/auth-module').then((m) => m.AuthModule),
  },
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full',
  },
  {
    path: 'admin',
    component: MainLayout,
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard-module').then((m) => m.DashboardModule),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
