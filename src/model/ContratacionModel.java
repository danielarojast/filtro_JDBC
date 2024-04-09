package model;

import dataBase.ConfigDB;
import entity.Coder;
import entity.Contratacion;
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

public class ContratacionModel {
    public Object insert(Object obj) {

        //abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try{
            String sql= "INSERT INTO contratacion(estado, salario, idVacante, idCoder) VALUES (?,?,?,?) ;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objContratacion.getEstado());
            objPrepare.setDouble(2, objContratacion.getSalario());
            objPrepare.setInt(3,objContratacion.getIdVacante());
            objPrepare.setInt(4,objContratacion.getIdCoder());

            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objContratacion.setIdVacante(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "La contratacion se agrego correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            //cerrar conexion
            ConfigDB.closeConnection();
        }
        return objContratacion;
    }

    //@Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listContratacion= new ArrayList<>();

        try{
            String sql= "SELECT * FROM contratacion INNER JOIN vacante ON contratacion.idVacante = vacante.idVacante" +
                    " INNER JOIN coder ON contratacion.idCoder = coder.idCoder;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                // Crearlosobjetos
                Contratacion objContratacion= new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();

                objContratacion.setIdContratacion(objResult.getInt("contratacion.IdContratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));
                objContratacion.setIdVacante(objResult.getInt("contratacion.idVacante"));
                objContratacion.setIdCoder(objResult.getInt("contratacion.idCoder"));

                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setIdEmpresa(objResult.getInt("vacante.idEmpresa"));
                objVacante.setIdVacante(objResult.getInt("vacante.idVacante"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellido(objResult.getString("coder.apellido"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));
                objCoder.setIdCoder(objResult.getInt("coder.idCoder"));


                //Agregar los datos de la la vacante y al coder a la contratacion
                objContratacion.setVacante(objVacante);
                objContratacion.setCoder(objCoder);

                // Agregamos ls contratacion a la lista
                listContratacion.add(objContratacion);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listContratacion;
    }

    //@Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        boolean idUpdate= false;

        try{
            String sql= "UPDATE contratacion SET estado = ?, salario = ?, idVacante = ?, idCoder = ? WHERE idContratacion = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            objPrepare.setString(1,objContratacion.getEstado());
            objPrepare.setDouble(2,objContratacion.getSalario());
            objPrepare.setInt(3,objContratacion.getIdVacante());
            objPrepare.setInt(4,objContratacion.getIdCoder());
            objPrepare.setInt(5,objContratacion.getIdContratacion());


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
        Contratacion objContratacion = (Contratacion) obj;

        boolean idDelete= false;

        try{
            String sql= "DELETE FROM contratacion WHERE idContratacion = ?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objContratacion.getIdVacante());

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

    public Contratacion findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;

        try{
            String sql= "SELECT * FROM contratacion INNER JOIN vacante ON contratacion.idVacante = vacante.IdVacante" +
                    " INNER JOIN coder ON contratacion.idCoder = coder.idCoder WHERE contratacion.idContratacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                objContratacion= new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();

                objContratacion.setIdContratacion(objResult.getInt("contratacion.IdContratacion"));
                objContratacion.setFecha_aplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));
                objContratacion.setIdVacante(objResult.getInt("contratacion.idVacante"));
                objContratacion.setIdCoder(objResult.getInt("contratacion.idCoder"));

                objVacante.setTitulo(objResult.getString("Vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setIdEmpresa(objResult.getInt("vacante.idEmpresa"));
                objVacante.setIdVacante(objResult.getInt("vacante.idVacante"));
                objVacante.setEstado(objResult.getString("vacante.estado"));

                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellido(objResult.getString("coder.apellido"));
                objCoder.setDocumento(objResult.getString("coder.documento"));
                objCoder.setCohorte(objResult.getInt("coder.cohorte"));
                objCoder.setCv(objResult.getString("coder.cv"));
                objCoder.setClan(objResult.getString("coder.clan"));
                objCoder.setIdCoder(objResult.getInt("coder.idCoder"));

                objContratacion.setVacante(objVacante);
                objContratacion.setCoder(objCoder);
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objContratacion;
    }
}
