package treeproject;
import java.util.Date;

public class Wedding {

	public Date weddingDate;

	public Couple couple;

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