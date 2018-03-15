package by.koval.one.electric_appliance;

import java.util.Date;

public abstract class ElectricApplience {
    int power;
    boolean state;
    int voltage;
    Date switchOnApplience;

    public ElectricApplience(int power, int voltage) {
        this.power = power;
        this.state = false;
        this.voltage = voltage;
        this.switchOnApplience = null;
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
