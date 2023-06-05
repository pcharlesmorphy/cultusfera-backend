package com.example.biblioteca.pojo;

public enum TransactionStatusType {
    PRESTAMO_EN_CURSO(1L,"Prestamo en curso"),
    PRESTAMO_FINALIZADO(2L,"Prestamo finalizado"),
    PRESTAMO_VENCIDO(3L,"Prestamo vencido"),
    RESERVA_EN_CURSO(4L,"Reserva en curso"),
    RESERVA_FINALIZADA(5L,"Reserva finalizada");
    private Long id;
    private String type;

    private TransactionStatusType (Long id,String type){
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
