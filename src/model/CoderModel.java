package model;

import dataBase.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel {
    //@Override
    public Object insert(Object obj) {

        //abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder= (Coder) obj;

        try{
            String sql= "INSERT INTO coder(nombre, apellido, documento, cohorte, cv, clan) VALUES (?,?,?,?,?,?) ;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellido());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());

            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objCoder.setIdCoder(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "el coder se agrego correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            //cerrar conexion
            ConfigDB.closeConnection();
        }
        return objCoder;
    }

    //@Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listCoder= new ArrayList<>();

        try{
            String sql= "SELECT * FROM coder;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                //6.1 Crear un coder
                Coder objCoder= new Coder();

                //6.2 Llenar el objetocon la informacion de la base de datos
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setIdCoder(objResult.getInt("idCoder"));

                //6.3 Agregamos el coder a la lista
                listCoder.add(objCoder);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }

    //@Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        boolean idUpdate= false;

        try{
            String sql= "UPDATE coder SET nombre = ?, apellido = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE idCoder = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellido());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());
            objPrepare.setInt(7, objCoder.getIdCoder());


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
        Coder objCoder = (Coder) obj;

        boolean idDelete= false;

        try{
            String sql= "DELETE FROM coder WHERE idCoder = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCoder.getIdCoder());

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

    public Coder findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try{
            String sql= "SELECT * FROM coder WHERE idCoder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setIdCoder(objResult.getInt("idCoder"));
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objCoder;
    }

    public ArrayList<Coder> findByClan(String clan) {
        ArrayList<Coder> listCoder = new ArrayList();
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try {
            String sql = "SELECT * FROM coder WHERE clan LIKE ? ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%" + clan + "%");
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setIdCoder(objResult.getInt("idCoder"));

                listCoder.add(objCoder);
            }
        } catch (Exception var8) {
            JOptionPane.showMessageDialog((Component)null, var8.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listCoder;
    }

    //Se hace con el fin de hacer la validacion al crear el coder
    public Coder findByDocumento(String documento){
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try{
            String sql= "SELECT * FROM coder WHERE documento LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, documento);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                objCoder = new Coder();

                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
                objCoder.setIdCoder(objResult.getInt("idCoder"));
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objCoder;
    }

}
