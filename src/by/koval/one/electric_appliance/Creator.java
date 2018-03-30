package by.koval.one.electric_appliance;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public abstract class Creator {
    public abstract ElectricAppliance factoryMethod(String name, int power, int voltage);
    public abstract ArrayList<ElectricAppliance> createListFromXml(String pathToFile) throws XMLStreamException;
    public abstract boolean validateXMLSchema(String xsdPath, String xmlPath) throws Exception;
    public abstract void xmlToHtml(String xmlFile, String xslFile) throws Exception;
}
