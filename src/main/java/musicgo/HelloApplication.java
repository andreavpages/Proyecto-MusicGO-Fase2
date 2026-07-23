package musicgo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/views/bienvenida-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 600);
        stage.setTitle("MusicGo - Fase 2");
        stage.setScene(scene);
        stage.show();
    }
}