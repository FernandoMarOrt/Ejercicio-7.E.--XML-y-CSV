/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7efernando;

import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author fer
 */
public class GenerarFicheros {

    public static void main(String[] args) throws JAXBException {

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

        //Genero el fichero xml a partir de la lista de facturas
//        generarFichero("./xml/facturas.xml", listaFacturas);
        generarFicheroXML(listaFacturas, "Fichero con las facturas xml", "./xml/facturas.xml");
        
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
    
    
    public static void generarFicheroXML(List<Factura> listAux, String descripcion,String ruta) throws JAXBException {

        // Se preparan los objetos a utilizar, en esta caso un catálogo
        CatalogoFacturas catalogo = new CatalogoFacturas();
        catalogo.setLista((ArrayList<Factura>) listAux);
        catalogo.setDescripcion(descripcion);

        // Crea el contexto JAXB. Se encarga de definir los objetos 
        // que vamos a guardar. En nuestro caso sólo el tipo CatalogoMuebles
        JAXBContext contexto = JAXBContext.newInstance(CatalogoFacturas.class);

        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        // El proceso de pasar objetos Java (CatalogoMuebles) a ficheros XML 
        // se conoce como "marshalling" o "serialización"
        Marshaller serializador = contexto.createMarshaller();

        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización
        // Serialización y salida por consola
        serializador.marshal(catalogo, System.out);

        // Volcado al fichero xml
        serializador.marshal(catalogo, new File(ruta));

    }

}
