package insurance.premiumcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumCalculator {
    public Double Premium;
    public Double[] SumsInsured = new Double[2];
    public Double[] Coefficients = new Double[2];
    private final Conditions conditions;

    @Autowired
    public PremiumCalculator(Conditions conditions) {
        this.conditions = conditions;
    }

    Double CalculatePremium(Policy policy) {
        SumsInsured = CalculateInsuredSums(policy);
        Premium = Calculation(SumsInsured);
        return Premium;
    }

    Double Calculation(Double[] SumsInsured) {
        Coefficients = CalculateCoefficients(SumsInsured);
        Premium = 0.0;
        for (int i = 0; i < conditions.NumberOfRisks; i++) {
            Premium = Premium + SumsInsured[i] * Coefficients[i];
        }
        Premium = Premium * 100;
        var rounded = Math.round(Premium);
        Premium = rounded / 100.0;
        return Premium;
    }

    Double[] CalculateInsuredSums(Policy policy) {
        policy.Objects.forEach((obj) -> {
            obj.SubObjects.forEach((sobj) -> {
                if (sobj.RiskTypes.contains(conditions.RiskTypes[0]))
                    SumsInsured[0] = SumsInsured[0] + sobj.Sum;
                if (sobj.RiskTypes.contains(conditions.RiskTypes[1]))
                    SumsInsured[1] = SumsInsured[1] + sobj.Sum;
            });
        });

        return SumsInsured;
    }

    Double[] CalculateCoefficients(Double[] SumsInsured) {
        for (int i = 0; i < conditions.NumberOfRisks; i++) {
            if (conditions.RiskTypes[i] == "Fire")
                Coefficients[i] = (SumsInsured[i] > conditions.ThresholdSums[i]) ? conditions.CoeffAboveThres[i]
                        : conditions.CoeffBelowThres[i];
            if (conditions.RiskTypes[i] == "Theft")
                Coefficients[i] = (SumsInsured[i] >= conditions.ThresholdSums[i]) ? conditions.CoeffAboveThres[i]
                        : conditions.CoeffBelowThres[i];
        }

        return Coefficients;
    }
}
