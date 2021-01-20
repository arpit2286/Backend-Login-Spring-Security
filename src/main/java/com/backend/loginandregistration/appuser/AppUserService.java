package com.backend.loginandregistration.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.loginandregistration.registration.token.ConfirmationToken;
import com.backend.loginandregistration.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final static String USER_NOT_FOUND = "User with email %s not found";

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}

	public int enableAppUser(String email) {
		return appUserRepository.enableAppUser(email);
	}

	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("email Already taken");
		}
		String encoded = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encoded);
		appUserRepository.save(appUser);
		// TODO: Send confirmation token

		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), appUser);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		// TODO: Email Trigger
		return token;
	}
}
