/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7efernando;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.DoubleStream;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author fer
 */
// Anotación @XmlRootElement, nombre de la etiqueta XML raíz.
@XmlRootElement(name = "factura")
// Anotación @XmlAccesorType define el elemento que usará JAXB durante el 
// procesamiento de datos (en este caso por atributo)
@XmlAccessorType(XmlAccessType.FIELD)

public class Factura {

    private static int contadorInstancias = 0;
    private String codigoUnico;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaEmision;
    private String descripcion;
    private double totalImporteFactura;

    // Constructor por defecto
    public Factura() {
        contadorInstancias++;
        codigoUnico = "Factura" + contadorInstancias;
        fechaEmision = LocalDate.now();
        descripcion = RandomStringUtils.randomAlphabetic(10);
        totalImporteFactura = generarImporteAleatorio();
    }

    // Constructor parametrizado
    public Factura(String codigoUnico, LocalDate fechaEmision, String descripcion, double totalImporteFactura) {
        contadorInstancias++;
        this.codigoUnico = codigoUnico;
        this.fechaEmision = fechaEmision;
        this.descripcion = descripcion;
        this.totalImporteFactura = totalImporteFactura;
    }



    // Genera un importe aleatorio entre 100€ y 1000€
    private double generarImporteAleatorio() {
        Random random = new Random();
        DoubleStream importeStream = random.doubles(1, 100, 1001);
        return importeStream.findFirst().getAsDouble();
    }

    // Getters y setters
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotalImporteFactura() {
        return totalImporteFactura;
    }

    public void setTotalImporteFactura(double totalImporteFactura) {
        this.totalImporteFactura = totalImporteFactura;
    }

    public static int getContadorInstancias() {
        return contadorInstancias;
    }

    public static void setContadorInstancias(int contadorInstancias) {
        Factura.contadorInstancias = contadorInstancias;
    }

    @Override
    public String toString() {
        return codigoUnico + ";" + fechaEmision + ";" + descripcion + ";" + totalImporteFactura + ";";
    }
    
    
    
    
}


