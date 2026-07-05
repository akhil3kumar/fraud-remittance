import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LayoutRoutingModule } from './layout-routing-module';
import { MainLayout } from './main-layout/main-layout';

@NgModule({
  declarations: [MainLayout],
  imports: [CommonModule, LayoutRoutingModule],
  exports: [MainLayout],
})
export class LayoutModule {}
