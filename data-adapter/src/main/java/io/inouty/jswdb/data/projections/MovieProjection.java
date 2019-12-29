package io.inouty.jswdb.data.projections;

import java.sql.Time;

interface MovieProjection {
    String getId();
    String getTitle();
    String getSummary();
    Integer getYear();
    Time getDuration();
    Double getRatingAvg();
    Long getRatingCount();
    String getProfileImg();
    String getCertificate();
    String getTrailerUrl();
    Integer getMetaScore();
}
