
package recuperatoriointegrador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecuperatorioIntegrador extends Application{

    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\vistaProductos.fxml"));
       Scene scene = new Scene(loader.load());
       stage.setScene(scene);
       stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }


    
}
