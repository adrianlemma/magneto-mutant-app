package com.magneto.mutant.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MetricsTest {

	Metrics metrics = new Metrics(40, 100);
	
	@Test
	void testMetrics() {
		Assertions.assertNotNull(metrics);
	}

	@Test
	void testGetCount_mutant_dna() {
		Assertions.assertEquals(40, metrics.getCount_mutant_dna());
	}

	@Test
	void testSetCount_mutant_dna() {
		metrics.setCount_mutant_dna(60);
		Assertions.assertEquals((float)0.6, metrics.getRatio());
	}

	@Test
	void testGetCount_human_dna() {
		Assertions.assertEquals(100, metrics.getCount_human_dna());
	}

	@Test
	void testSetCount_human_dna() {
		metrics.setCount_human_dna(200);
		Assertions.assertEquals((float)0.2, metrics.getRatio());
		metrics.setCount_human_dna(0);
		Assertions.assertEquals((float)0, metrics.getRatio());
	}

	@Test
	void testGetRatio() {
		Assertions.assertEquals((float)0.4, metrics.getRatio());
	}

}
