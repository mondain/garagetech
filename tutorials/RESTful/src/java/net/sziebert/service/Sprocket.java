package net.sziebert.service;

import java.io.Serializable;

public class Sprocket implements Serializable {

    private static final long serialVersionUID = -3550916423864971002L;

    private int id;
    private String name;

    public Sprocket() {}

    public Sprocket(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return new StringBuilder()
                .append("This is the description for the sprocket with an id of: ")
                .append(getId())
                .toString();
    }
}
