/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author maeda
 */
public interface DAO {
    
    public void salvar(Object o);
    public void atualizar(Object o);
    public void remover(int id);
    public Object getPorID(int id);
    public ArrayList<Object> getAll();    
    public ArrayList<Object> getAll(String filtro);
}
