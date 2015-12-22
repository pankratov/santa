package ru.eastwind.santa.business;

public class NotSupportedMailException extends RuntimeException {

	private static final long serialVersionUID = -2209603674151123396L;

	public NotSupportedMailException(String message) {
        super(message);
    }
}
