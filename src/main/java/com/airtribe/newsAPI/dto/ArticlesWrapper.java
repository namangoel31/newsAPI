//package com.airtribe.newsAPI.dto;
//
//import java.util.List;
//
//
//public class ArticlesWrapper {
//
//    public List<Article> articles;
//
//    public ArticlesWrapper(List<Article> articles) {
//        this.articles = articles;
//    }
//
//    public List<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(List<Article> articles) {
//        this.articles = articles;
//    }
//}
//package com.airtribe.newsAPI.dto;
//
//import java.util.List;
//
//public class ArticlesWrapper {
//
//    private ArticlesResponse articles;  // This should match the JSON structure
//    // You can also use a List<Article> if the JSON returns a direct array.
//
//    public ArticlesWrapper(ArticlesResponse articles) {
//        this.articles = articles;
//    }
//
//    public ArticlesResponse getArticles() {
//        return articles;
//    }
//
//    public void setArticles(ArticlesResponse articles) {
//        this.articles = articles;
//    }
//
//    public static class ArticlesResponse {
//        private List<Article> list; // Assuming 'list' contains the articles
//
//        public List<Article> getList() {
//            return list;
//        }
//
//        public void setList(List<Article> list) {
//            this.list = list;
//        }
//    }
//}

package com.airtribe.newsAPI.dto;

import java.util.List;

public class ArticlesWrapper {

    private Articles articles;  // A new class to represent the "articles" object

    public ArticlesWrapper(Articles articles) {
        this.articles = articles;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    public static class Articles {
        private List<Article> results;  // List of articles inside "results"

        // Getter and setter for "results"
        public List<Article> getResults() {
            return results;
        }

        public void setResults(List<Article> results) {
            this.results = results;
        }
    }
}


