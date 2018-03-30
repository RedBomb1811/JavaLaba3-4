package by.koval.one.electric_appliance;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.out;

public class Fridge extends ElectricAppliance {
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
                "Name=" + name +
                ", power=" + power +
                ", state=" + state +
                ", voltage=" + voltage +
                ", switchOnApplience=" + String.format("%02d:%02d:%02d", time / 1000 / 3600, time / 1000 / 60 % 60, time / 1000 % 60) +
                "}";
    }
}

