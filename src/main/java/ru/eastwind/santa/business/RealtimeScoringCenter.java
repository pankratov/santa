package ru.eastwind.santa.business;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.eastwind.santa.domain.Behaviour;
import ru.eastwind.santa.domain.Mail;

// TODO: написать ЮНИТ-тест для класса
@Component
public class RealtimeScoringCenter implements ScoringCenter {

	// TODO: внедрить спринговую зависимость
	private ContentBasedBehaviourAnalyzer contentBasedBehaviourAnalyzer = new ContentBasedBehaviourAnalyzer();

	@Autowired
	private Santa santa;
	
	private Map<String, Integer> scores = new HashMap<>();
	
	/**
	 * Получает из письма с заголовком
	 *     вида 'Report on John Black' имя
	 * @param mail - письмо
	 * @return имя, указанное в заголовке
	 */
	private String getChildNameByMail(Mail mail) {
		return mail.getTitle().replace("Report on ", "");
	}
	
	private synchronized Integer estimate(Behaviour behaviour, String childName) {
		Integer score = scores.getOrDefault(childName, 0);
		if(Behaviour.Good.equals(behaviour)) {
			score = score + 1;
		} else {
			score = score - 1;
		}
		scores.put(childName, score);
		return score;		
	}
	
	public void consider(Mail mail) {
		Behaviour behaviour = contentBasedBehaviourAnalyzer.analyse(mail);
		String childName = getChildNameByMail(mail);
		Integer score = estimate(behaviour, childName);
		if(score < -1) {
			santa.complain(childName);
		}
	}

	@Override
	public Optional<Integer> getScore(String childName) {
		return Optional.ofNullable(scores.get(childName));
	}

}
