package main;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.Fridge;
import by.koval.one.electric_appliance_exception.ElectricApplianceException;
import by.koval.one.electric_applience_manager.ElectricApplienceManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.concurrent.TimeUnit;

public class Main {
    //logger
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Starting program_____________________________");
        try {
            Fridge f1 = new Fridge("Fridge_1", 3000, 220);
            Fridge f2 = new Fridge("Fridge_2",200, 24);
            System.out.println(f1.toString());
            f1.turnOn();
            System.out.println(f1.toString());
            Apartment ap1 = new Apartment("Apartment_1");

            ap1.addApplience(f1);
            ap1.addApplience(f2);

            ElectricApplienceManager manager1 = new ElectricApplienceManager("Manager_1");
            manager1.addApartmentManager(ap1);
            System.out.println(manager1.calculateTotalPower());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
