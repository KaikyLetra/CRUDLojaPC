package com.template.main;

import com.template.validator.ComponentesValidator;
import com.template.validator.IComponentesValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        IComponentesValidator cValidator = new ComponentesValidator();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load(),600,400);

        stage.setTitle("Cadastro de Componente");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
