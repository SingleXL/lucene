package com.study.lucene;

import org.junit.Test;

public class TestIndex {

	@Test
	public void testIndex() {
		IndexUtil iUtil = new IndexUtil();
		iUtil.index();

	}

	@Test
	public void testQuery() {
		IndexUtil iUtil = new IndexUtil();
		iUtil.query();
	}
	
}
