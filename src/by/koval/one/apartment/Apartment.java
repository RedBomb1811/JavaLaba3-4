package by.koval.one.apartment;

import by.koval.one.electric_appliance.ElectricAppliance;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;

public class Apartment implements Serializable{
    private static final Logger LOG = Logger.getLogger(Apartment.class);

    String name;
    public ArrayList<ElectricAppliance> listOfElectricAppliance;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ElectricAppliance> getListOfElectricAppliance() {
        return listOfElectricAppliance;
    }
    public void setListOfElectricAppliance(ArrayList<ElectricAppliance> listOfElectricAppliance) {
        this.listOfElectricAppliance = listOfElectricAppliance;
    }

    public Apartment(String apart1) {
        name = apart1;
        listOfElectricAppliance = new ArrayList<ElectricAppliance>();
    }

    public boolean addApplience(ElectricAppliance obj) {
        boolean ret = this.listOfElectricAppliance.add(obj);
        if (ret)
            LOG.info("The " + obj.getName() + " was added to the " + this.getName());
        else
            LOG.info("The " + obj.getName() + " wasn't added to the " + this.getName());
        return ret;

    }

    public int countAllApplience() {
        return listOfElectricAppliance.size();
    }
}

