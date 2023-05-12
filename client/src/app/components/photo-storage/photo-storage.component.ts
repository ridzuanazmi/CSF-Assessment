import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Archive } from 'src/app/model/Upload';
import { SbapiService } from 'src/app/services/sbapi.service';

@Component({
  selector: 'app-photo-storage',
  templateUrl: './photo-storage.component.html',
  styleUrls: ['./photo-storage.component.css']
})
export class PhotoStorageComponent implements OnInit {

  archives: Archive[] = [];

  constructor(
    private http: HttpClient,
    private router: Router,
    private apiSrvc: SbapiService) { }

  ngOnInit(): void {
    this.retrieveArchives();
  }

  retrieveArchives(): void {
    this.apiSrvc.getBundles()
      .then((response: Archive[]) => {
        this.archives = response;
        console.log("Archives:", this.archives);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }
}
