package by.koval.one.electric_applience_manager;

import by.koval.one.apartment.Apartment;
import by.koval.one.electric_appliance.ElectricAppliance;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface ManagerInterface{
    void addApartmentManager(Apartment param1);
    ArrayList<ElectricAppliance> getAllAppliance();
}

public class ElectricApplianceManager implements ManagerInterface, Serializable {

    public static ElectricApplianceManager instance;

    private ElectricApplianceManager(String name) {
        this.name = name;
    }

    public static ElectricApplianceManager getInstance(String name){
        if(instance == null)
            instance = new ElectricApplianceManager(name);
        return instance;
    }

    public enum SortType{
        DESC,
        NONE,
        ESC
    }

    private static final Logger LOG = Logger.getLogger(ElectricApplianceManager.class);

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

    @Override
    public void addApartmentManager(Apartment param1) {
        LOG.info("The " + param1.getName() + " was added for the " + this.getName());
        this.apartment = param1;
    }

    @Override
    public ArrayList<ElectricAppliance> getAllAppliance() {
        return this.apartment.listOfElectricAppliance;
    }

    public int calculateTotalPower(){
        LOG.info("Calculated total power!");
        int sumPower = 0;
        for (Object obj :
                this.apartment.listOfElectricAppliance) {
            sumPower += ((ElectricAppliance) obj).getPower();
        }
        return sumPower;
    }

    public void sort(SortType type){
        LOG.info("Sorting!");
        int k = type.ordinal()-1;
        Collections.sort(apartment.getListOfElectricAppliance(), new Comparator<ElectricAppliance>() {
            public int compare(ElectricAppliance o1, ElectricAppliance o2) {
                if(o1.getPower() > o2.getPower())
                    return 1*k;
                if(o1.getPower() < o2.getPower())
                    return -1*k;
                return 0*k;
            }
        });
    }

    public ArrayList<ElectricAppliance> getApplienceInRange(int min, int max){
        LOG.info("Get applience in the range!");
        ArrayList<ElectricAppliance> ret = new ArrayList<>();
        for (ElectricAppliance elem :
                this.getAllAppliance()) {
            if (elem.getPower() >= min && elem.getPower() <= max)
                ret.add(elem);
        }
        return ret;
    }

}
