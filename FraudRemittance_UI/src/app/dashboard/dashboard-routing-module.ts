import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboard } from './admin-dashboard/admin-dashboard';
import { authGuard } from '../core/guards/auth-guard';
import { roleGuard } from '../core/guards/role-guard';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboard,
    canActivate: [authGuard, roleGuard],
    data: {
      roles: ['ADMIN'],
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
