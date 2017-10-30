/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
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
public class DAOCliente implements DAO{
    
    private Connection conn;
    
    public DAOCliente()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public void salvar(Object o) {
        Cliente cliente;
        cliente = (Cliente) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO cliente (nome, rg, cpf, endereco, sexo, nascimento) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString (1, cliente.getNome());
            stmt.setString (2, cliente.getRg());
            stmt.setString (3, cliente.getCpf());
            stmt.setString (4, cliente.getEndereco());
            stmt.setString (5, cliente.getSexo());
            stmt.setString (6, cliente.getNascimento());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Cliente cadastrado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Cliente ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }     
    }

    @Override
    public void atualizar(Object o) {
        Cliente cliente;
        cliente = (Cliente) o;

        try
        {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, rg = ?,  cpf = ?, endereco = ?, sexo = ?, nascimento = ? WHERE id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getSexo());
            stmt.setString(6, cliente.getNascimento());
            stmt.setInt(7, cliente.getId());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "*** Cliente Alterado com Sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            Conn.closeMinhaConexao(conn);

        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Cliente ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void remover(int id) {
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt (1, id);
            int cod = JOptionPane.showConfirmDialog(null, "*** Deseja mesmo excluir esse cliente? ***", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            
            if (cod == JOptionPane.YES_OPTION)
            {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "*** Cliente excluido com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao remover o cliente ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getPorID(int id) {
        Cliente cliente = new Cliente();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNascimento(rs.getString("nascimento"));
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta de Clientes ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return cliente;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> ListCliente = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNascimento(rs.getString("nascimento"));
                
                ListCliente.add(cliente);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consuta de clientes ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListCliente;
    }
    
    @Override
    public ArrayList<Object> getAll(String filtro)
    {
        ArrayList<Object> ListCliente = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setNascimento(rs.getString("nascimento"));
                

                ListCliente.add(cliente);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Clientes", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListCliente;
    }


}
