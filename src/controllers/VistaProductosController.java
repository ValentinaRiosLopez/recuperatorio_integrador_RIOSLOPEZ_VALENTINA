
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.GestorGuardadoSerializacion;
import models.ProductoFarmacia;


public class VistaProductosController implements Initializable {

    @FXML
    private ListView<ProductoFarmacia> lstProductos;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    
    private GestorGuardadoSerializacion guardado = new GestorGuardadoSerializacion("productosFarmacia");
    
    private List<ProductoFarmacia> productos;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //se lee el contenido del archivo
        List<ProductoFarmacia> contenido = guardado.leerArchivo();
        
        //si esta vacio se crea una lista y sino se cargan los datos del archivos
        if(contenido == null){
            this.productos = new ArrayList<>();
        }else{
            this.productos = contenido;
        }
        
        this.actualizarLista();
    }    

    @FXML
    private void agregar(ActionEvent event) {
        this.abrirFormulario(null);
        
    }

    @FXML
    private void modificar(ActionEvent event) {
        ProductoFarmacia seleccionado = this.lstProductos.getSelectionModel().getSelectedItem();
        this.abrirFormulario(seleccionado);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        ProductoFarmacia seleccionado = this.lstProductos.getSelectionModel().getSelectedItem();
        
        if(seleccionado != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMAR ELIMINACION");
            alert.setHeaderText("Desea eliminar el producto "+ seleccionado.getNombre()+"?");
            alert.setContentText(seleccionado.toString());
            
            Optional<ButtonType> resultado = alert.showAndWait();
            
            if(resultado.isPresent() && resultado.get() == ButtonType.OK){
                this.productos.remove(seleccionado);
                this.guardado.serializar(productos);
                this.actualizarLista();
            }
            
        }
    }
    
    private void actualizarLista(){
        this.lstProductos.getItems().clear();
        this.lstProductos.getItems().addAll(this.productos);
      
    }
    
    //Recibe el titulo y los contenidos de una alerta y la muestra
    private static void mostrarError(String msjTitulo, String msjHeader, String msjContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(msjTitulo);
        alert.setHeaderText(msjHeader);
        alert.setContentText(msjContent);
        alert.show();
    }
    
    private void abrirFormulario(ProductoFarmacia producto){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\formulario.fxml"));
            Scene scene = new Scene(loader.load());
            FormularioController controller = loader.getController();
            controller.setProducto(producto);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(scene);
            stage.showAndWait();
            
            ProductoFarmacia resultado = controller.getProducto();
            
            if(resultado != null){
                if(producto == null){
                    if(!this.productos.contains(resultado)){
                        this.productos.add(resultado);
                        
                    }
                }
                
                this.guardado.serializar(productos);
                this.actualizarLista();
            }
            
        } catch (Exception e) {
        }
    }
    
   
    
}
