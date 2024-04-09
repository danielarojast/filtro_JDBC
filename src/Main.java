import controller.CoderController;
import controller.ContratacionController;
import controller.EmpresaController;
import controller.VacanteController;
import dataBase.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ConfigDB.openConnection();

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    opciones: 
                    1. Coder
                    2. Empresas
                    3. Vacante
                    4. Contratacion
                    5. Salir
                                        
                    Selecciona una opcion: 
                    """);

            switch (option) {
                case "1":
                    //Coder
                    String option1 = "";

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Crear coder
                                2. listar todos los coders
                                3. buscar por id
                                4. buscar por clan
                                5. editar informacion
                                6. eliminar
                                7. atras
                                                  
                                Selecciona una opcion: 
                                """);

                        switch (option1) {
                            case "1":
                                CoderController.create();
                                break;
                            case"2":
                                CoderController.getAll();
                                break;
                            case"3":
                                CoderController.findById();
                                break;
                            case"4":
                                CoderController.findByClan();
                                break;
                            case"5":
                                CoderController.update();
                                break;
                            case"6":
                                CoderController.delete();
                                break;
                        }

                    } while (!option1.equals("7"));

                    break;
                case "2":
                    //Empresa
                    EmpresaController.getAll();

                    break;
                case "3":
                    //Vacante
                    String option3 = "";

                    do {
                        option3 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Crear vacante
                                2. listar todos las vacantes
                                3. buscar por id
                                4. buscar por estado
                                5. editar informacion
                                6. eliminar
                                7. atras
                                                  
                                Selecciona una opcion: 
                                """);

                        switch (option3) {

                            case "1":
                                VacanteController.create();
                                break;
                            case "2":
                                VacanteController.getAll();
                                break;
                            case "3":
                                VacanteController.findById();
                                break;
                            case "4":
                                VacanteController.findByEstado();
                                break;
                            case "5":
                                VacanteController.update();
                                break;
                            case "6":
                                VacanteController.delete();
                                break;
                        }

                    } while (!option3.equals("7"));
                    break;
                case "4":
                    //Contratacion
                    String option4 = "";

                    do {
                        option4 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Crear contratacion
                                2. listar todas las contrataciones
                                3. buscar por id
                                4. editar informacion
                                5. eliminar
                                6. atras
                                                  
                                Selecciona una opcion: 
                                """);


                        switch (option4) {
                            case "1":
                                ContratacionController.create();
                                break;
                            case "2":
                                ContratacionController.getAll();
                                break;
                            case "3":
                                ContratacionController.findById();
                                break;
                            case "4":
                                ContratacionController.update();
                                break;
                            case "5":
                                ContratacionController.delete();
                                break;
                        }

                    } while (!option4.equals("6"));
                    break;
            }


        } while (!option.equals("5"));
    }
}