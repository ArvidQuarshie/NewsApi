package model;


/**
 * Created by arvid.quarshie on 5/7/2017.
 *
 * POJO object for the Categories
 */


public class Categories {
    private String name, Category, Description;

    public Categories() {
    }

    public Categories(String name, String Category, String Description) {
        this.name = name;
        this.Category = Category;
        this.Description = Description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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

