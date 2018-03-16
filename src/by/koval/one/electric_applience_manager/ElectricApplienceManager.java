package by.koval.one.electric_applience_manager;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.ElectricApplience;
import main.Main;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface ManagerInterface{
    void addApartmentManager(Apartment param1);

    ArrayList<? super ElectricApplience> getAllApplience();
}

public class ElectricApplienceManager implements ManagerInterface {
    private static final Logger LOG = Logger.getLogger(ElectricApplienceManager.class);

    public Apartment apartment;
    public String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Apartment getApartment() {
        return apartment;
    }
    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public ElectricApplienceManager(String name) {
        this.name = name;
    }

    @Override
    public void addApartmentManager(Apartment param1) {
        LOG.info("The " + getApartment().getName() + " was added for the " + this.getName());
        this.apartment = param1;
    }

    @Override
    public ArrayList<? super ElectricApplience> getAllApplience() {
        return this.apartment.listOfElectricApplience;
    }

    public int calculateTotalPower(){
        int sumPower = 0;
        for (Object obj :
                this.apartment.listOfElectricApplience) {
            sumPower += ((ElectricApplience) obj).getPower();
        }
        return sumPower;
    }

    public void sort(){
    }

}
