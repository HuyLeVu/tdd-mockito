package test_tdd_password;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class TestPassword {

	@Test
	public void shouldReturnTrueWhenPasswordBetween6To10(){
		String password = "smartdev";
		Assertions.assertThat(password).matches("^\\w[a-zA-Z]{6,10}$");
	}
	
	@Test
	public void shouldReturnTrueWhenPasswordContainLeastOneDigit(){
		String password = "smartdev1";
		Assertions.assertThat(password).matches("(.*\\d.*)");
	}
	
	@Test
	public void shouldReturnTrueWhenPasswordContainOneUpperCaseLetter(){
		String password = "Smartdev1";
		Assertions.assertThat(password).matches("(.*[A-Z].*)");
	}

}
