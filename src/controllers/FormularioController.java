/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DatoInvalidoException;
import models.Medicamento;
import models.Objetivos;
import models.ProductoFarmacia;
import models.Suplemento;


public class FormularioController implements Initializable {

    
    @FXML
    private ComboBox<String> cmbTipo;
    @FXML
    private Label lblNomVariable;
    @FXML
    private Label lblVariable;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDosis;
    @FXML
    private DatePicker dtpFechaVencimiento;
    @FXML
    private CheckBox chkReceta;
    @FXML
    private ComboBox<Objetivos> cmbObjetivo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    private ProductoFarmacia producto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTipo.getItems().addAll("MEDICAMENTO","SUPLEMENTO");
        this.cmbObjetivo.getItems().addAll(Objetivos.values());
    } 
    
    //Pone los datos del producto seleccionado en las casillas de los datos
    public void setProducto(ProductoFarmacia prod){
        this.producto = prod;
        
        if(this.producto != null){
            if(this.producto instanceof Medicamento){
                Medicamento m = (Medicamento) this.producto;
                this.cmbTipo.setValue("MEDICAMENTO");
                this.cmbTipo.setDisable(true);
                this.chkReceta.setSelected(m.necesitaReceta());
                this.cmbObjetivo.setDisable(true);
                
            }else{
                Suplemento s = (Suplemento) this.producto;
                this.cmbTipo.setValue("SUPLEMENTO");
                this.cmbTipo.setDisable(true);
                this.cmbObjetivo.setValue(s.getObjetivo());
                this.chkReceta.setDisable(true);
            }
            
            this.txtNombre.setText(this.producto.getNombre());
            this.txtDosis.setText(String.valueOf(this.producto.getDosis()));
            this.dtpFechaVencimiento.setValue(this.producto.getFechaDeVencimiento());
        }
    }
    
    //devuelve el producto agregado o modificado
    public ProductoFarmacia getProducto(){
        return this.producto;
    }

    @FXML
    private void guardar(ActionEvent event) {
       
        try {
            String nombre = this.txtNombre.getText();
            int dosis = Integer.parseInt(this.txtDosis.getText());
            LocalDate fechaVencimiento = this.dtpFechaVencimiento.getValue();
            
            //si se esta agregando un producto se crea uno con los datos ingresados
            if(this.producto == null){
                if (this.cmbTipo.getValue() != null) {
                    if (cmbTipo.getValue().equals("MEDICAMENTO")) {
                        boolean receta = this.chkReceta.isSelected();
                        this.producto = new Medicamento(nombre, dosis, fechaVencimiento, receta);
                    } else {
                        Objetivos objetivo = this.cmbObjetivo.getValue();
                        this.producto = new Suplemento(nombre, dosis, fechaVencimiento, objetivo);
                    }
                } else {
                    mostrarError("ERROR", "Seleccione un tipo de producto", "REINTENTAR");
                }
                
            //sino se modifican los datos en el producto seleccionado
            }else{ 
                if(cmbTipo.getValue().equals("MEDICAMENTO")){
                    Medicamento m =(Medicamento) this.producto;
                    m.setNombre(nombre);
                    m.setDosis(dosis);
                    m.setFechaDeVencimiento(fechaVencimiento);
                    m.setReceta(this.chkReceta.isSelected());
                    
                    this.producto = m;
                }else{
                    Suplemento s =(Suplemento) this.producto;
                    s.setNombre(nombre);
                    s.setDosis(dosis);
                    s.setFechaDeVencimiento(fechaVencimiento);
                    s.setObjetivo(this.cmbObjetivo.getValue());
                    
                    this.producto = s;
                }
            }
            
            
            
           this.cerrarVentana(); 
            
        } catch(DatoInvalidoException | NumberFormatException  e) {
            mostrarError("ERROR","Los datos ingresados son invalidos (sin nombre, dosis no es un numero entero, o fecha de vencimiento pasada)", "REINTENTE");
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.cerrarVentana();
    }
    
    //Recibe el titulo y los contenidos de una alerta y la muestra
    private static void mostrarError(String msjTitulo, String msjHeader, String msjContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(msjTitulo);
        alert.setHeaderText(msjHeader);
        alert.setContentText(msjContent);
        alert.show();
    }
    private void cerrarVentana(){
        Stage stage = (Stage)this.btnCancelar.getScene().getWindow();
        stage.close();
    }
}
