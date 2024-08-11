package com.example.bookmarketservice.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import exception.AudienceIncorrectException;
import exception.IssuerIncorrectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImp implements TokenService {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Override
    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            if(!decodedJWT.getIssuer().equals("auth-service")){
                System.err.println("Issuer is incorrect");
                return false;
            }

            if (!decodedJWT.getAudience().contains("bookmarket")){
                System.err.println("Audience is incorrect");
                return false;
            }

        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
            return false;
        }
        
        return true;
    }
}
