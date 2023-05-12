export interface Upload {
    name: string,
    title: string,
    comments:string,
    archive: File
}

export class Archive {
    bundleId: string;
    date: Date;
    title: string;
    name: string;
    comments: string;
    urls: string[];
  
    constructor(
      bundleId: string,
      date: Date,
      title: string,
      name: string,
      comments: string,
      urls: string[]
    ) {
      this.bundleId = bundleId;
      this.date = date;
      this.title = title;
      this.name = name;
      this.comments = comments;
      this.urls = urls;
    }
  }