package io.inouty.jswdb.core.entities;

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
