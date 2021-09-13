package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Calculator {
	int add(int a, int b) {
		return a + b;
	}
}

@SpringBootTest
class DemoApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int numberOne = 1;
		int numberTwo = 2;

		// when
		int result = underTest.add(numberOne, numberTwo);

		// then
		int expected = 3;
		assertThat(result).isEqualTo(expected);
	}
}
