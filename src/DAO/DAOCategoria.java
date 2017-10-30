/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Categoria;
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
public class DAOCategoria implements DAO{
    
    private Connection conn;
    
    public DAOCategoria()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public void salvar(Object o) {
        Categoria categoria;
        categoria = (Categoria) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO categoria (nome) VALUES (?)");
            stmt.setString (1, categoria.getNome());
                        
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Categoria cadastrada com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Categoria ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }     
    }

    @Override
    public void atualizar(Object o) {
        Categoria categoria;
        categoria = (Categoria) o;

        try
        {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE categoria SET nome = ? WHERE id = ?");
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "*** Categoria Alterada com Sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            Conn.closeMinhaConexao(conn);

        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar a Categoria ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void remover(int id) {
                
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM categoria WHERE id = ?");
            stmt.setInt (1, id);
            int cod = JOptionPane.showConfirmDialog(null, "*** Deseja mesmo excluir essa categoria? ***", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            
            if (cod == JOptionPane.YES_OPTION)
            {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "*** Categoria excluida com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao remover a categoria ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getPorID(int id) {
        
        Categoria categoria = new Categoria();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM categoria WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta da Categoria ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return categoria;
    }

    @Override
    public ArrayList<Object> getAll() {
        
        ArrayList<Object> ListCategoria = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM categoria");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                
                ListCategoria.add(categoria);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consuta de Categorias ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListCategoria;
    }

    @Override
    public ArrayList<Object> getAll(String filtro) {
        
        ArrayList<Object> ListCategoria = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM categoria WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));

                ListCategoria.add(categoria);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Categorias", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListCategoria;
    }
    
}
