package ibf2022.batch2.csf.backend.models;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "archives")
public class Archive {
    
    public String bundleId;
    public Date date;
    public String title;
    public String name;
    public String comments;
    public String[] urls;
    
    public String getBundleId() {
        return bundleId;
    }
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String[] getUrls() {
        return urls;
    }
    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Archive [bundleId=" + bundleId + ", date=" + date + ", title=" + title + ", name=" + name
                + ", comments=" + comments + ", urls=" + Arrays.toString(urls) + "]";
    }
}
