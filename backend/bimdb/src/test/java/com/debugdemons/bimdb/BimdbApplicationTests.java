package com.debugdemons.bimdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BimdbApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> BimdbApplication.main(new String[0]));
	}

}
