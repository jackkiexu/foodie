package com.lami.tarsier.core.util;

public class Seq {
	private static int sn = 0;
	private static int MaxSeq = 0x7fffffff;

	public Seq(int min, int max) {
		if (min >= max || max > 0x7fffffff) {
			sn = 0;
			MaxSeq = 0x7fffffff;
		} else {
			sn = 0;
			MaxSeq = max;
		}
	}

	public static int getSequences() {
		try {
			if (sn >= MaxSeq)
				sn = 0;
			else
				sn++;

			return sn;
		} finally {
		}

	}
}