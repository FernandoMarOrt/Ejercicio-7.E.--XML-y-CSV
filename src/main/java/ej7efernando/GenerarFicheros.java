/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7efernando;

import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fer
 */
public class GenerarFicheros {

    public static void main(String[] args) {

        List<Factura> listaFacturas = new ArrayList<>();

        //Creo las 50 facturas y las meto en una lista
        for (int i = 0; i < 50; i++) {

            listaFacturas.add(new Factura());

        }

        //Imprimo la lista de facturas
        listaFacturas.forEach(System.out::println);

        //Creo las carpetas csv y xml 
        crearDirectorio("csv");
        crearDirectorio("xml");

        //Genero el fichero csv a partir de la lista de facturas
        generarFichero("./csv/facturas.csv", listaFacturas);

        //Genero el fichero csv a partir de la lista de facturas
        generarFichero("./xml/facturas.xml", listaFacturas);
        
        crearDirectorio("facturascsv");
        
        //Genero los 50 ficheros respectivamente nombrados como el codigo unico de la factura
        generarFicheros("./facturascsv/", listaFacturas);

    }

    public static void crearDirectorio(String rutaDirectorio) {

        Path directorio = Paths.get(rutaDirectorio);

        if (!Files.exists(directorio)) { // Verificar si el directorio no existe
            try {
                Files.createDirectory(directorio); // Intentar crear el directorio
                System.out.println("Directorio creado exitosamente.");
            } catch (IOException e) {
                System.err.println("Error al crear el directorio: " + e.getMessage());
            }
        } else {
            System.out.println("El directorio ya existe.");
        }
    }

    public static void generarFichero(String nomFichero, List<Factura> listaFacturas) {
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = nomFichero;
        String tmp;
        //
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {

            for (Factura f : listaFacturas) {
                tmp = f.getCodigoUnico() + ";" + f.getFechaEmision() + ";" + f.getDescripcion() + ";" + f.getTotalImporteFactura();
                flujo.write(tmp);
                flujo.newLine();
            }
            flujo.flush();
            System.out.println("Fichero " + nomFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generarFicheros(String ruta ,List<Factura> listaFacturas) {
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto

        for (Factura f : listaFacturas) {

            try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(ruta +f.getCodigoUnico()))) {
                String tmp;
                tmp = f.getCodigoUnico() + ";" + f.getFechaEmision() + ";" + f.getDescripcion() + ";" + f.getTotalImporteFactura();
                flujo.write(tmp);
                flujo.flush();
                System.out.println("Fichero " + f.getCodigoUnico() + " creado correctamente.");
                
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
