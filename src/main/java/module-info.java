module DukemarketJavaFX {
 
   
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    requires java.sql;

    opens br.dev.mariana.dukemarket.javafx to javafx.fxml;
    opens br.dev.mariana.dukemarket.javafx.controller to javafx.fxml;

    exports br.dev.mariana.dukemarket.javafx;
    exports br.dev.mariana.dukemarket.javafx.DAO;
    exports br.dev.mariana.dukemarket.javafx.controller;
    exports br.dev.mariana.dukemarket.javafx.model;
    exports connection;
    
    requires mysql.connector.java;
}