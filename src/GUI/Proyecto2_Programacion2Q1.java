 package GUI;

import static resources.PropertiesFileCreator.createFile;

public class Proyecto2_Programacion2Q1 {

    public static void main(String[] args) {
        createFile();
        PgInicial pgInicial = new PgInicial();
        pgInicial.frame.setVisible(true);
    }
    
}