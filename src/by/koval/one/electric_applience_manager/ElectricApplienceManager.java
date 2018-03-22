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
    ArrayList<ElectricApplience> getAllApplience();
}

public class ElectricApplienceManager implements ManagerInterface {

    public enum SortType{
        DESC,
        NONE,
        ESC
    }

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
        LOG.info("The " + param1.getName() + " was added for the " + this.getName());
        this.apartment = param1;
    }

    @Override
    public ArrayList<ElectricApplience> getAllApplience() {
        return this.apartment.listOfElectricApplience;
    }

    public int calculateTotalPower(){
        LOG.info("Calculated total power!");
        int sumPower = 0;
        for (Object obj :
                this.apartment.listOfElectricApplience) {
            sumPower += ((ElectricApplience) obj).getPower();
        }
        return sumPower;
    }

    public void sort(SortType type){
        LOG.info("Sorting!");
        int k = type.ordinal()-1;
        Collections.sort(apartment.getListOfElectricApplience(), new Comparator<ElectricApplience>() {
            public int compare(ElectricApplience o1, ElectricApplience o2) {
                if(o1.getPower() > o2.getPower())
                    return 1*k;
                if(o1.getPower() < o2.getPower())
                    return -1*k;
                return 0*k;
            }
        });
    }

    public ArrayList<ElectricApplience> getApplienceInRange(int min, int max){
        LOG.info("Get applience in the range!");
        ArrayList<ElectricApplience> ret = new ArrayList<>();
        for (ElectricApplience elem :
                this.getAllApplience()) {
            if (elem.getPower() >= min && elem.getPower() <= max)
                ret.add(elem);
        }
        return ret;
    }

}
