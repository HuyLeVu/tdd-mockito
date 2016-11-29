package tutorial;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class MainPro {

	// 1. Let's verify some behaviour!
	@Test
	public void verifySomeBehaviour(){
		//mock creation
		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	// 2. How about some stubbing?
	@Test
	public void someStubbing() throws Exception{
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("1");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		//following prints "1"
		// System.out.println(mockedList.get(0));
		//assertEquals("1", mockedList.get(0));
		//following throws runtime exception
		// System.out.println(mockedList.get(1));


		//following prints "null" because get(999) was not stubbed
		// System.out.println(mockedList.get(999));
		assertNull(mockedList.get(999));
	}

	// 3. Argument matchers
	@Test
	public void argumentMatchers(){
		//stubbing using built-in anyInt() argument matcher
		LinkedList mockedList = mock(LinkedList.class);
		when(mockedList.get(anyInt())).thenReturn("element");

		Assertions.assertThat(mockedList.get(999)).isEqualTo("element");

		verify(mockedList).get(anyInt());

		when(mockedList.subList(anyInt(), anyInt())).thenReturn(null);
		Assertions.assertThat(mockedList.subList(2, 1)).isNull();

	}
	// Verifying exact number of invocations / at least x / never
	@Test
	public void verifyExactNumberOfInvocations() throws Exception{
		LinkedList mockedList = mock(LinkedList.class);

		//using mock
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		//following two verifications work exactly the same - times(1) is used by default
		//verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		//exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		//verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("dnever happened");

		//verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(3)).add("three times"); 
	}

	// 5. Stubbing void methods with exceptions
	@Test(expected = RuntimeException.class)
	public void stubbingVoidMethodsWithExceptions() throws Exception{
		LinkedList mockedList = mock(LinkedList.class);
		doThrow(new Exception()).when(mockedList).clear();

		//following throws RuntimeException:
		mockedList.clear();
	}

	// 6. Verification in order
	@Test
	public void orderVerification() throws Exception{
		// A. Single mock whose methods must be invoked in a particular order
		List singleMock = mock(List.class);

		//using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		//create an inOrder verifier for a single mock
		InOrder inOrder = Mockito.inOrder(singleMock);

		//following will make sure that add is first called with "was added first,
		//then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List firstMock = mock(List.class);
		List secondMock = mock(List.class);

		//using a mock
		firstMock.add("was added first");
		firstMock.add("was called first-2");
		secondMock.add("was added second");

		//create inOrder object passing any mocks that need to be verified in order
		InOrder inOrder2 = Mockito.inOrder(firstMock, secondMock);

		//following will make sure that firstMock was called before secondMock
		inOrder2.verify(firstMock).add("was added first");
		inOrder2.verify(firstMock).add("was called first-2");
		inOrder2.verify(secondMock).add("was added second");
	}
	// 7. Making sure interaction(s) never happened on mock
	@Test
	public void interactionNeverHappenedOnMock() throws Exception{

		List mockOne = mock(List.class);
		List mockTwo = mock(List.class);
		List mockThree = mock(List.class);
		//using mocks - only mockOne is interacted
		mockOne.add("one");
		//ordinary verification
		verify(mockOne).add("one");

		//verify that method was never called on a mock
		verify(mockOne, never()).add("two");

		//verify that other mocks were not interacted
		verifyZeroInteractions(mockTwo,mockThree);

		// failed.
		/*mockOne.add("one");
        verifyZeroInteractions(mockOne);*/
	}

	// 8. Finding redundant invocations
	@Test
	public void redundantInvocations() throws Exception { 

		LinkedList mockedList = mock(LinkedList.class);
		//using mocks
		mockedList.add("one");
		mockedList.add("two");

		verify(mockedList).add("one");
		//following verification will fail.
		//removing add "two".
		verifyNoMoreInteractions(mockedList);

	}

	// 10. Stubbing consecutive calls (iterator-style stubbing)
	@Test
	public void consecutiveCalls() throws Exception {
		List mockedList = mock(List.class);
		when(mockedList.get(anyInt())).thenReturn("one", "two");
		Assertions.assertThat(mockedList.get(0)).isEqualTo("one");
		Assertions.assertThat(mockedList.get(1)).isEqualTo("two");
	}

	// 13. Spying on real objects
	@Test
	public void spyingOnRealObject() throws Exception {
		List list = new LinkedList();
		List spy = Mockito.spy(list);

		//optionally, you can stub out some methods:
		when(spy.size()).thenReturn(100);

		//using the spy calls *real* methods
		spy.add("one");
		spy.add("two");

		//prints "one" - the first element of a list
		Assertions.assertThat(spy.get(0)).isEqualTo("one");

		//size() method was stubbed - 100 is printed
		Assertions.assertThat(spy.size()).isEqualTo(100);

		//optionally, you can verify
		verify(spy).add("one");
		verify(spy).add("two");

		// Important gotcha on spying real objects!
		// Sometimes it's impossible or impractical to use when(Object) for stubbing spies.
		// Therefore when using spies please consider doReturn|Answer|Throw() family of methods for stubbing. Example:
		List list2 = new LinkedList();
		List spy2 = spy(list2);

		//Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
		//when(spy2.get(0)).thenReturn("foo");

		//You have to use doReturn() for stubbing
		doReturn("foo").when(spy2).get(0);

		Assertions.assertThat(spy2.get(0)).isEqualTo("foo");
	}


	// 14. Changing default return values of unstubbed invocations (Since 1.7)
	@Test
	public void unstubbedInvocations() throws Exception {
		List mockedList = mock(List.class, RETURNS_SMART_NULLS);
		String str  = mockedList.toString();
		String str2 = str.toLowerCase();
		Assertions.assertThat(str2).isEqualTo("NPE");
	}

	// 15. Capturing arguments for further assertions (Since 1.8.0)
	// http://stackoverflow.com/questions/12295891/how-to-use-argumentcaptor-for-stubbing
	@Test
	public void shouldReturnTheSameValue() {
		List mock = mock(List.class);
		when(mock.get(anyInt())).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation){
				Object[] args = invocation.getArguments();
				//Object mock = invocation.getMock();
				return "called with arguments: " + args;
			}
		});
	}
}









