package com.web.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book implements Serializable {

    private Book.Meta meta;
    private List<Document> documents;

    public Book.Meta getMeta() {
        return meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    @Getter
    public static class Meta {
        int total_count;
        int pageable_count;
        Boolean is_end;

        public int getPageable_count() {
            return (int) Math.ceil(pageable_count / 10.0);
        }
    }

    @Getter
    public static class Document{
        String title;
        String thumbnail;
        String contents;
        String isbn;
        List<String> authors;
        String publisher;
        String datetime;
        int price;
        int sale_price;

        public String getIsbn() {
            if (isbn.trim().contains(" ")) {
                return isbn.split(" ")[0];
            } else {
                return isbn;
            }
        }

        public String getDatetime() {
            if (datetime.length() > 10) {
                return datetime.substring(0, 10);
            } else {
                return datetime;
            }
        }
    }
}
