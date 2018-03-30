package by.koval.one.electric_appliance;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;

public class ConcreteCreatorFridge extends Creator {

    @Override
    public ElectricAppliance factoryMethod(String name, int power, int voltage) {
        return new Fridge(name, power, voltage);
    }

    @Override
    public ArrayList<ElectricAppliance> createListFromXml(String pathToFile) throws XMLStreamException {
        String name = null;
        int power = 0, voltage = 0;
        boolean b1 = false, b2 = false, b3 = false, b0 = false;
        XMLStreamReader reader = null;
        ArrayList<ElectricAppliance> list = null;
        try {
//            if (validateXMLSchema("Files\\fridges.xsd", pathToFile)) {
                list = new ArrayList<>();
                reader = XMLInputFactory.newInstance().createXMLStreamReader(pathToFile, new FileInputStream(pathToFile));
                int event = reader.getEventType();
                while (true) {
                    switch (event) {
                        case XMLStreamConstants.START_DOCUMENT:
                            break;
                        case XMLStreamConstants.START_ELEMENT:
                            if (!b0) b0 = reader.getName().toString().equals("electricAppliance");
                            b1 = reader.getName().toString().equals("name");
                            b2 = reader.getName().toString().equals("power");
                            b3 = reader.getName().toString().equals("voltage");
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            if (b1) name = reader.getText();
                            if (b2) power = Integer.parseInt(reader.getText());
                            if (b3) voltage = Integer.parseInt(reader.getText());
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            if (b0 && b3) {
                                list.add(new Fridge(name, power, voltage));
                                b0 = false;
                            }
                            if (b1) b1 = false;
                            if (b2) b2 = false;
                            if (b3) b3 = false;
                            break;
                        case XMLStreamConstants.END_DOCUMENT:
                            break;
                    }
                    if (!reader.hasNext())
                        break;
                    event = reader.next();
                }
//            }
//            else return null;
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return list;
    }

    @Override
    public boolean validateXMLSchema(String xsdPath, String xmlPath) throws Exception {
        try {
            // Получить фабрику для схемы
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Загрузить схему из XSD
            Schema schema = factory.newSchema(new File(xsdPath));
            // Создать валидатор (проверялбщик)
            Validator validator = schema.newValidator();
            // Запусить проверку - если будет исключение, значит есть ошибки.
            // Если нет - все заполнено правильно
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("+++++++++Exception: " + e.getMessage());
            return false;
        }
        xmlToHtml(xmlPath, xsdPath);
        return true;
    }

    @Override
    public void xmlToHtml(String xmlFile, String xslFile) throws Exception {
        // Открыть файлы в виде потоков
        InputStream xml = new FileInputStream(xmlFile);
        InputStream xsl = new FileInputStream(xslFile);
        // Сщоздать источник для транформации из потоков
        StreamSource xmlSource = new StreamSource(xml);
        StreamSource styleSource = new StreamSource(xsl);

        // Создать байтовый поток для результата
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // СОздать приемноик для результатат из байтового потока
        StreamResult xmlOutput = new StreamResult(bos);
        // Создать трансформатор и выполнить трансформацию
        Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
        transformer.transform(xmlSource, xmlOutput);

        File a = new File("Files\\fridges.html");
        a.createNewFile();
        FileWriter html = new FileWriter(a);
        html.write(bos.toString());
        }
}
