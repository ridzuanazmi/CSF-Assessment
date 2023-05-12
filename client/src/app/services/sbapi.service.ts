import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SbapiService {

  // private postUploadUrl = 'http://localhost:8080/api/upload';

  constructor(private http: HttpClient) { }

  upload(formData: FormData): Promise<any> {
    
    return firstValueFrom(
      this.http.post('http://localhost:8080/upload', formData)
    );
  }

  // get bundleId from server
  getBundle(bundleId: string): Promise<any> {
    const url = `http://localhost:8080/bundle/${bundleId}`;
    const headers = new HttpHeaders().set("Accept", "application/json");

    return lastValueFrom(this.http.get(url, { headers }));
  }

}
