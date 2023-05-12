import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SbapiService } from 'src/app/services/sbapi.service';

@Component({
  selector: 'app-photo-view',
  templateUrl: './photo-view.component.html',
  styleUrls: ['./photo-view.component.css']
})
export class PhotoViewComponent implements OnInit, OnDestroy {

  bundleId!: string;
  param$!: Subscription
  title!: string;
  name!: string;
  comments!: string;
  imgUrl : string[] = []
  uploadDate!: Date;

  constructor(
    private router: Router,
    private actRoute: ActivatedRoute,
    private apiSrvc: SbapiService) { }

  ngOnInit(): void {
    this.param$ = this.actRoute.params.subscribe(
      (params) => {
        this.bundleId = params['bundleId'];
        console.log(">>> bundleId = ", this.bundleId);
      }
    );
    this.loadArchives()
  }

  loadArchives() {
    this.apiSrvc.getBundle(this.bundleId)
    .then((response) => {
      // Handle successful response here
      console.log("Response:", response);
      // Extract the desired fields from the response
      this.title = response.title;
      this.name = response.name;
      this.comments = response.comments;
      this.imgUrl = response.urls;
      this.uploadDate = new Date(response.date);

      // Log the retrieved data
      console.log("Title:", this.title);
      console.log("Name:", this.name);
      console.log("Comments:", this.comments);
      console.log("URLs:", this.imgUrl);
      console.log("Upload Date:", this.uploadDate);
    })
    .catch((error) => {
      // Handle error here
      console.error("Error:", error);
    });
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }
}
