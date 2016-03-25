package com.study.lucene;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLog {

	@Test
	public void testLog() {

		Logger logger = Logger.getLogger(this.getClass());
		logger.error("aa");

		try {
			System.out.println(1 / 0);
		} catch (Exception e) {
			logger.info(e);
		}

	}

}
