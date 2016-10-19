import static org.junit.Assert.*;

import org.junit.Test;

public class UsdToInrTest {
	UsdToInr obj = new UsdToInr();
	double inr = obj.convertUSDToINRfromInput(101.0);
	double testVal = 6664;

	@Test
	public void test() {
		System.out.println("@Test inr: " + inr + " = " + testVal);
		assertEquals((int) inr, (int) testVal);
	}

}
