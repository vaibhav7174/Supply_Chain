package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailID;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailID="";
        connection=new DatabaseConnection();
        root=new Group();
        Header header=new Header();
        ProductPage products=new ProductPage();
        ListView<HBox> productList=products.showProducts();

        AnchorPane productPane =new AnchorPane();
        productPane.setLayoutX(50);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(header.root,productPane);

        stage.setTitle("Supply Chain");
        stage.setScene(new Scene(root,500,500));
        stage.show();
        stage.setOnCloseRequest(e->{
            try{
                connection.con.close();
                System.out.println("Connection is closed");
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}