package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arvid.quarshie on 5/7/2017.
 */


public class Article {
    private String title, description, urlToImage;

    public Article() {
    }

    public Article(String title, String description, String urlToImage) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
//public class Article {
//
//
//        private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//
//        private String author;
//        private String title;
//        private String description;
//        private String url;
//        private String urlToImage;
//        private Date publishedAt;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getAuthor() {
//            return author;
//        }
//
//        public void setAuthor(String author) {
//            this.author = author;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUrlToImage() {
//        return urlToImage;
//    }
//
//    public void setUrlToImage(String urlToImage) {
//        this.urlToImage = urlToImage;
//    }
//
//    public Date getPublishedAt() {
//        return publishedAt;
//    }
//
//    public void setPublishedAt(Date publishedAt) {
//        this.publishedAt = publishedAt;
//    }
//}

