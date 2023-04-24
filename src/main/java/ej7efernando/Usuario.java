/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7efernando;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fer
 */
public class Usuario {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        /*Pregunta al usuario que factura desea leer de las posibles que haya en “./facturascsv”.
        Para ello muestra un listado con las que haya en la carpeta y luego pregunta la factura a leer.*/
        
        listarDirectorio("./facturascsv");
        
        
        System.out.println("Dime que factura quieres leer de las anteriores: ");
        String facturaLeer = teclado.nextLine();

        /*Una vez leída la factura desde el fichero y creado el objeto factura con los datos correspondientes, muestra por pantalla.*/
        
        Factura f = crearFacturaArchivo(facturaLeer);
        
        System.out.println(f.toString());
    }

    public static void listarDirectorio(String ruta) {

        File f = new File(ruta);
        if (f.exists()) {
            // Obtiene los ficheros y directorios dentro de f y los 
            // devuelve en un array
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }
    }

    public static Factura crearFacturaArchivo(String nomFichero) {

        // Fichero a leer con datos de ejemplo
        String idFichero = "./facturascsv/" + nomFichero;

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        Factura f = null;

        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();

                tokens = linea.split(";");

                // Crear un objeto DateTimeFormatter con el patrón de fecha deseado
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Parsear el String a un LocalDate utilizando el DateTimeFormatter
                LocalDate fechaLocalDate = LocalDate.parse(tokens[1], formatter);

                f = new Factura(tokens[0], fechaLocalDate, tokens[2], Double.parseDouble(tokens[3]));

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return f;
    }

}
