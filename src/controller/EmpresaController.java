package controller;

import entity.Coder;
import entity.Empresa;
import model.CoderModel;
import model.EmpresaModel;

import javax.swing.*;

public class EmpresaController {
    public static void getAll(){
        EmpresaModel objEmpresaModel= new EmpresaModel();

        String listCoder= "LISTA DE EMPRESAS: "+ "\n";
        for(Object iterador: objEmpresaModel.findAll()){
            Empresa objEmpresa= (Empresa) iterador;
            listCoder += objEmpresa.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCoder);
    }

    public static String getAllString(){
        EmpresaModel objEmpresaModel = new EmpresaModel();

        String listEmpresa = "Lista de empresas\n";

        for (Object iterator : objEmpresaModel.findAll()){
            Empresa objEmpresa = (Empresa) iterator;
            listEmpresa += objEmpresa.toString() + "\n";
        }
        return listEmpresa;
    }
}
