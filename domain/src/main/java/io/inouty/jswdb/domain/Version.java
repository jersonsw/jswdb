package io.inouty.jswdb.domain;

public enum Version {

    normal("normal"),
    extended("extended");

    private String name;

    Version(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
