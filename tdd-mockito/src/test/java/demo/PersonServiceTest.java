package demo;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	@Mock PersonDao personDao;
	@InjectMocks PersonService personService;
	
	@Test
	public void shouldUpdatePersonName(){
		Person person = new Person(1, "Phillip");
		when(personDao.fetchPerson(1)).thenReturn(person);
		
		Assertions.assertThat(personService.update(1, "Davic")).isTrue();
		
		 // optional
		verify(personDao).fetchPerson(1);
		
		ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
		verify(personDao).update(argumentCaptor.capture());
		Assertions.assertThat(argumentCaptor.getValue().getPersonName()).isEqualTo("Davic");
		
	}
	
	@Test public void shouldNotUpdateIfPersonNotFound(){
		when(personDao.fetchPerson(1)).thenReturn(null);
		Assertions.assertThat(personService.update((1), "Davic")).isFalse();
		verify(personDao).fetchPerson(1);
		verifyZeroInteractions(personDao);
		verifyNoMoreInteractions(personDao);
	}
	

}
