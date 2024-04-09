package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CoderController {
    public static void create(){
        CoderModel objCoderModel= new CoderModel();

        String nombre= JOptionPane.showInputDialog("Nombre: ");
        String apellido= JOptionPane.showInputDialog("Apellido: ");
        String documento= JOptionPane.showInputDialog("Documento de identidad");
        String cohorteOption= JOptionPane.showInputDialog("Cohorte: ");
        String clan= JOptionPane.showInputDialog("Clan: ");
        String cv= JOptionPane.showInputDialog("CV: ");

        int cohorte= Integer.parseInt(cohorteOption);

        //validacion paraque si le doy cancelar sin ingresar datos no me parezca error si no que me envie atras
        if(nombre != null && apellido != null && documento != null && cohorteOption != null && cv != null && clan != null ){

            //Validacion para que no se repita el documento
            if(objCoderModel.findByDocumento(documento) == null){
                Coder objCoder= new Coder();

                objCoder.setNombre(nombre);
                objCoder.setApellido(apellido);
                objCoder.setDocumento(documento);
                objCoder.setCohorte(cohorte);
                objCoder.setCv(cv);
                objCoder.setClan(clan);

                objCoder= (Coder) objCoderModel.insert(objCoder);
                JOptionPane.showMessageDialog(null, objCoder.toString());
            }else {
                JOptionPane.showMessageDialog(null, "Ese documento ya se encontra registrado.");
            }
        }
    }

    public static void getAll(){
        CoderModel objCoderModel= new CoderModel();

        String listCoder= "Lista de Coders: "+ "\n";
        for(Object iterador: objCoderModel.findAll()){
            Coder objCoder= (Coder) iterador;
            listCoder += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCoder);
    }

    public static String getAllString(){
        CoderModel objCoderModel = new CoderModel();

        String listCoder = "Lista de coders\n";

        for (Object iterator : objCoderModel.findAll()){
            Coder objCoder = (Coder) iterator;
            listCoder += objCoder.toString() + "\n";
        }
        return listCoder;
    }

    public static void findById() {
        CoderModel objCoderModel= new CoderModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a buscar: ")) ;

        Coder objCoder= objCoderModel.findById(id);

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objCoder.toString());
        }
    }

    public static void delete() {
        CoderModel objCoderModel= new CoderModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a eliminar: ")) ;
        String listCoder= getAllString();
        Coder objCoder= objCoderModel.findById(id);

        if(objCoder == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            int confirm2= JOptionPane.showConfirmDialog(null, "Esta seguro que lo desea eliminar? "+ objCoder.toString()) ;
            if(confirm2 == 0){
                objCoderModel.delete(objCoder);
            }
        }
    }

    public static void update() {
        CoderModel objCoderModel = new CoderModel();
        String listCoder = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(listCoder + "Ingrese el ID a editar: "));

        Coder objCoder = objCoderModel.findById(id);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "No se encontro id");
        } else {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre: ", objCoder.getNombre());
            String apellido = JOptionPane.showInputDialog(null, "Apellido: ", objCoder.getApellido());
            String documento = JOptionPane.showInputDialog(null, "Documento de identidad", objCoder.getDocumento());
            String cohorteOption = JOptionPane.showInputDialog(null, "Cohorte: ", objCoder.getCohorte());
            String clan = JOptionPane.showInputDialog(null, "Clan: ", objCoder.getClan());
            String cv = JOptionPane.showInputDialog(null, "CV: ", objCoder.getCv());

            int cohorte = Integer.parseInt(cohorteOption);

            if (nombre != null && apellido != null && documento != null && cohorteOption != null && cv != null && clan != null) {
                objCoder.setNombre(nombre);
                objCoder.setApellido(apellido);
                objCoder.setDocumento(documento);
                objCoder.setCohorte(cohorte);
                objCoder.setCv(cv);
                objCoder.setClan(clan);

                objCoderModel.update(objCoder);
            }
        }
    }

    public static void findByClan() {
        CoderModel objCoderModel = new CoderModel();
        String clan = JOptionPane.showInputDialog("\n Inserte el nombre del clan que busca: ");
        String listaString = "CONICIDENCIAS: \n";

        Coder iterador;
        for(Iterator var = objCoderModel.findByClan(clan).iterator(); var.hasNext(); listaString = listaString + iterador.toString() + "\n") {
            iterador = (Coder) var.next();
        }

        JOptionPane.showMessageDialog((Component) null, listaString);
    }
}
