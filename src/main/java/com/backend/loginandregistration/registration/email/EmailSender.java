package com.backend.loginandregistration.registration.email;

public interface EmailSender {
	void send(String to, String email);
}
