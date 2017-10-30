/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuarios;
import connection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author maeda
 */
public class DAOUsuarios implements DAOLogin {
    
    private Connection conn;
    
    public DAOUsuarios()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public Usuarios logar(String usuario, String senha) {
        Usuarios usuarios = new  Usuarios();
        ResultSet rs;
        
        try
        {
            String selectSQL = "SELECT * FROM usuario WHERE usuario = ? and senha = ?";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(selectSQL);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                usuarios.setUsuario(rs.getString("usuario"));
                usuarios.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta do Usuario", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
        return usuarios;
    }

    @Override
    public void cadastrar(Object o) {
        Usuarios usuarios;
        usuarios = (Usuarios) o;

        try
        {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO usuario (usuario, senha, funcionario_id) VALUE (?, ?, ?)");
            stmt.setString(1, usuarios.getUsuario());
            stmt.setString(2, usuarios.getSenha());
            stmt.setInt(3, usuarios.getFuncionario_id());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "*** Usuário cadastrado com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            Conn.closeMinhaConexao(conn);

        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Erro ao cadastrar o usuario ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> ListUsuarios = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Usuarios usuarios = new Usuarios();
                
                usuarios.setId(rs.getInt("id"));
                usuarios.setUsuario(rs.getString("usuario"));
                usuarios.setFuncionario_id(rs.getInt("funcionario_id"));
                
                ListUsuarios.add(usuarios);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consuta de Usuários ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListUsuarios;
    }
    
    @Override
    public ArrayList<Object> getAll(String filtro)
    {
        ArrayList<Object> ListUsuarios = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Usuarios usuarios = new Usuarios();
                
                usuarios.setId(rs.getInt("id"));
                usuarios.setUsuario(rs.getString("usuario"));
                usuarios.setFuncionario_id(rs.getInt("funcionario_id"));
                
                ListUsuarios.add(usuarios);

            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Usuários", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListUsuarios;
    }
}
