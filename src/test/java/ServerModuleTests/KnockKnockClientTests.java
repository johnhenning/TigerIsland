package ServerModuleTests;

import ServerModule.KnockKnockClient;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by carlos on 4/3/2017.
 */
public class KnockKnockClientTests {

    private KnockKnockClient client;
    private String testString;

    @Before
    public void setup()throws Exception{
        client = new KnockKnockClient();
        testString = "NEW CHALLENGE 28 YOU WILL PLAY 8 MATCHES";

    }

    @Test
    public void parseStringFromServerTest(){
        client.parseStringFromServer(testString);
        assert client.getCid() == 28;
        assert client.getNumRounds() == 8;

    }
}
