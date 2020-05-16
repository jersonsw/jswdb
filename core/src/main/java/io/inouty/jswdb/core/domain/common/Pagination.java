package io.inouty.jswdb.core.domain.common;

import java.util.List;
import java.util.Objects;

public final class Pagination<T> {

    private final List<T> content;
    private final long currentPage;
    private final long totalPages;
    private final long totalRecords;

    private Pagination(Pagination.Builder<T> builder) {
        this.content = builder.content;
        this.currentPage = builder.currentPage;
        this.totalPages = builder.totalPages;
        this.totalRecords = builder.totalRecords;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Pagination pagination) {
        return builder()
                .withContent(pagination.content)
                .withCurrentPage(pagination.currentPage)
                .withTotalPages(pagination.totalPages)
                .withTotalRecords(pagination.totalRecords);
    }

    public List<T> getContent() {
        return content;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public boolean isLast() {
        return currentPage == totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination<?> that = (Pagination<?>) o;
        return currentPage == that.currentPage &&
                totalPages == that.totalPages &&
                totalRecords == that.totalRecords &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, currentPage, totalPages, totalRecords);
    }

    public static final class Builder<T> {
        private List<T> content;
        private long currentPage;
        private long totalPages;
        private long totalRecords;

        private Builder() {}

        public Builder withContent(List<T> content) {
            this.content = content;
            return this;
        }

        public Builder withCurrentPage(long currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder withTotalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder withTotalRecords(long totalRecords) {
            this.totalRecords = totalRecords;
            return this;
        }

        public Pagination build() {
            return new Pagination(this);
        }
    }
}
