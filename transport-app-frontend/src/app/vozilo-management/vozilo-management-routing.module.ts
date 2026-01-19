import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VoziloManagementComponent } from './vozilo-managment.component';

const routes: Routes = [{ path: '', component: VoziloManagementComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VoziloManagementRoutingModule { }
