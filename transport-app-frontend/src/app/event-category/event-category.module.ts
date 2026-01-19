import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventCategoryRoutingModule } from './event-category-routing.module';
import { EventCategoryContainerComponent } from './event-category-container/event-category-container.component';
import { AppMaterialModule } from '../app-material/app-material.module/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EventCategoryEditComponent } from './event-category-edit/event-category-edit.component';
import { EventCategoryListComponent } from './event-category-list/event-category-list.component';


@NgModule({
  declarations: [
    EventCategoryContainerComponent,
    EventCategoryEditComponent,
    EventCategoryListComponent
  ],
  imports: [
    CommonModule,
    EventCategoryRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EventCategoryModule { }
