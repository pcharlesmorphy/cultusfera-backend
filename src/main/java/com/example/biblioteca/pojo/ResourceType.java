package com.example.biblioteca.pojo;

public enum ResourceType {

    LIBRO(1,"Libro"),
    PELICULA(2,"Pelicula"),
    ALBUM(3,"Album"),
    REVISTA(4,"Revista");

    private Integer id;
    private String type;

    private ResourceType (Integer id,String type){
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public Integer getId(){
        return this.id;
    }

}
