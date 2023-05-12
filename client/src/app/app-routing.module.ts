import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhotoStorageComponent } from './components/photo-storage/photo-storage.component';
import { PhotoUploadComponent } from './components/photo-upload/photo-upload.component';
import { PhotoViewComponent } from './components/photo-view/photo-view.component';

const routes: Routes = [
  { path:'', component: PhotoStorageComponent },
  { path:'upload', component: PhotoUploadComponent },
  { path:'view', component: PhotoViewComponent },
  { path: "**", redirectTo: "/", pathMatch: "full"}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
