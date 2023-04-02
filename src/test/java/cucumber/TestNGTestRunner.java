package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features="src/test/java/cucumber",
                  glue="SeleniumFrameworkDesign.StepDefinition",
                  monochrome=true,
                  tags= "@Regression",
                  plugin= {"pretty","html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	

}
