package com.example.biblioteca.pojo;

public enum StatusCopyType {

    DISPONIBLE(1L,"Disponible"),
    RESERVADO(2L,"Reservado"),
    PRESTADO(3L,"Prestado"),
    PERDIDO(4L,"Perdido"),
    BAJA(5L,"Baja");
    private Long id;
    private String type;

    private StatusCopyType (Long id,String type){
        this.id = id;
        this.type = type;
    }

    public Long getId(){
        return this.id;
    }

    public String getType() {
        return this.type;
    }
}
