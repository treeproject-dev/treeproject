package group1.gentrees;

import java.util.Date;

public class Wedding {

	private Date weddingDate;

	private Couple couple;

	public Wedding(Date date, Couple c) {

		weddingDate = date;

		couple = c;
	}

	public Date getWeddingDate() {

		return weddingDate;

	}

	public Couple getCouple() {

		return couple;

	}

}