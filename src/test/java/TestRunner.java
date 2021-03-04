import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources"},
        glue = {"Test"},
        plugin = {"pretty","html:target/cucumber/report.html", "json:target/cucumber/report.json"},
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
