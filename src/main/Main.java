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
import java.util.Scanner;
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
            Scanner in = new Scanner(System.in);
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

            ElectricApplienceManager manager1 = ElectricApplienceManager.getInstance("Manager_1");
            manager1.addApartmentManager(ap1);
            while (true){
                System.out.println(
                                "-------------------------------------------\n"+
                                "1.\tПосчитать общую мощность\n"+
                                "2.\tОтсортировать список устройств\n"+
                                "3.\tНайти элементы в диапазоне мощности\n"+
                                "4.\tВывод списка элементов\n"+
                                "0. Выход\n"+
                                "-------------------------------------------\n");
                switch (in.nextInt()){
                    case 1:
                        System.out.println("Общая мощность: " + manager1.calculateTotalPower());
                        break;
                    case 2:
                        System.out.println("По убыванию:\t0\nПо\tвозрастанию: 1");
                        if (in.nextInt()==0)
                            manager1.sort(SortType.DESC);
                        else
                            manager1.sort(SortType.ESC);
                        System.out.println("Отсортирован!");
                        break;
                    case 3:
                        System.out.println("Min:");
                        int min = in.nextInt();
                        System.out.println("Max:");
                        int max = in.nextInt();
                        System.out.println("-------------------------------------------");
                        for (ElectricApplience elem :
                                manager1.getApplienceInRange(min, max)) {
                            System.out.println(elem.toString());
                        }
                        break;
                    case 4:
                        for (ElectricApplience elem :
                                manager1.getAllApplience()) {
                            System.out.println(elem.toString());
                        }
                        break;
                    case 0: System.exit(0);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
