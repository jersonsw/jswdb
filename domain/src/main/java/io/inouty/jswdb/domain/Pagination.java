package io.inouty.jswdb.domain;

import java.util.List;

public class Pagination<T> {

    private List<T> content;
    private long currentPage;
    private long totalPages;
    private long totalRecords;

    public Pagination() {
    }

    public Pagination(List<T> content, int currentPage, int totalPages, int totalRecords) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public boolean isLast() {
        return currentPage == totalPages;
    }
}
