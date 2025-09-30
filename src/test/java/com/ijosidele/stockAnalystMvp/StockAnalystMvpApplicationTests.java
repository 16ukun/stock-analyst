package com.ijosidele.stockAnalystMvp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = StockAnalystMvpApplication.class) // explicitly point to the main app
@ActiveProfiles("test") // ensures to use application-test.yml
class StockAnalystMvpApplicationTests {

	@Test
	void contextLoads() {
	}

}
