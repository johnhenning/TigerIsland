/**
 * Created by kyle on 3/16/17.
 */

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@Cucumber.Options(
        features={"src/test/Gherkin"}
)
public class CukesRunner {}