/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7efernando;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author fer
 */
public class LeerXML {

    public static void main(String[] args) throws JAXBException,
            FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoFacturas.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoFacturas catalogo = (CatalogoFacturas) um.unmarshal(new File("./xml/facturas.xml"));

        ArrayList<Factura> listaFacturas = catalogo.getListaFactura();

        listaFacturas.forEach(System.out::println);

    }

}
