package com.spring.ecommerce.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.emailConfirmation.ConfirmationToken;
import com.spring.ecommerce.emailConfirmation.ConfirmationTokenRepo;
import com.spring.ecommerce.emailConfirmation.EmailService;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.UserRepo;
import com.spring.ecommerce.security.jwt.AuthenticationRequest;
import com.spring.ecommerce.security.jwt.AuthenticationResponse;
import com.spring.ecommerce.security.jwt.JwtProvider;
import com.spring.ecommerce.security.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/*************************************************************************
 * {@link AuthServiceImpl} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-1-29
 *************************************************************************/
@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl service;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ConfirmationTokenRepo tokenRepo;
	@Autowired
	EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*************************************************************************
	 * Create a new User
	 * 
	 * @param ob {@link User} object
	 * @return {@link String}
	 *************************************************************************/
	@Override
	public String create(User user, HttpServletResponse rs) {
		User existingUser = userRepo.findByEmailIgnoreCase(user.getEmail());
		if (existingUser != null) {
			rs.setHeader("Denied", "User exists!!!");
			return "User exists!!!";
		} else {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepo.save(user);
				ConfirmationToken confirmationToken = new ConfirmationToken(user);
				tokenRepo.save(confirmationToken);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("Complete Registration!");
				mailMessage.setText("To confirm your account, please click here : "
						+ "http://localhost:8080/api/auth/confirm-account?token="
						+ confirmationToken.getConfirmationToken());
				emailService.sendEmail(mailMessage);
			} catch (Exception e) {
				log.warn("Failed to create  Product: ", e);
				rs.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return "Failed to create  User ";
			}
		}
		return "Account created. Check your mail for confirmation..";
	}

	/*************************************************************************
	 * User Login
	 * 
	 * @param ob {@link AuthenticationRequest} object
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link JWT}
	 *************************************************************************/
	@Override
	public ResponseEntity<?> createToken(AuthenticationRequest authenticationRequest) throws Exception {

		final User user = userRepo.findByEmailAndActive(authenticationRequest.getEmail(), true);
		if (user == null) {
			return ResponseEntity.ok("User is Not Activated Yet");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Email Or Pasword.", e);
		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());

		final String jwt = jwtProvider.generateToken(userDetails);

		System.out.println(jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	/*************************************************************************
	 * Activate newly created USer
	 * 
	 * @param ob {@link Token} String
	 * @param rs {@link HttpServletResponse} object
	 * @return {@link String}
	 *************************************************************************/
	@Override
	public String confirmUserAccount(String token, HttpServletResponse rs) {
		ConfirmationToken cToken = tokenRepo.findByConfirmationToken(token);
		if (cToken != null) {
			User user = userRepo.findByEmailIgnoreCase(cToken.getUser().getEmail());
			user.setActive(true);
			userRepo.save(user);
			return "User Activated";
		} else {
			rs.setHeader("error", "The link is invalid or broken!");
			return "Failed Activation";
		}
	}
}
