package by.koval.one.apartment;

import by.koval.one.electric_appliance.ElectricApplience;
import by.koval.one.electric_appliance.Fridge;
import org.apache.log4j.Logger;

import java.util.*;

public class Apartment {
    private static final Logger LOG = Logger.getLogger(Apartment.class);

    String name;
    public ArrayList<? super ElectricApplience> listOfElectricApplience;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<? super ElectricApplience> getListOfElectricApplience() {
        return listOfElectricApplience;
    }
    public void setListOfElectricApplience(ArrayList<? super ElectricApplience> listOfElectricApplience) {
        this.listOfElectricApplience = listOfElectricApplience;
    }


    public Apartment(String apart1) {
        name = apart1;
        listOfElectricApplience = new ArrayList<>();
    }

    public <T1 extends ElectricApplience> boolean addApplience(T1 obj) {
        boolean ret = this.listOfElectricApplience.add(obj);
        if (ret)
            LOG.info("The " + obj.getName() + " was added to the " + this.getName());
        else
            LOG.info("The " + obj.getName() + " wasn't added to the " + this.getName());
        return ret;

    }

    public int countAllApplience() {
        return listOfElectricApplience.size();
    }
}

