/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
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
public class DAOProduto implements DAO {
    
    private Connection conn;
    
    public DAOProduto()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public void salvar(Object o) {
        Produto produto;
        produto = (Produto) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO produto (nome, custo, venda, qtd, fabricacao, validade, categoria_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString (1, produto.getNome());
            stmt.setDouble (2, produto.getCusto());
            stmt.setDouble (3, produto.getVenda());
            stmt.setInt (4, produto.getEstoque());
            stmt.setString (5, produto.getFabricacao());
            stmt.setString (6, produto.getValidade());
            stmt.setInt (7, produto.getCategoria());
            stmt.setInt (8, produto.getFornecedor());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Produto cadastrado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao cadastrar o produto ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void atualizar(Object o) {
        Produto produto;
        produto = (Produto) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE produto SET nome = ?, custo = ?,  venda = ?, qtd = ?, fabricacao = ?, validade = ?, categoria_id = ?, fornecedor_id = ? WHERE id = ?");
            stmt.setString (1, produto.getNome());
            stmt.setDouble (2, produto.getCusto());
            stmt.setDouble (3, produto.getVenda());
            stmt.setInt (4, produto.getEstoque());
            stmt.setString (5, produto.getFabricacao());
            stmt.setString (6, produto.getValidade());
            stmt.setInt (7, produto.getCategoria());
            stmt.setInt (8, produto.getFornecedor());
            stmt.setInt (9, produto.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Produto atualizado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao atualizar o produto ***", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }

    @Override
    public void remover(int id) {
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt (1, id);
            int cod = JOptionPane.showConfirmDialog(null, "Digite o número do produto a excluir?", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            
            if (cod == JOptionPane.YES_OPTION)
            {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "*** Produto excluido com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao remover o produto ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getPorID(int id) {
        Produto produto = new Produto();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCusto(rs.getDouble("custo"));
                produto.setVenda(rs.getDouble("venda"));
                produto.setEstoque(rs.getInt("qtd"));
                produto.setFabricacao(rs.getString("fabricacao"));
                produto.setValidade(rs.getString("validade"));
                produto.setCategoria(rs.getInt("categoria_id"));
                produto.setFornecedor(rs.getInt("fornecedor_id"));
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta do produto ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return produto;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> ListProduto = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCusto(rs.getDouble("custo"));
                produto.setVenda(rs.getDouble("venda"));
                produto.setEstoque(rs.getInt("qtd"));
                produto.setFabricacao(rs.getString("fabricacao"));
                produto.setValidade(rs.getString("validade"));
                produto.setCategoria(rs.getInt("categoria_id"));
                produto.setFornecedor(rs.getInt("fornecedor_id"));
                
                ListProduto.add(produto);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta de produtos ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListProduto;
    }
    
    @Override
    public ArrayList<Object> getAll(String filtro)
    {
        ArrayList<Object> ListProduto = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCusto(rs.getDouble("custo"));
                produto.setVenda(rs.getDouble("venda"));
                produto.setEstoque(rs.getInt("qtd"));
                produto.setFabricacao(rs.getString("fabricacao"));
                produto.setValidade(rs.getString("validade"));
                produto.setCategoria(rs.getInt("categoria_id"));
                produto.setFornecedor(rs.getInt("fornecedor_id"));

                ListProduto.add(produto);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Produtos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListProduto;
    }

}
