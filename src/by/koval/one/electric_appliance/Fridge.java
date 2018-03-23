package by.koval.one.electric_appliance;
import java.util.Comparator;
import by.koval.one.electric_appliance_exception.*;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fridge extends ElectricApplience{
    private static final Logger LOG = Logger.getLogger(Fridge.class);

    public Fridge(String name, int power, int voltage) {
        super(name, power, voltage);
    }

    @Override
    public void turnOn() {
        this.switchOnApplience = new Date();
        this.state = true;
        LOG.info("The " + this.getName() + " was turned on!");

    }

    @Override
    public void turnOff() {
        this.state = false;
        LOG.info("The " + this.getName() + " was turned off!");
    }

    public long workingHours(){
        if(this.state)
            return new Date().getTime() - this.switchOnApplience.getTime();
        else return 0;
    }

    @Override
    public String toString() {
        long time = workingHours();
        return "Fridge{" +
                "power=" + power +
                ", state=" + state +
                ", voltage=" + voltage +
                ", switchOnApplience=" + String.format("%02d:%02d:%02d", time / 1000 / 3600, time / 1000 / 60 % 60, time / 1000 % 60) +
                "}";
    }
}

