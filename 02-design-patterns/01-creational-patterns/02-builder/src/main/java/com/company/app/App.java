package com.company.app;
import java.util.HashMap;
import static java.lang.System.out;
public class App
{
    public enum PartType{ Frame, Engine, Wheel, Door }
    public enum VehicleType{ Car, Scooter, MotorCycle }
    static class Vehicle{  // Product
        private VehicleType _vehicleType;
        private HashMap<PartType, String> _parts = new HashMap<PartType, String>();
        public Vehicle(VehicleType vehicleType){ _vehicleType = vehicleType; }
        public String getPart(PartType partType){ return _parts.get(partType); }
        public void setPart(PartType partType, String value){ _parts.put(partType, value); }
        public void Show(){
            out.println("\n--------------------------");
            out.format("Vehicle Type: %s\n", _vehicleType);
            out.format(" Frame  : %s\n", getPart(PartType.Frame));
            out.format(" Engine  : %s\n", getPart(PartType.Engine));
            out.format(" Wheels  : %s\n", getPart(PartType.Wheel));
            out.format(" Doors  : %s\n", getPart(PartType.Door));
        }
    }
    static abstract class VehicleBuilder{  // abstract Builder
        private Vehicle vehicle;
        public Vehicle getVehicle() { return vehicle; }
        public VehicleBuilder(VehicleType vehicleType){ vehicle = new Vehicle(vehicleType); }
        public abstract void BuildFrame();
        public abstract void BuildEngine();
        public abstract void BuildWheels();
        public abstract void BuildDoors();
    }
    static class MoterCycleBuilder extends VehicleBuilder{  // ConcreteBuilder_1
        public MoterCycleBuilder() { super(VehicleType.MotorCycle); }
        @Override
        public void BuildFrame() { getVehicle().setPart(PartType.Frame, "MotorCycle Frame"); }
        @Override
        public void BuildEngine() { getVehicle().setPart(PartType.Engine, "500 cc"); }
        @Override
        public void BuildWheels() { getVehicle().setPart(PartType.Wheel, "2"); }
        @Override
        public void BuildDoors() { getVehicle().setPart(PartType.Door, "0"); }
    }
    static class CarBuilder extends VehicleBuilder{  // ConcreteBuilder_2
        public CarBuilder() { super(VehicleType.Car); }
        @Override
        public void BuildFrame() { getVehicle().setPart(PartType.Frame, "Car Frame"); }
        @Override
        public void BuildEngine() { getVehicle().setPart(PartType.Engine, "2500 cc"); }
        @Override
        public void BuildWheels() { getVehicle().setPart(PartType.Wheel, "4"); }
        @Override
        public void BuildDoors() { getVehicle().setPart(PartType.Door, "4"); }
    }
    static class ScooterBuilder extends VehicleBuilder{  // ConcreteBuilder_3
        public ScooterBuilder() { super(VehicleType.Scooter); }
        @Override
        public void BuildFrame() { getVehicle().setPart(PartType.Frame, "Scooter Frame"); }
        @Override
        public void BuildEngine() { getVehicle().setPart(PartType.Engine, "50 cc"); }
        @Override
        public void BuildWheels() { getVehicle().setPart(PartType.Wheel, "2"); }
        @Override
        public void BuildDoors() { getVehicle().setPart(PartType.Door, "0"); }
    }
    static class Shop{
        private VehicleBuilder _vehicleBuilder;
        public void Construct(VehicleBuilder vehicleBuilder){
            _vehicleBuilder = vehicleBuilder;
            _vehicleBuilder.BuildFrame();;
            _vehicleBuilder.BuildEngine();
            _vehicleBuilder.BuildWheels();
            _vehicleBuilder.BuildDoors();
        }
        public void ShowVehicle(){ _vehicleBuilder.getVehicle().Show(); }
    }
    public static void main( String[] args )
    {
        Shop shop = new Shop();
        shop.Construct(new ScooterBuilder());
        shop.ShowVehicle();
        shop.Construct(new CarBuilder());
        shop.ShowVehicle();
        shop.Construct(new MoterCycleBuilder());
        shop.ShowVehicle();
    }
}
