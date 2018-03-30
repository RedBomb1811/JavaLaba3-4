package by.koval.one.serializator;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_applience_manager.ElectricApplianceManager;
import org.apache.log4j.Logger;

import java.io.*;
public class Serializator {
    private static final Logger LOG = Logger.getLogger(Serializator.class);

    public boolean serialization(ElectricApplianceManager card, String fileName) {
        boolean flag = false;
        File f = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            if (fos != null) {
                ObjectOutputStream oStream = new ObjectOutputStream(fos);
                oStream.writeObject(card); // сериализация
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("The object was serialized!");
        return flag;
    }

    public ElectricApplianceManager deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        try ( FileInputStream fis = new FileInputStream(fr)){
            ObjectInputStream istream = new ObjectInputStream(fis);
            ElectricApplianceManager manager = (ElectricApplianceManager) istream.readObject();
            LOG.info("The object was deserialized");
            return manager;
        }
        catch ( IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new InvalidObjectException
                ("объект не восстановлен");
    }
}
