package by.koval.one.electric_appliance;

import by.koval.one.electric_appliance_exception.ElectricApplianceException;

import java.util.Comparator;
import java.util.Date;

public abstract class ElectricApplience{
    String name;
    int power;
    boolean state;
    int voltage;
    Date switchOnApplience;

    public ElectricApplience(String name, int power, int voltage) {
        this.name = name;
        this.power = power;
        this.state = false;
        this.voltage = voltage;
        this.switchOnApplience = null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = Math.abs(power);
    }

    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }

    public int getVoltage() {
        return voltage;
    }
    public void setVoltage(int voltage) {
        this.voltage = Math.abs(voltage);
    }

    public Date getSwitchOnApplience() {
        return switchOnApplience;
    }
    public void setSwitchOnApplience(Date switchOnApplience) {
        this.switchOnApplience = switchOnApplience;
    }

    abstract public void turnOn();
    abstract public void turnOff();
}
