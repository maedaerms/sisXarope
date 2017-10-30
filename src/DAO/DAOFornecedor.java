/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fornecedor;
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
public class DAOFornecedor implements DAO{
    
    private Connection conn;
    
    public DAOFornecedor()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public void salvar(Object o) {
        
        Fornecedor fornecedor;
        fornecedor = (Fornecedor) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO fornecedor (nome, cnpj, ie, endereco, telefone) VALUES (?, ?, ?, ?, ?)");
            stmt.setString (1, fornecedor.getNome());
            stmt.setString (2, fornecedor.getCnpj());
            stmt.setString (3, fornecedor.getIe());
            stmt.setString (4, fornecedor.getEndereco());
            stmt.setString (5, fornecedor.getTelefone());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Fornecedor cadastrado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Fornecedor ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }     
    }

    @Override
    public void atualizar(Object o) {
        
        Fornecedor fornecedor;
        fornecedor = (Fornecedor) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE fornecedor SET nome = ?, cnpj = ?,  ie = ?, endereco = ?, telefone = ? WHERE id = ?");
            stmt.setString (1, fornecedor.getNome());
            stmt.setString (2, fornecedor.getCnpj());
            stmt.setString (3, fornecedor.getIe());
            stmt.setString (4, fornecedor.getEndereco());
            stmt.setString (5, fornecedor.getTelefone());
            stmt.setInt (6, fornecedor.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Fornecedor atualizado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao Atualizar Fornecedor ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }     
    }

    @Override
    public void remover(int id) {
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
            stmt.setInt (1, id);
            int cod = JOptionPane.showConfirmDialog(null, "*** Deseja mesmo excluir esse fornecedor? ***", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            
            if (cod == JOptionPane.YES_OPTION)
            {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "*** Fornecedor excluido com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao remover o fornecedor ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getPorID(int id) {
        
        Fornecedor fornecedor = new Fornecedor();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setIe(rs.getString("ie"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setTelefone(rs.getString("telefone"));
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta de Fornecedores ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return fornecedor;
    }

    @Override
    public ArrayList<Object> getAll() {
        
        ArrayList<Object> ListFornecedor = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM fornecedor");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setIe(rs.getString("ie"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setTelefone(rs.getString("telefone"));
                
                ListFornecedor.add(fornecedor);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consuta de fornecedores ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListFornecedor;
    }

    @Override
    public ArrayList<Object> getAll(String filtro) {
        
        ArrayList<Object> ListFornecedor = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM fornecedor WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setIe(rs.getString("ie"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setTelefone(rs.getString("telefone"));
                
                ListFornecedor.add(fornecedor);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Fornecedores", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListFornecedor;
    }
    
}
