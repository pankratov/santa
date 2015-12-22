package ru.eastwind.santa.business;

import ru.eastwind.santa.domain.Behaviour;
import ru.eastwind.santa.domain.Mail;

public interface BehaviourAnalyzer {
	Behaviour analyse(Mail mail);
}
