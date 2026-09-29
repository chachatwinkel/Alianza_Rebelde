package Clases;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EvaSamah
 */
@XmlRootElement(name = "Personaje")

public class Personaje implements Serializable{
    private String nombre;
    private String rango;

    public Personaje() {
    }
    
    public Personaje(String nombre, String rango) {
        this.nombre = nombre;
        this.rango = rango;
    }

    
    
    /**
     * @return the nombre
     */
    @XmlElement(name = "nombre")

    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the rango
     */
    @XmlElement(name = "rango")
    public String getRango() {
        return rango;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(String rango) {
        this.rango = rango;
    }
    
    
}
