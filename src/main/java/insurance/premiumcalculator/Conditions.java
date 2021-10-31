package insurance.premiumcalculator;

import org.springframework.stereotype.Service;

@Service
public class Conditions {
    public Integer NumberOfRisks = 2;
    public String[] RiskTypes = { "Fire", "Theft" };
    public Double[] ThresholdSums = { 100.0, 15.0 };
    public Double[] CoeffBelowThres = { 0.014, 0.11 };
    public Double[] CoeffAboveThres = { 0.024, 0.05 };

    public Conditions() {
    }
}
