package net.scit.word.vo;

import java.util.Objects;

public class Word {
	private int seq;
	private String word;
	private String mean;
	private boolean memorize; // true : 암기한 단어, false : 암기하지 못한 단어
	
	public Word() {
		super();
	}

	public Word(int seq, String word, String mean, boolean memorize) {
		super();
		this.seq = seq;
		this.word = word;
		this.mean = mean;
		this.memorize = memorize;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mean, memorize, seq, word);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(mean, other.mean) && memorize == other.memorize && seq == other.seq
				&& Objects.equals(word, other.word);
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public boolean getMemorize() {
		return memorize;
	}

	public void setMemorize(boolean memorize) {
		this.memorize = memorize;
	}

	@Override
	public String toString() {
		String tmp = memorize ? "O" : "X";
		return seq + ". " + word + "-" + mean + "(" + tmp + ")";
	}
}
