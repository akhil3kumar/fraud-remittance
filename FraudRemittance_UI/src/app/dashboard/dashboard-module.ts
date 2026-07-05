import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing-module';
import { AdminDashboard } from './admin-dashboard/admin-dashboard';

@NgModule({
  declarations: [
    AdminDashboard
  ],
  imports: [CommonModule, DashboardRoutingModule],
})
export class DashboardModule {}
