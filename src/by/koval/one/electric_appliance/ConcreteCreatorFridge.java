package by.koval.one.electric_appliance;

public class ConcreteCreatorFridge extends Creator {

    @Override
    public ElectricApplience factoryMethod(String name, int power, int voltage) {
        return new Fridge(name, power, voltage);
    }
}

