package com.packt.legacy.powermockito;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SuppressMethod.class)
public class SuppressMethodTest {

	@Test
	public void supresses_method() throws Exception {
		 suppress(method(SuppressMethod.class, "getCurrency"));
		 SuppressMethod method = new SuppressMethod();
		 assertFalse(method.format("10").contains("$"));
	}
}
