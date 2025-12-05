
package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Suplemento extends ProductoFarmacia implements Serializable{
    private Objetivos objetivo;

    public Suplemento(String nombre, int dosis, LocalDate fecha,Objetivos objetivo) {
        super(nombre, dosis, fecha);
        if(objetivo != null){
            this.objetivo = objetivo;
        }else{
            throw new DatoInvalidoException();
        }
        
    }

    public Objetivos getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivos objetivo) {
        if(objetivo != null){
            this.objetivo = objetivo;
        }else{
            throw new DatoInvalidoException();
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre.toUpperCase()).append(" | DOSIS: ").append(this.dosis);
        sb.append(" | FECHA DE VENCIMIENTO: ").append(this.fechaDeVencimiento).append(" | OBJETIVO: ");
        sb.append(this.objetivo.toString().toLowerCase());

        return sb.toString();
    }
    
    
    
}
