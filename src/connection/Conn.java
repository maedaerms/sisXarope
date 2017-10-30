/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author maeda
 */
public class Conn {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_java?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getMinhaConexao()
    {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static void closeMinhaConexao(Connection c)
    {
        try{
            if (c != null)
            {
                c.close();
            }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                }
    }
}
