package com.fpoly.service;

import jakarta.mail.MessagingException;

public interface MailService {
	void send(String to, String body) throws MessagingException;
}
