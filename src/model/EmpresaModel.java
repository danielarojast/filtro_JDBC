package model;

import dataBase.ConfigDB;
import entity.Coder;
import entity.Empresa;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {

    //Se crea para lisatar en las opciones de vacantes
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listEmpresa= new ArrayList<>();

        try{
            String sql= "SELECT * FROM empresa;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                //6.1 Crear una empresa
                Empresa objEmpresa= new Empresa();

                //6.2 Llenar el objeto con la informacion de la base de datos
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setIdEmpresa(objResult.getInt("idEmpresa"));

                //6.3 Agregamos el coder a la lista
                listEmpresa.add(objEmpresa);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listEmpresa;
    }

    // se crea para validar que en al ingresar el id en vacantes la empresa si exista
    public Empresa findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = null;

        try{
            String sql= "SELECT * FROM empresa WHERE idEmpresa = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                objEmpresa = new Empresa();

                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setIdEmpresa(objResult.getInt("idEmpresa"));
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objEmpresa;
    }
}
