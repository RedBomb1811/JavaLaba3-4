package main;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.Fridge;
import by.koval.one.electric_appliance_exception.ElectricApplianceException;
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
            Fridge f1 = new Fridge(3000, 220);
            Fridge f2 = new Fridge(200, 24);
            System.out.println(f1.toString());
            f1.turnOn();
            System.out.println(f1.toString());
            Apartment ap1 = new Apartment();

            ap1.listOfElectricApplience.add(f1);
            ap1.listOfElectricApplience.add(f2);

            for (Object obj :
                    ap1.listOfElectricApplience) {
                System.out.println(obj.toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
