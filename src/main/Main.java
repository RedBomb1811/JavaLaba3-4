package main;

import by.koval.one.electric_appliance.Fridge;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.concurrent.TimeUnit;

public class Main {
    //logger
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Starting program_____________________________");
        Fridge f1 = new Fridge(3000, 220);
        System.out.println(f1.toString());
        f1.turnOn();
        System.out.println(f1.toString());
    }
}
