/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.ResultSet;

/**
 *
 * @author sesi2dib
 */
public class MySQLConnection {
private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
private static final String URL= "jdbc:mysql://172.16.0.30:3306/mariana_dukemarket";

private static final String USER= "mariana";
private static final String PASS= "2diSenai";
//continuação
public static Connection getConnection(){
    try{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    } catch (SQLException ex){
        throw new RuntimeException("Erro na Conexão com o BD. Verifique!", ex);
    }    catch (ClassNotFoundException ex) {
        throw new RuntimeException("Falha na carga do Driver", ex);
    }
}
//continuação
public static void closeConnection(Connection coon){
    try{
        if (coon != null){
            coon.close();
        }
    }catch(SQLException ex){
           Logger.getLogger(
           MySQLConnection.class.getName()).log(Level.SEVERE,null,ex);
                
    }
}
        //continuação
        public static void closeConnection(Connection coon, PreparedStatement stmt){
    Connection conn = null;
            
                closeConnection(conn);
            
            try{
                if (stmt !=null){
                    stmt.close();
                }
            } catch (SQLException ex){
                Logger.getLogger(
                        MySQLConnection.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
        //continuação
        public static void closeConnection (Connection conn, 
                PreparedStatement stmt,
                ResultSet rs){
            closeConnection(conn, stmt);
            
            try{
                if(rs !=null){
                    rs.close();
                }
            } catch (SQLException ex){
                Logger.getLogger(
                MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}


    



