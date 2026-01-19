import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './service/auth-guard.service';

const routes: Routes = [
  {
    path: '', // HomeModule at root
    loadChildren: () => import('./home/home.module').then(mod => mod.HomeModule)
  },
  {
    path: 'auth', // AuthModule under /auth
    loadChildren: () => import('./auth/auth.module').then(mod => mod.AuthModule)
  },
  { path: 'vozila', loadChildren: () => import('./vozilo-management/vozilo-management.module').then(m => m.VoziloManagementModule), canActivate: [AuthGuard] },
  { 
    path: 'events', 
    loadChildren: () => import('./event/event.module').then(m => m.EventModule),
  },
  {
    path: 'event-categories',
    loadChildren: () => import('./event-category/event-category.module').then(m => m.EventCategoryModule),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }