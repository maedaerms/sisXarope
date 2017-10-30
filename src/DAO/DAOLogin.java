/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuarios;
import java.util.ArrayList;

/**
 *
 * @author maeda
 */
public interface DAOLogin {
    
     public Usuarios logar(String usuario, String senha);
     public void cadastrar (Object o);
     public ArrayList<Object> getAll();    
     public ArrayList<Object> getAll(String filtro);
}
