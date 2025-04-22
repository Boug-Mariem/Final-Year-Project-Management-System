package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import interfacePFE.MyConnection;
import projetPFE.PFEException;

public abstract class  DAO <T>{
	public  Connection connbase;
	
	public  DAO () {
		try {
			connbase=MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	public abstract T create(T objet)throws PFEException;
	public abstract T update(T obj)throws PFEException;
	public abstract boolean delete(T obj)throws PFEException;
	public abstract T find(T obj)throws PFEException;
	public abstract List<T> findAll() throws PFEException; 
}
