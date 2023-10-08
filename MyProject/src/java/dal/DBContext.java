/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.BaseEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public abstract class DBContext <T extends BaseEntity>{
   protected Connection connection;
   
   public DBContext()
   {
       try {
           String url = "jdbc:sqlserver://localhost:1433;databaseName=Account_PRJ301";
           String user = "sa";
           String pass = "123";
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           connection = DriverManager.getConnection(url, user, pass);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   public abstract ArrayList<T> list();
   public abstract void insert(T entity);
   public abstract void update(T entity);
   public abstract void delete(T entity);
   public abstract T get(T entity);
}