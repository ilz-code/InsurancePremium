package insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.premiumcalculator.PremiumCalculator;

@RestController
public class PremiumCalculatorController {

	private final PremiumCalculator calculator;

	@Autowired
    public PremiumCalculatorController(PremiumCalculator calculator) {
        this.calculator = calculator;
    }

	@GetMapping("/premium")
	public Double GetPremium() {
		return calculator.Premium;
	}
}
