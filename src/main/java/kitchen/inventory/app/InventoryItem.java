package kitchen.inventory.app;

public class InventoryItem implements Countable{

    protected String name;
    protected double numberOfUnits;
    protected UnitOfVolume unit;



    public InventoryItem(String name, double numberOfUnits, UnitOfVolume unit){
        this.name = name;
        this.numberOfUnits = numberOfUnits;
        this.unit = unit;
    }
    public InventoryItem(String name, double numberOfUnits){
        this.name = name;
        this.numberOfUnits = numberOfUnits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfUnits(double numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public void setUnit(UnitOfVolume unit) {
        this.unit = unit;
    }

    @Override
    public String getUnitOfVolume() {
        return unit.getType();
    }

    @Override
    public double getNumberOfUnits() {
        return numberOfUnits;
    }
}
