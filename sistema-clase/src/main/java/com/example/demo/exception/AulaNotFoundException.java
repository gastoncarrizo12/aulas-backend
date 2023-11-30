package com.example.demo.exception;

public class AulaNotFoundException extends RuntimeException{
    public AulaNotFoundException(Long id){
        super("No se encontro un usuario con ese ID" + id);
    }
}
