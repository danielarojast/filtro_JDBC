package model;

import dataBase.ConfigDB;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel {
    public Object insert(Object obj) {

        //abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        try{
            String sql= "INSERT INTO vacante(titulo, descripcion, duracion, estado, tecnologia, idEmpresa) VALUES (?,?,?,?,?,?) ;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setString(5,objVacante.getTecnologia());
            objPrepare.setInt(6,objVacante.getIdEmpresa());

            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objVacante.setIdVacante(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "La vacante se agrego correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            //cerrar conexion
            ConfigDB.closeConnection();
        }
        return objVacante;
    }

    //@Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listVacante= new ArrayList<>();

        try{
            String sql= "SELECT * FROM vacante INNER JOIN empresa ON vacante.idEmpresa = empresa.IdEmpresa;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                //6.1 Crear una vacante
                Vacante objVacante= new Vacante();
                Empresa objEmpresa = new Empresa();

                //6.2 Llenar el objetocon la informacion de la base de datos
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setIdEmpresa(objResult.getInt("vacante.idEmpresa"));
                objVacante.setIdVacante(objResult.getInt("vacante.idVacante"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objEmpresa.setIdEmpresa(objResult.getInt("empresa.idEmpresa"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                //Agregar los datos de la empresa al objeto vacante
                objVacante.setEmpresa(objEmpresa);

                //6.3 Agregamos el coder a la lista
                listVacante.add(objVacante);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }

    //@Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        boolean idUpdate= false;

        try{
            String sql= "UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ?, idEmpresa = ? WHERE idVacante = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setString(5,objVacante.getTecnologia());
            objPrepare.setInt(6,objVacante.getIdEmpresa());
            objPrepare.setInt(7, objVacante.getIdVacante());


            int totalAfectedRows= objPrepare.executeUpdate();
            if(totalAfectedRows > 0){
                idUpdate = true;
                JOptionPane.showMessageDialog((Component) null, "Actualizacion exitosa.");
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return idUpdate;
    }

    //@Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        boolean idDelete= false;

        try{
            String sql= "DELETE FROM vacante WHERE idVacante = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objVacante.getIdVacante());

            int totalAfectedRows= objPrepare.executeUpdate();
            if(totalAfectedRows > 0){
                idDelete = true;
                JOptionPane.showMessageDialog((Component) null, "Se elimino exitosamente.");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return idDelete;
    }

    public Vacante findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;

        try{
            String sql= "SELECT * FROM vacante INNER JOIN empresa ON vacante.idEmpresa = empresa.idEmpresa WHERE idVacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                objVacante= new Vacante();
                Empresa objEmpresa = new Empresa();

                //6.2 Llenar el objetocon la informacion de la base de datos
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setIdEmpresa(objResult.getInt("vacante.idEmpresa"));
                objVacante.setIdVacante(objResult.getInt("vacante.idVacante"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objEmpresa.setIdEmpresa(objResult.getInt("empresa.idEmpresa"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setEmpresa(objEmpresa);
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objVacante;
    }

    public ArrayList<Vacante> findByEstado(String estado) {
        ArrayList<Vacante> listVacante = new ArrayList();
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;

        try {
            String sql= "SELECT * FROM vacante INNER JOIN empresa ON vacante.idEmpresa = empresa.idEmpresa WHERE idVacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, estado);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objVacante= new Vacante();
                Empresa objEmpresa = new Empresa();

                //6.2 Llenar el objetocon la informacion de la base de datos
                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setIdEmpresa(objResult.getInt("vacante.idEmpresa"));
                objVacante.setIdVacante(objResult.getInt("vacante.idVacante"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objEmpresa.setIdEmpresa(objResult.getInt("empresa.idEmpresa"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                objVacante.setEmpresa(objEmpresa);

                listVacante.add(objVacante);
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }
}
