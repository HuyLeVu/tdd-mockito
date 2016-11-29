package test_tdd_helloword;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


import org.junit.Test;

import tdd_helloword.Greeter;

public class GreeterTest {
	@Test
	public void shouldSayHelloWord(){
		Greeter greeter = new Greeter();
		String helloMesg = greeter.sayHello();
		
		assertThat(helloMesg, equalTo("Hello Word !"));
		
	}
}
