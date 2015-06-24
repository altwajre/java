package com.company.app;
import org.apache.commons.lang3.SerializationUtils;
import java.io.Serializable;
public class App
{
    static class ProspectMemory{  // Caretaker
        public Memento Memento;
    }
    static class Memento{  // Memento
        private byte[] data;
        public Memento serialize(Object object){
            data = SerializationUtils.serialize((Serializable) object);
            return this;
        }
        public Object deserialize(){ return SerializationUtils.deserialize(data); }
    }
    static class SalesProspect implements Serializable{
        private String name;
        private String phone;
        private double budget;
        public String getName() { return name; }
        public void setName(String name) {
            this.name = name;
            System.out.println("Name: " + name);
        }
        public String getPhone() { return phone; }
        public void setPhone(String phone) {
            this.phone = phone;
            System.out.println("Phone: " + phone);
        }
        public double getBudget() { return budget; }
        public void setBudget(double budget) {
            this.budget = budget;
            System.out.println("Budget: " + budget);
        }
        // Stores (serializes) memento
        public Memento saveMemento(){
            System.out.println("\nSaving state --\n");
            Memento memento = new Memento();
            return memento.serialize(this);
        }
        // Restores (deserializes) memento
        public void restoreMemento(Memento memento){
            System.out.println("\nRestoring state --\n");
            SalesProspect salesProspect = (SalesProspect) memento.deserialize();
            this.name = salesProspect.getName();
            this.phone = salesProspect.getPhone();
            this.budget = salesProspect.getBudget();
        }
    }
    public static void main( String[] args )
    {
        SalesProspect salesProspect = new SalesProspect();
        salesProspect.setName("Joel van Halen");
        salesProspect.setPhone("(412) 256-0990");
        salesProspect.setBudget(25000.0);
        // Store internal state
        ProspectMemory prospectMemory = new ProspectMemory();
        prospectMemory.Memento = salesProspect.saveMemento();
        // Change originator
        salesProspect.setName("New name");
        salesProspect.setPhone("New phone number");
        salesProspect.setBudget(1.0);
        // Restore saved state
        salesProspect.restoreMemento(prospectMemory.Memento);
        System.out.println(salesProspect.getName());
        System.out.println(salesProspect.getPhone());
        System.out.println(salesProspect.getBudget());
    }
}
