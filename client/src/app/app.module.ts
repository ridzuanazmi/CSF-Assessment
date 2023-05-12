import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PhotoUploadComponent } from './components/photo-upload/photo-upload.component';
import { PhotoStorageComponent } from './components/photo-storage/photo-storage.component';
import { PhotoViewComponent } from './components/photo-view/photo-view.component';
import { SbapiService } from './services/sbapi.service';

@NgModule({
  declarations: [
    AppComponent,
    PhotoUploadComponent,
    PhotoStorageComponent,
    PhotoViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [SbapiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
