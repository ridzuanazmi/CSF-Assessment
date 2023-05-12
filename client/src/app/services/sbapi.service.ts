import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

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

}
