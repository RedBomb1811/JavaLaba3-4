package main;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.ConcreteCreatorFridge;
import by.koval.one.electric_appliance.ElectricApplience;
import by.koval.one.electric_appliance.Fridge;
import by.koval.one.electric_appliance_exception.ElectricApplianceException;
import by.koval.one.electric_applience_manager.ElectricApplienceManager;
import by.koval.one.electric_applience_manager.ElectricApplienceManager.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;
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
            ConcreteCreatorFridge concreteCreatorFridge = new ConcreteCreatorFridge();
            ElectricApplience fridge1 = concreteCreatorFridge.factoryMethod("Fridge_1", 3000, 220);
            ElectricApplience fridge2 = concreteCreatorFridge.factoryMethod("Fridge_2",200, 24);
            ElectricApplience fridge3 = concreteCreatorFridge.factoryMethod("Fridge_2",600, 36);

            System.out.println(fridge1.toString());
            fridge1.turnOn();
            System.out.println(fridge1.toString());
            Apartment ap1 = new Apartment("Apartment_1");

            ap1.addApplience(fridge1);
            ap1.addApplience(fridge2);
            ap1.addApplience(fridge3);

            ElectricApplienceManager manager1 = new ElectricApplienceManager("Manager_1");
            manager1.addApartmentManager(ap1);
            System.out.println(manager1.calculateTotalPower());
            System.out.println(fridge1.toString());

            Thread.sleep(10000);

            manager1.sort(SortType.DESC);
            for (ElectricApplience a :
                    manager1.getAllApplience()) {
                System.out.printf(a.toString());
            }

            System.out.println("-----------");
            ArrayList<ElectricApplience> eee = manager1.getApplienceInRange(550, 55000);
            for (ElectricApplience elem :
                    eee) {
                System.out.println(elem);
            }

            //TODO класс светильник со внутренним классом лампочка(перечисления)
            //TODO консольное меню

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
