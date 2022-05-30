package net.scit.word.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.scit.word.dao.WordDAO;
import net.scit.word.vo.Word;

// �ܾ� ���߱� ����
public class GuessingGame {
	int todayScore;
	Scanner keyin = new Scanner(System.in);		

	WordDAO dao = new WordDAO();
	List<Word> todayWord;

	public GuessingGame() {
		System.out.println("\n<< �ܾ� ���߱� ���� >>");

		todayWord = choiceWord();

		startGame();
	}

	// �ܾ� ���߱� ����
	private void startGame() {
		String word, rightMean;
		Word w = null;
		List<Word> myAnswer = new ArrayList<Word>();

		for(int i=0; i<5; ++i) {
			w = todayWord.get(i);
			rightMean = w.getMean();

			System.out.println((i+1)+"�� : " + rightMean);
			System.out.print("* �ܾ� :");
			word = keyin.nextLine();

			// �Է��� �ܾ�� ����ܾ �ٸ��� ����
			if(!word.equals(w.getWord())) {
				System.out.println("** �����Դϴ�\n");
			} else {
				System.out.println("** �����Դϴ�\n");
				todayScore++;
				w.setMemorize(true);
				dao.updateWord(w);
			}
			myAnswer.add(w);
		}

		result(myAnswer);
	}

	// ���Ӱ���� ���� ���
	private void result(List<Word> myAnswer) {
		System.out.println("\n## ���� ���� ���� : " + todayScore);

		for(int i=0; i<myAnswer.size(); ++i) {
			System.out.println(myAnswer.get(i));
		}
	}

	// ������ �ܾ� ����
	private List<Word> choiceWord() {
		List<Word> list = dao.listWord();
		List<Word> choiceWord = new ArrayList<Word>();
		Set<Word> choice = new HashSet<>();

		for(int i=0; i<5; i++) {
			int number = (int)(Math.random()*list.size());

			// �ܾ�� �ߺ����� �ʾƾ� �ϰ�, �ܿ��� ���� �ܾ�� �Ѵ�.
			if(!choice.add(list.get(number))
					|| (list.get(number).getMemorize())) {
				--i;
				continue;
			}
			choiceWord.add(list.get(number));
		}
		
		return choiceWord;
	}
}
