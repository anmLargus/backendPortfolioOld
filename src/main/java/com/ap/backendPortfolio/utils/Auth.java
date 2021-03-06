package com.ap.backendPortfolio.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

public class Auth {
	
	static private String secret = "Usuario1";
	
	static public String generateJWT(Login p) { // TODO recibir payload
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

		    String token = JWT.create()
		        .withIssuer("auth0")
		        .withClaim("user", p.email)
		        .withClaim("admin", true)
		        .withClaim("role", "admin")
		        .sign(algorithm);

			return token;
		} catch(IllegalArgumentException e) {
			return "";
		}
		
		catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	static public boolean verify(String t) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);

		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance

    		verifier.verify(t);

		    return true;
		} catch(JWTVerificationException e) {
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
