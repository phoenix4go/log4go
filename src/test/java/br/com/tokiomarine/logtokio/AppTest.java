package br.com.tokiomarine.logtokio;

import com.phoenix4go.log4go.logging.Logger4Go;
import com.phoenix4go.log4go.logging.Logger4GoFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest {

    private static Logger4Go logger;
    private static Logger4Go loggerOther;

    @BeforeClass
    public static void beforeClass() {
        logger = Logger4GoFactory.getLogger(AppTest.class, "log4go", "lib");
        loggerOther = Logger4GoFactory.getLogger(AppTest.class, "log4go", "lib");
    }

    @Test
    public void testLogger() {
        logger.info("Hello");
        loggerOther.warn("Warn");
        Assert.assertTrue(true);
    }

}
