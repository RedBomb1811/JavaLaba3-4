package main;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.ConcreteCreatorFridge;
import by.koval.one.electric_appliance.ElectricAppliance;
import by.koval.one.electric_appliance.Fridge;
import by.koval.one.electric_applience_manager.ElectricApplianceManager;
import by.koval.one.electric_applience_manager.ElectricApplianceManager.*;
import by.koval.one.serializator.Serializator;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.InvalidObjectException;
import java.util.Scanner;

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
            ElectricAppliance fridge1 = concreteCreatorFridge.factoryMethod("Fridge_1", 3000, 220);
            ElectricAppliance fridge2 = concreteCreatorFridge.factoryMethod("Fridge_2", 200, 24);
            ElectricAppliance fridge3 = concreteCreatorFridge.factoryMethod("Fridge_2", 600, 36);

            Gson gson = new Gson();
            String jsonStr = gson.toJson(fridge1);
            fridge1 = null;
            fridge1 = gson.fromJson(jsonStr, Fridge.class);
            LOG.info(fridge1.getName() + " get from JSON!");

            Apartment ap1 = new Apartment("Apartment_1");

            if(concreteCreatorFridge.createListFromXml("Files\\fridges.xml") != null) {
                ap1.addApplience(concreteCreatorFridge.createListFromXml("Files\\fridges.xml").get(0));
                ap1.addApplience(concreteCreatorFridge.createListFromXml("Files\\fridges.xml").get(1));
            }

            ap1.addApplience(fridge1);
            ap1.addApplience(fridge2);
            ap1.addApplience(fridge3);

            ElectricApplianceManager manager1 = ElectricApplianceManager.getInstance("Manager_1");
            manager1.addApartmentManager(ap1);
            //--------------------------------------------------------

            Serializator sz = new Serializator();
            boolean b = sz.serialization(manager1, "Files\\ser.txt");
// BankCard.bankName = "SSS"; // изменение значения static-поля
// чтение и вывод объекта
            ElectricApplianceManager res = null;
            try {
                res = sz.deserialization("Files\\ser.txt");
            } catch (InvalidObjectException e) {
// обработка
                e.printStackTrace();
            }

            manager1 = null;
            manager1 = res;

            //--------------------------------------------------------
                while (true) {
                    System.out.println(
                            "-------------------------------------------\n" +
                                    "1.\tПосчитать общую мощность\n" +
                                    "2.\tОтсортировать список устройств\n" +
                                    "3.\tНайти элементы в диапазоне мощности\n" +
                                    "4.\tВывод списка элементов\n" +
                                    "0. Выход\n" +
                                    "-------------------------------------------\n");
                    if (!in.hasNextInt()) {
                        System.out.println("Not correct!");
                        in.nextLine();
                    } else {
                        switch (in.nextInt()) {
                        case 1:
                            System.out.println("Общая мощность: " + manager1.calculateTotalPower());
                            break;
                        case 2:
                            System.out.println("По убыванию:\t0\nПо\tвозрастанию: 1");
                            if (!in.hasNextInt()) {
                                System.out.println("Not correct!");
                                in.nextLine();
                            }else {
                                if (in.nextInt() == 0)
                                    manager1.sort(SortType.DESC);
                                else
                                    manager1.sort(SortType.ESC);
                                System.out.println("Отсортирован!");
                            }
                            break;
                        case 3:
                            System.out.println("Min:");
                            int min, max;
                            if (!in.hasNextInt()) {
                                System.out.println("Not correct!");
                                in.nextLine();
                            }else {
                                min = in.nextInt();

                                System.out.println("Max:");
                                if (!in.hasNextInt()) {
                                    System.out.println("Not correct!");
                                    in.nextLine();
                                } else {
                                    max = in.nextInt();

                                    System.out.println("-------------------------------------------");
                                    for (ElectricAppliance elem :
                                            manager1.getApplienceInRange(min, max)) {
                                        System.out.println(elem.toString());
                                    }
                                }
                            }
                            break;
                        case 4:
                            for (ElectricAppliance elem :
                                    manager1.getAllAppliance()) {
                                System.out.println(elem.toString());
                            }
                            break;
                        case 0:
                            System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
