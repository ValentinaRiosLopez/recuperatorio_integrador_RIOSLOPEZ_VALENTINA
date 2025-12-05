
package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Medicamento extends ProductoFarmacia implements Serializable{
    private boolean receta;

    public Medicamento(String nombre, int dosis, LocalDate fecha, boolean receta) {
        super(nombre, dosis, fecha);
        
        this.receta = receta;
    }

    public boolean necesitaReceta() {
        return receta;
    }

    public void setReceta(boolean receta) {
        this.receta = receta;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre.toUpperCase()).append(" | DOSIS: ").append(this.dosis);
        sb.append(" | FECHA DE VENCIMIENTO: ").append(this.fechaDeVencimiento).append(" | RECETA: ");
        if(this.receta){
            sb.append("Si");
        }else{
            sb.append("No");
        }
        
        return sb.toString();
    }
    
    
}
