package io.inouty.jswdb.domain;

import java.util.Objects;
import java.util.Optional;

public final class SearchPagination {

    private final Integer startRecord;
    private final Integer endRecord;
    private final Integer recordsCount;
    private final Optional<String> nextPageUrl;

    private SearchPagination(Builder builder){
        this.startRecord = builder.startRecord;
        this.endRecord = builder.endRecord;
        this.recordsCount = builder.recordsCount;
        this.nextPageUrl = builder.nextPageUrl;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static Builder builder(SearchPagination p){
        return builder()
                .withStartRecord(p.startRecord)
                .withEndRecord(p.endRecord)
                .withRecordsCount(p.recordsCount)
                .withNextPageUrl(p.nextPageUrl);
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public Integer getEndRecord() {
        return endRecord;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public Optional<String> getNextPageUrl() {
        return nextPageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPagination that = (SearchPagination) o;
        return Objects.equals(startRecord, that.startRecord) &&
                Objects.equals(endRecord, that.endRecord) &&
                Objects.equals(recordsCount, that.recordsCount) &&
                Objects.equals(nextPageUrl, that.nextPageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startRecord, endRecord, recordsCount, nextPageUrl);
    }


    public static final class Builder {

        private Integer startRecord;
        private Integer endRecord;
        private Integer recordsCount;
        private Optional<String> nextPageUrl;

        private Builder() {}

        public Builder withStartRecord(Integer startRecord) {
            this.startRecord = startRecord;
            return this;
        }

        public Builder withEndRecord(Integer endRecord) {
            this.endRecord = endRecord;
            return this;
        }

        public Builder withRecordsCount(Integer recordsCount) {
            this.recordsCount = recordsCount;
            return this;
        }

        public Builder withNextPageUrl(Optional<String> nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
            return this;
        }

        public SearchPagination build() {
            return new SearchPagination(this);
        }
    }
}
