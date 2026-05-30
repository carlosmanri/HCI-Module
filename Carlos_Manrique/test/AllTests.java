import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import logic.billTest.BillCartActionsTest;
import logic.billTest.BillConstructorTest;
import logic.billTest.BillGetTotalPriceTest;
import logic.billTest.BillSettersTest;
import logic.billTest.BillToStringTest;
import logic.parserTest.ProductParserTest;
import logic.parserTest.UserParserTest;
import logic.parserTest.UserSerializerTest;
import logic.productTest.ProductTest;
import logic.userTest.UserAccountTest;

@RunWith(Suite.class)
@SuiteClasses({
	UserParserTest.class,
	UserAccountTest.class,
	ProductTest.class,
	ProductParserTest.class,
	BillConstructorTest.class,
	BillGetTotalPriceTest.class,
	BillCartActionsTest.class,
	BillSettersTest.class,
	BillToStringTest.class,
	UserSerializerTest.class
})
public class AllTests {

}
