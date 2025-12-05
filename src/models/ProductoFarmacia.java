
package models;

import java.io.Serializable;
import java.time.LocalDate;
import models.DatoInvalidoException;


public class ProductoFarmacia implements Serializable{
    protected String nombre;
    protected int dosis;
    protected LocalDate fechaDeVencimiento;

    public ProductoFarmacia(String nombre, int dosis, LocalDate fecha) {
        if(fecha.isAfter(LocalDate.now()) || nombre != null || dosis > 0){
            this.nombre = nombre;
            this.dosis = dosis;
            this.fechaDeVencimiento = fecha;
        }else{
            throw new DatoInvalidoException();
        }
        
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null){
            this.nombre = nombre;
        }else{
            throw new DatoInvalidoException();
        }
        
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        if(dosis > 0){
            this.dosis = dosis;
        }else{
            throw new DatoInvalidoException();
        }
        
    }

    public LocalDate getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
        
        if(!fechaDeVencimiento.isAfter(LocalDate.now())){
            this.fechaDeVencimiento = fechaDeVencimiento;
        }else{
            throw new DatoInvalidoException();
        }
        
    }
    
    
    
    
}
