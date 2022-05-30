package net.scit.word.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import net.scit.word.dao.WordDAO;
import net.scit.word.vo.Word;

// 단어 맞추기 게임
public class GuessingGame {
	int todayScore;
	Scanner keyin = new Scanner(System.in);		

	WordDAO dao = new WordDAO();
	List<Word> todayWord;

	public GuessingGame() {
		System.out.println("\n<< 단어 맞추기 게임 >>");

		todayWord = choiceWord();

		startGame();
	}

	// 단어 맞추기 게임
	private void startGame() {
		String word, rightMean;
		Word w = null;
		List<Word> myAnswer = new ArrayList<Word>();

		for(int i=0; i<5; ++i) {
			w = todayWord.get(i);
			rightMean = w.getMean();

			System.out.println((i+1)+"번 : " + rightMean);
			System.out.print("* 단어 :");
			word = keyin.nextLine();

			// 입력한 단어와 정답단어가 다르면 오답
			if(!word.equals(w.getWord())) {
				System.out.println("** 오답입니다\n");
			} else {
				System.out.println("** 정답입니다\n");
				todayScore++;
				w.setMemorize(true);
				dao.updateWord(w);
			}
			myAnswer.add(w);
		}

		result(myAnswer);
	}

	// 게임결과와 점수 출력
	private void result(List<Word> myAnswer) {
		System.out.println("\n## 오늘 맞춘 개수 : " + todayScore);

		for(int i=0; i<myAnswer.size(); ++i) {
			System.out.println(myAnswer.get(i));
		}
	}

	// 오늘의 단어 선택
	private List<Word> choiceWord() {
		List<Word> list = dao.listWord();
		List<Word> choiceWord = new ArrayList<Word>();
		Set<Word> choice = new HashSet<>();

		for(int i=0; i<5; i++) {
			int number = (int)(Math.random()*list.size());

			// 단어는 중복되지 않아야 하고, 외우지 않은 단어여야 한다.
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
