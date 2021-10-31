package insurance.premiumcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PremiumCalculatorApplicationTests {

	private final PremiumCalculator calculator;

	@Autowired
	public PremiumCalculatorApplicationTests(PremiumCalculator calculator) {
		this.calculator = calculator;
	}

	@Test
	void CalculateCoefficientsIfSumsBelowThreshold() {
		Double[] SumsInsured = { 100.0, 8.00 };
		Double[] Coefficients = calculator.CalculateCoefficients(SumsInsured);
		Double actual = Coefficients[0];
		assertEquals(0.014, actual);
	}

	@Test
	void CalculateCoefficientsIfSumsAboveThreshold() {
		Double[] SumsInsured = { 500.00, 102.51 };
		Double[] Coefficients = calculator.CalculateCoefficients(SumsInsured);
		Double actual = Coefficients[0];
		assertEquals(0.024, actual);
	}

	@Test
	void CalculateIfSumsBelowThreshold() {
		Double[] SumsInsured = { 100.0, 8.00 };
		Double actual = calculator.Calculation(SumsInsured);
		assertEquals(2.28, actual);
	}

	@Test
	void CalculateIfSumsAboveThreshold() {
		Double[] SumsInsured = { 500.00, 102.51 };
		Double actual = calculator.Calculation(SumsInsured);
		assertEquals(17.13, actual);
	}
}
