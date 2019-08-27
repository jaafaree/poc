package com.jaafar.poc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 8/27/2019 3:41 PM
 */
@RunWith(JUnit4.class)
public class PoCExtensionTests {

    class TestExtension {

        public void process() {
            System.out.println("execute process");
            this.processExtension();
        }

        public void processExtension() {
            System.out.println("execute in TestExtension");
        }

    }

    class SubTestExtension extends TestExtension {

        @Override
        public void processExtension() {
            System.out.println("execute in SubTestExtension");
        }
    }

    class Sub2TestExtension extends SubTestExtension {

        public void perform() {
            super.process();
        }

    }

    @Test
    public void testExtension() {
        new Sub2TestExtension().perform();
        new SubTestExtension().process();
    }

}
