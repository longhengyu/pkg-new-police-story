package com.siplan.newpolice.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class MessageTest {

	@Test public void test() {
		Message msg = new Message();
		msg.setId(112L);
		assertNotNull(msg.getId());
	}
}
