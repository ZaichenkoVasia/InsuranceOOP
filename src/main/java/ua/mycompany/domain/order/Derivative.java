package ua.mycompany.domain.order;

import ua.mycompany.exception.ActionDerivativeWithNullRuntimeException;
import ua.mycompany.exception.RangeUncorrectedRuntimeException;

import java.util.ArrayList;
import java.util.Collections;

public class Derivative {
    private ArrayList<Insurance> insurances;

    public Derivative() {
        insurances = new ArrayList<>();
    }

    public Derivative(ArrayList<Insurance> insurances) {
        this.insurances = insurances;
    }

    public ArrayList<Insurance> getInsurances() {
        return insurances;
    }

    public void add(Insurance insurance){
        if(insurance == null){
            throw new ActionDerivativeWithNullRuntimeException("Add null to derivative");
        }
        insurances.add(insurance);
    }

    public void remove(Insurance insurance){
        if(insurance == null){
            throw new ActionDerivativeWithNullRuntimeException("Remove null from derivative");
        }
        insurances.remove(insurance);
    }

    public ArrayList<Insurance> sortByRisk(){
        Collections.sort(insurances);
        return insurances;
    }

    public double sumOfInsurance() {
        double sum = 0;
        for (Insurance element : insurances) {
            sum += element.getPrice();
        }
        return sum;
    }

    public ArrayList<Insurance> searchElementRisk(double startRange, double endRange) {
        if(startRange>endRange || startRange < 0){
            throw new RangeUncorrectedRuntimeException("Uncorrected range for search in derivative");
        }
        ArrayList<Insurance> searchElementRisks = new ArrayList<>();
        for (Insurance element: insurances) {
            if(element.getRisk()>startRange && element.getRisk()<endRange){
                searchElementRisks.add(element);
            }
        }
        return searchElementRisks;
    }

    public ArrayList<Insurance> searchElementPrice(double startRange, double endRange) {
        if(startRange>endRange || startRange < 0){
            throw new RangeUncorrectedRuntimeException("Uncorrected range for search in derivative");
        }
        ArrayList<Insurance> searchElementPrice = new ArrayList<>();
        for (Insurance element: insurances) {
            if(element.getPrice()>startRange && element.getPrice()<endRange){
                searchElementPrice.add(element);
            }
        }
        return searchElementPrice;
    }

    public ArrayList<Insurance> searchElementPayment(double startRange, double endRange) {
        if(startRange>endRange || startRange < 0){
            throw new RangeUncorrectedRuntimeException("Uncorrected range for search in derivative");
        }
        ArrayList<Insurance> searchElementPayment = new ArrayList<>();
        for (Insurance element: insurances) {
            if(element.getPayment()>startRange && element.getPayment()<endRange){
                searchElementPayment.add(element);
            }
        }
        return searchElementPayment;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Derivative{ insurances= ");
        for (Insurance insurance:insurances) {
            result.append(insurance.toString());
        }
        result.append('}');
        return result.toString();
    }
}
