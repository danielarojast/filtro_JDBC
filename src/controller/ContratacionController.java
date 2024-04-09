package controller;

import entity.Contratacion;
import entity.Vacante;
import model.CoderModel;
import model.ContratacionModel;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class ContratacionController {
    public static void create(){
        ContratacionModel objContratacionModel = new ContratacionModel();

        //Variable estado
        String option= " ";
        do {
            option = JOptionPane.showInputDialog("""
                Estado de la contratacion
                1. activo
                2. inactivo
                """);

            if(!option.equals("1") && !option.equals("2")){
                JOptionPane.showMessageDialog(null, "La opcion que ingreso no es correcta");
            }
        }while(!option.equals("1") && !option.equals("2"));

        String estado = " ";
        switch (option){
            case"1":
                estado = "activa";
                break;
            case"2":
                estado = "inactiva";
                break;
        }

        //Pedir el resto de las variables
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario: "))  ;
        //String listVacantesAct= VacanteController.findByEstado("activa");
        int idVacante= Integer.parseInt(JOptionPane.showInputDialog(VacanteController.getAllString() + "Seleccione el id de la vacante: ")) ;
        int idCoder = Integer.parseInt(JOptionPane.showInputDialog(CoderController.getAllString() + "Sleccione el id del coder: "));


        //Validar si si esta el id de la vacante
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel= new CoderModel();

        //Validar que el id de la empresa si exista
        if(objVacanteModel.findById(idVacante) == null){
                JOptionPane.showMessageDialog(null, "La empresa no existe, no se puede contratar sin vacante.");
        }else {
            if(objCoderModel.findById(idCoder) == null){
                JOptionPane.showMessageDialog(null, "El coder no se encontro");
            }else{
                Contratacion objContratacion = new Contratacion();
                objContratacion.setEstado(estado);
                objContratacion.setSalario(salario);
                objContratacion.setIdVacante(idVacante);
                objContratacion.setIdCoder(idCoder);

                objContratacion = (Contratacion) objContratacionModel.insert(objContratacion);

            }

        }
    }

    public static void getAll(){
        ContratacionModel objContratacionModel= new  ContratacionModel();

        String listContratacion= "Lista de  contrataciones: "+ "\n";
        for(Object iterador: objContratacionModel.findAll()){
            Contratacion objContratacion = (Contratacion) iterador;
            listContratacion += objContratacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listContratacion);
    }

    public static String getAllString(){
        ContratacionModel objContratacionModel = new ContratacionModel();

        String listContratacion = "Lista de contrataciones\n";

        for (Object iterator : objContratacionModel.findAll()){
            Contratacion objContratacion = (Contratacion) iterator;
            listContratacion += objContratacion.toString() + "\n";
        }
        return listContratacion;
    }

    public static void findById() {
        ContratacionModel objContratacionModel= new ContratacionModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a buscar: ")) ;

        Contratacion objContratacion= objContratacionModel.findById(id);

        if(objContratacion == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objContratacion.toString());
        }
    }

    public static void delete() {
        ContratacionModel objContratacionModel= new ContratacionModel();
        String listContratacion= getAllString();
        int id= Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "Ingrese el ID a eliminar: ")) ;

        Contratacion objContratacion= objContratacionModel.findById(id);

        if(objContratacion == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            int confirm2= JOptionPane.showConfirmDialog(null, "Esta seguro que lo desea eliminar? "+ objContratacion.toString()) ;
            if(confirm2 == 0){
                objContratacionModel.delete(objContratacion);
            }
        }
    }

    public static void update() {
        ContratacionModel objContratacionModel= new ContratacionModel();
        String listContratacion= getAllString();
        int id= Integer.parseInt(JOptionPane.showInputDialog(listContratacion + "Ingrese el ID a editar: ")) ;

        Contratacion objContratacion= objContratacionModel.findById(id);

        if(objContratacion == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else {
            String option = " ";
            do {
                option = JOptionPane.showInputDialog(null, """
                        Estado de la contratacion
                        1. activo
                        2. inactivo
                        """, objContratacion.getEstado());

                if (!option.equals("1") && !option.equals("2")) {
                    JOptionPane.showMessageDialog(null, "La opcion que ingreso no es correcta");
                }
            } while (!option.equals("1") && !option.equals("2"));

            String estado = " ";
            switch (option) {
                case "1":
                    estado = "activa";
                    break;
                case "2":
                    estado = "inactiva";
                    break;
            }

            //Pedir el resto de las variables
            double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el salario: ", objContratacion.getSalario()));
            //String listVacantesAct= VacanteController.findByEstado("activa");
            int idVacante = Integer.parseInt(JOptionPane.showInputDialog(null, VacanteController.getAllString() + "Seleccione el id de la vacante: ", objContratacion.getIdVacante()));
            int idCoder = Integer.parseInt(JOptionPane.showInputDialog(null, CoderController.getAllString() + "Seleccione el id del coder: ", objContratacion.getIdCoder()));

            EmpresaModel objEmpresaModel = new EmpresaModel();

            //Validar si si esta el id de la vacante
            VacanteModel objVacanteModel = new VacanteModel();
            CoderModel objCoderModel = new CoderModel();

            //Validar que el id de la empresa si exista
            if (objVacanteModel.findById(idVacante) == null) {
                JOptionPane.showMessageDialog(null, "La empresa no existe, no se puede contratar sin vacante.");
            } else {
                if (objCoderModel.findById(idCoder) == null) {
                    JOptionPane.showMessageDialog(null, "El coder no se encontro");
                } else {

                    objContratacion.setEstado(estado);
                    objContratacion.setSalario(salario);
                    objContratacion.setIdVacante(idVacante);
                    objContratacion.setIdCoder(idCoder);

                    objContratacionModel.update(objContratacion);
                }
            }
        }
    }
}
