package gof.behavioral.state.java;

interface PatientHealthState {

  void supplyOxygen();

  void supplyOralFood();

  void recommendWalking();

  void prescribeGeneralMedicine();

  void admitInICU();
}

class StablePatientState implements PatientHealthState {

  private TreatmentContext context;

  public StablePatientState(TreatmentContext context) {
    this.context = context;
  }

  @Override
  public void supplyOxygen() {
    System.out.println("Stable patient might become unstable if oxygen is forced fed. ");
    context.setState(context.unStablePatientState);
  }

  @Override
  public void supplyOralFood() {
    System.out.println(" Oral food will help faster recovery for Stable Patients. ");
    context.setState(context.stablePatientState);
  }

  @Override
  public void recommendWalking() {
    System.out.println(" Stable Patients should walk for about 1 mile each day. ");
    context.setState(context.stablePatientState);
  }

  @Override
  public void prescribeGeneralMedicine() {
    System.out.println(" will help in improvement ");
    context.setState(context.stablePatientState);
  }

  @Override
  public void admitInICU() {
    System.out.println(" Stable patient might become unstable if forced admit in ICU  ");
    context.setState(context.unStablePatientState);
  }
}

class UnStablePatientState implements PatientHealthState {

  private TreatmentContext context;

  public UnStablePatientState(TreatmentContext context) {
    this.context = context;
  }

  @Override
  public void supplyOxygen() {
    System.out.println("UnStable patient do need oxygen, will help in getting stable ");
    context.setState(context.stablePatientState);
  }

  @Override
  public void supplyOralFood() {
    System.out.println(" supply blood and liquids, will help in getting stable ");
    context.setState(context.stablePatientState);
  }

  @Override
  public void recommendWalking() {
    System.out.println("Not needed until patient becomes stable ");
    context.setState(context.unStablePatientState);
  }

  @Override
  public void prescribeGeneralMedicine() {
    System.out.println(" needs advance medicine ");
  }

  @Override
  public void admitInICU() {
    System.out.println(" Intensive care is needed, will help in getting stable  ");
    context.setState(context.stablePatientState);
  }
}

class TreatmentContext {

  PatientHealthState state;
  StablePatientState stablePatientState = new StablePatientState(this);
  UnStablePatientState unStablePatientState = new UnStablePatientState(this);


  public PatientHealthState getState() {
    return state;
  }

  public void setState(PatientHealthState state) {
    this.state = state;
  }


  void prescribeGeneralMedicine() {
    state.prescribeGeneralMedicine();
  }

  void admitInICU() {
    state.admitInICU();
  }

  void supplyOxygen() {
    state.supplyOxygen();
  }

  void supplyNormalFood() {
    state.supplyOralFood();
  }

  void recommendWalking() {
    state.recommendWalking();
  }

}

public class Base {
  public static void main(String[] args) {
    TreatmentContext context = new TreatmentContext();
    context.setState(context.unStablePatientState);
    context.admitInICU();

  }
}
/*
 Intensive care is needed, will help in getting stable
 */
