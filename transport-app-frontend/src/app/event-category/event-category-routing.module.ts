import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventCategoryContainerComponent } from './event-category-container/event-category-container.component';
import { EventCategoryListComponent } from './event-category-list/event-category-list.component';

const routes: Routes = [
  {
    path:'',
    component: EventCategoryContainerComponent,
    children: [
      {
        path:'',
        component: EventCategoryListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventCategoryRoutingModule { }
