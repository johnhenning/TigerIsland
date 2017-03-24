package AcceptanceTests; /**
 * Created by johnhenning on 3/15/17.
 */
import cucumber.api.junit.*;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@Cucumber.Options(
        features={"src/test/Gherkin"}
)

public class CukesRunner {}

