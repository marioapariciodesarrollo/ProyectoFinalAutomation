package ProyectoBDD_POM.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ProyectoBDD_POM.steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}