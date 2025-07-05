package qkart_ecommerce;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class DP {
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(Method m) throws IOException {
        switch (m.getName()) {
            case "TestCase":
                return new Object[][] {
                        { "YONEX Smash Badminton Racquet", "Tan Leatherette Weekender Duffle",
                                "Addr line 1 addr Line 2 addr line 3" },
                        { "Tan Leatherette Weekender Duffle", "YONEX Smash Badminton Racquet",
                                "TEST ADDR LINES COUNT GREATER TH" },
                        { "YONEX Smash Badminton Racquet", "Connector",
                                "1 Hacker Way Menlo Park, CA 94025" } };

        }
        return null;

    }
}
