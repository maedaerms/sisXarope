/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Funcionario;
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
public class DAOFuncionario implements DAO {

    private Connection conn;
    
    public DAOFuncionario()
    {
        conn = Conn.getMinhaConexao();
    }

    @Override
    public void salvar(Object o) {
        Funcionario funcionario;
        funcionario = (Funcionario) o;
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO funcionario (nome, rg, cpf, endereco, sexo, cargo, salario, nascimento, admissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString (1, funcionario.getNome());
            stmt.setString (2, funcionario.getRg());
            stmt.setString (3, funcionario.getCpf());
            stmt.setString (4, funcionario.getEndereco());
            stmt.setString (5, funcionario.getSexo());
            stmt.setString (6, funcionario.getCargo());
            stmt.setDouble (7, funcionario.getSalario());
            stmt.setString (8, funcionario.getNascimento());
            stmt.setString (9, funcionario.getAdmissao());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "*** Funcionario cadastrado com sucesso ***", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
           
            Conn.closeMinhaConexao(conn);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Funcionario ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }     
    }

    @Override
    public void atualizar(Object o) {
        Funcionario funcionario;
        funcionario = (Funcionario) o;

        try
        {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE funcionario SET nome = ?, rg = ?,  cpf = ?, endereco = ?, sexo = ?, cargo = ?, salario = ?, nascimento = ?, admissao = ? WHERE id = ?");
            stmt.setString (1, funcionario.getNome());
            stmt.setString (2, funcionario.getRg());
            stmt.setString (3, funcionario.getCpf());
            stmt.setString (4, funcionario.getEndereco());
            stmt.setString (5, funcionario.getSexo());
            stmt.setString (6, funcionario.getCargo());
            stmt.setDouble (7, funcionario.getSalario());
            stmt.setString (8, funcionario.getNascimento());
            stmt.setString (9, funcionario.getAdmissao());
            stmt.setInt (10, funcionario.getId());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "*** Funcionario Alterado com Sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

            Conn.closeMinhaConexao(conn);

        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Erro ao Cadastrar Funcionario ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void remover(int id) {
        
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setInt (1, id);
            int cod = JOptionPane.showConfirmDialog(null, "*** Deseja mesmo excluir esse Funcionário? ***", "EXCLUIR", JOptionPane.YES_NO_OPTION);
            
            if (cod == JOptionPane.YES_OPTION)
            {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "*** Funcionário excluido com sucesso ***", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Erro ao remover o Funcionário ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getPorID(int id) {
        Funcionario funcionario = new Funcionario();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setNascimento(rs.getString("nascimento"));
                funcionario.setAdmissao(rs.getString("admissao"));
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consulta de Funcionários ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return funcionario;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> ListFuncionario = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setNascimento(rs.getString("nascimento"));
                funcionario.setAdmissao(rs.getString("admissao"));
                
                ListFuncionario.add(funcionario);
            }
            
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "*** Não foi possivel realizar a consuta de Funcionários ***", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return ListFuncionario;
    }
    
    @Override
    public ArrayList<Object> getAll(String filtro)
    {
        ArrayList<Object> ListFuncionario = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;

        try
        {
            stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, filtro + "%");
            rs = stmt.executeQuery();

            while (rs.next())
            {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setNascimento(rs.getString("nascimento"));
                funcionario.setAdmissao(rs.getString("admissao"));
                
                ListFuncionario.add(funcionario);
            }
            Conn.closeMinhaConexao(conn);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta de Funcionários", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return ListFuncionario;
    }
    
}
