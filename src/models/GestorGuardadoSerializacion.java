
package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class GestorGuardadoSerializacion<T> {
    private final File archivo; 
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    
    public GestorGuardadoSerializacion(String nombre){
        archivo = new File(nombre+".dat");
        this.oos = null;
        this.ois = null;
    }
    
    
    //lee el archivo y devuelve una lista con el contenido
    public ArrayList<T> leerArchivo(){
        
        ArrayList<T> lista = new ArrayList<>();
        
        try {
            FileInputStream fis = new FileInputStream(this.archivo);
            this.ois = new ObjectInputStream(fis);
            
            lista = (ArrayList<T>)ois.readObject();
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR No se encontro el archivo.");
        } catch (IOException | ClassNotFoundException ex ){
            System.out.println("ERROR En lectura ");
        }finally{
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException ex){
                System.out.println("ERROR Al cerrar el archivo.");
            }
        }
        
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }
        
    }
    
    //guarda una lista de objetos en el archivo
    public void serializar(List<T> lista){
        try {
            FileOutputStream fos = new FileOutputStream(this.archivo);
            oos = new ObjectOutputStream(fos);
           
            oos.writeObject(lista);
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR archivo no encontrado.");
        } catch (IOException ex) {
            System.out.println("ERROR al serializar.");
        }finally{
            try {
                if(oos != null){
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("ERROR al cerrar el archivo.");
            }
        }
        
        
    }
}

