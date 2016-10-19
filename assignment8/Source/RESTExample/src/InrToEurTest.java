import static org.junit.Assert.*;

import org.junit.Test;

public class InrToEurTest {InrToEur obj = new InrToEur();
double eur = obj.convertInrtoEurfromInput(1000.0);
double testVal = 12;

@Test
public void test() {
	System.out.println("@Test eur: " + eur + " = " + testVal);
	assertEquals((int) eur, (int) testVal);
}

}
