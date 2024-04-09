package controller;

import entity.Vacante;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class VacanteController {
    public static void create(){
        VacanteModel objVacanteModel = new VacanteModel();

        String titulo = JOptionPane.showInputDialog("Titulo de la vacante: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese descripcion: ");
        String duracion = JOptionPane.showInputDialog("Ingrese duracion de la vacante: ");
        String tecnologia = JOptionPane.showInputDialog("Tecnologia: ");
        String idEmpresaOption = JOptionPane.showInputDialog(EmpresaController.getAllString() + "Ingrese el ID de la empresa: ");

        //ciclo para asegurarme que pongan una opcion correcta
        String option= " ";
        do {
            option = JOptionPane.showInputDialog("""
                 Estado de la vacante
                 1. Activa
                 2. Inactivas
                 Seleccione una opcion: 
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

        //esta validacion es por si le da cancelar antes de llenar los datos no aparezca error
        if(titulo != null && descripcion != null && duracion!= null && estado != null && tecnologia != null && idEmpresaOption != null){
            int idEmpresa = Integer.parseInt(idEmpresaOption) ;
            EmpresaModel objEmpresaModel = new EmpresaModel();

            //Validar si si esta el id de la empresa

            //Validar que el id de la empresa si exista
            if(objEmpresaModel.findById(idEmpresa) == null){
                JOptionPane.showMessageDialog(null, "La empresa no existe, no se puede crear vacante sin empresa.");
            }else{

                Vacante objVacante = new Vacante();
                objVacante.setTitulo(titulo);
                objVacante.setDescripcion(descripcion);
                objVacante.setDuracion(duracion);
                objVacante.setEstado(estado);
                objVacante.setTecnologia(tecnologia);
                objVacante.setIdEmpresa(idEmpresa);

                objVacante = (Vacante) objVacanteModel.insert(objVacante);

            }
        }
    }

    public static void getAll(){
        VacanteModel objVacanteModel= new  VacanteModel();

        String listVacante= "Lista de  Vacantes: "+ "\n";
        for(Object iterador: objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) iterador;
            listVacante += objVacante.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listVacante);
    }

    public static String getAllString(){
        VacanteModel objVacanteModel = new VacanteModel();

        String listVacante = "Lista de vacantes\n";

        for (Object iterator : objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) iterator;
            listVacante += objVacante.toString() + "\n";
        }
        return listVacante;
    }

    public static void findById() {
        VacanteModel objVacanteModel= new VacanteModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a buscar: ")) ;

        Vacante objVacante= objVacanteModel.findById(id);

        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objVacante.toString());
        }
    }

    public static void delete() {
        VacanteModel objVacanteModel= new VacanteModel();
        String listVacante= getAllString();
        int id= Integer.parseInt(JOptionPane.showInputDialog(listVacante + "Ingrese el ID a eliminar: ")) ;

        Vacante objVacante= objVacanteModel.findById(id);

        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            int confirm2= JOptionPane.showConfirmDialog(null, "Esta seguro que lo desea eliminar? "+ objVacante.toString()) ;
            if(confirm2 == 0){
                objVacanteModel.delete(objVacante);
            }
        }
    }

    public static void update() {
        VacanteModel objVacanteModel= new VacanteModel();
        String listVacante= getAllString();
        int id= Integer.parseInt(JOptionPane.showInputDialog(listVacante + "Ingrese el ID a editar: ")) ;

        Vacante objVacante= objVacanteModel.findById(id);

        if(objVacante == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            String titulo = JOptionPane.showInputDialog(null, "Titulo de la vacante: ", objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog(null,"Ingrese descripcion: ", objVacante.getDescripcion());
            String duracion = JOptionPane.showInputDialog(null,"Ingrese duracion de la vacante: ",objVacante.getDuracion());
            String tecnologia = JOptionPane.showInputDialog(null,"Tecnologia: ", objVacante.getTecnologia());
            String idEmpresaOption = JOptionPane.showInputDialog(null,"Ingrese el ID de la empresa: ", objVacante.getIdEmpresa());
            String option = "";

            do {
                option = JOptionPane.showInputDialog(null, """
                 Estado de la vacante
                 1. Activa
                 2. Inactivas
                 Seleccione una opcion: 
                 """, objVacante.getEstado());
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

            //esta validacion es por si le da cancelar antes de llenar los datos no aparezca error
            if(titulo != null && descripcion != null && duracion!= null && estado != null && tecnologia != null && idEmpresaOption != null) {
                int idEmpresa = Integer.parseInt(idEmpresaOption);
                EmpresaModel objEmpresaModel = new EmpresaModel();

                //Validar que el id de la empresa si exista
                if (objEmpresaModel.findById(idEmpresa) == null) {
                    JOptionPane.showMessageDialog(null, "La empresa no existe, no se puede crear vacante sin empresa.");
                } else {

                    objVacante.setTitulo(titulo);
                    objVacante.setDescripcion(descripcion);
                    objVacante.setDuracion(duracion);
                    objVacante.setEstado(estado);
                    objVacante.setTecnologia(tecnologia);
                    objVacante.setIdEmpresa(idEmpresa);

                    //objVacante = (Vacante) objVacanteModel.insert(objVacante);

                    objVacanteModel.update(objVacante);
                }
            }
        }
    }

    public static void findByEstado() {
        VacanteModel objVacanteModel = new VacanteModel();
        String option= " ";

                //ciclo para asegurarme que pongan una opcion correcta
        do {
            option = JOptionPane.showInputDialog("""
                 1. vacantes Activas
                 2. vacantes inectivas
                 Seleccione una opcion: 
                 """);
            if(!option.equals("1") && !option.equals("2")){
                JOptionPane.showMessageDialog(null, "La opcion que ingreso no es correcta");
            }
        }while(!option.equals("1") && !option.equals("2"));

        if(option.equals("1")){
            String estado= "activa";
            String listaString = "CONICIDENCIAS: \n";
            Vacante iterador;
            for(Iterator var = objVacanteModel.findByEstado(estado).iterator(); var.hasNext(); listaString = listaString + iterador.toString() + "\n") {
                iterador = (Vacante) var.next();
            }
            JOptionPane.showMessageDialog((Component) null, listaString);
        }else{
            String estado= "inactiva";
            String listaString = "CONICIDENCIAS: \n";
            Vacante iterador;
            for(Iterator var = objVacanteModel.findByEstado(estado).iterator(); var.hasNext(); listaString = listaString + iterador.toString() + "\n") {
                iterador = (Vacante) var.next();
            }
            JOptionPane.showMessageDialog((Component) null, listaString);
        }
    }
}
