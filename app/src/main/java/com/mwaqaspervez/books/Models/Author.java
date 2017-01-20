package com.mwaqaspervez.books.Models;


public class Author {

    private String name;
    private int pic;

    public Author(String name, int pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public int getPic() {
        return pic;
    }
}
