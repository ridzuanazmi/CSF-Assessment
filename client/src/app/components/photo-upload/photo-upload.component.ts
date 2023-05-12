import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SbapiService } from 'src/app/services/sbapi.service';

@Component({
  selector: 'app-photo-upload',
  templateUrl: './photo-upload.component.html',
  styleUrls: ['./photo-upload.component.css']
})
export class PhotoUploadComponent implements OnInit {

  // Create a reference to the input element in HTML using its name
  // it is a full file object. The formControlName "picture" is only a path
  // hence we use uploaded picture instead
  @ViewChild('file') zipFile!: ElementRef;
  uploadForm!: FormGroup;
  bundleId!: string;

  constructor(
    private fb: FormBuilder,
    private apiSrvc: SbapiService,
    private router: Router) { }

  ngOnInit(): void {
    this.uploadForm = this.createUploadForm();
  }

  onSubmit() {
    console.log("onSubmit(): ", this.uploadForm.value); // check
    console.log("onSubmit(): ",this.zipFile);

    const formData = new FormData();
    formData.set('name', this.uploadForm.get('name')?.value);
    formData.set('title', this.uploadForm.get('title')?.value);
    formData.set('comments', this.uploadForm.get('comments')?.value);
    formData.set('archive', this.zipFile.nativeElement.files[0]);

    this.apiSrvc.upload(formData)
    .then((res) => {
      console.log("onSubmit(): response = ", res);
      this.bundleId = res.bundleId;
      this.router.navigate(['/view', this.bundleId]); // navigate to View 2 with bundleId params
    })
    .catch((err) => {
      alert(err); // dialog pop up box
      console.error("upload(): error = ", err);
    })
    this.uploadForm.reset();
  }

  private createUploadForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>('', [Validators.required]),
      archive: this.fb.control(null, [Validators.required])
    });
  }
}
