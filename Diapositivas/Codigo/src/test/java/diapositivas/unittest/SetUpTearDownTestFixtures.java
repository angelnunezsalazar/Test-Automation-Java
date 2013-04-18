package diapositivas.unittest;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class SetUpTearDownTestFixtures {

	@BeforeClass
	public void SetUpFixture() {
		expensiveSetupOperation();
	}

	@AfterClass
	public void TearDownFixture() {
		expensiveSetupOperation();
	}

	private void expensiveSetupOperation() {
		
	}
}
