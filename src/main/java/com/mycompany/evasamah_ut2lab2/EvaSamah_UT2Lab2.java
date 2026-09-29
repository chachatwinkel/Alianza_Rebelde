package com.mycompany.evasamah_ut2lab2;

import Clases.Personaje;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author EvaSamah
 */
public class EvaSamah_UT2Lab2 {

    public static void main(String[] args) {

        paso2();
        paso3();
        paso4();
    }
    
    public static void paso2(){
    
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse(new File("imperio.xml"));
            doc.getDocumentElement().normalize();
            System.out.println("Elemento raiz: " + doc.getDocumentElement().getNodeName());
        
            NodeList listaNaves = doc.getElementsByTagName("nave");
            for (int i = 0; i < listaNaves.getLength(); i++) {
                Node nodo = listaNaves.item(i);
                
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                
                    String id = elemento.getAttribute("id");
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String piloto = elemento.getElementsByTagName("piloto").item(0).getTextContent();
                    System.out.println("ID: " + id + " | Nombre: " + nombre + " | Piloto: " + piloto);
                } 
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("===============================================================================================");

    }
    
    public static void paso3(){
    
         try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
             DefaultHandler manejador = new DefaultHandler() {
                private boolean esNombre = false;
                private String  clase = "";
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("nave")) {
                        clase = attributes.getValue("clase");
                    } else if (qName.equalsIgnoreCase("nombre")) {
                        esNombre = true;
                    } 
                }
                
                 @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (esNombre) {
                        String nombre = new String(ch, start, length); 
                        System.out.println("Clase: " +clase+ "|| Nombre: " + nombre);
                    }
                    esNombre= false;
                }
            };
               saxParser.parse(new File("imperio.xml"), manejador); 
               
         }catch(IOException e){
             System.err.println("Error: " + e.getMessage());
             
         } catch (SAXException ex) {
            Logger.getLogger(EvaSamah_UT2Lab2.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EvaSamah_UT2Lab2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("===============================================================================================");

    }
    
    public static void paso4() {
        
        File fichero = new File("rebelde.xml");
        
           try {
                JAXBContext contexto = JAXBContext.newInstance(Personaje.class);
                Marshaller marshaller = contexto.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                
                
                Personaje personaje = new Personaje (
                    "Luke Skywalker",
                    "Comandante"
                );
                
                marshaller.marshal(personaje, fichero);
                System.out.println("Archivo 'rebelde.xml' generado correctamente mediante Binding");

                System.out.println("--- Contenido generado ---");
                marshaller.marshal(personaje, System.out);
            
           } catch (Exception e) {
               System.out.println("Error: " +e.getMessage());
           }
    }
}
