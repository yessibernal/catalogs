package com.innter.msdesigncatalog.services.IMPL;

import com.innter.msdesigncatalog.dtos.RolDto;
import com.innter.msdesigncatalog.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.innter.msdesigncatalog.constants.UtilConstants.SCOPES;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${token-public-key}")
    private String  tokenPublicKey;

    @Override
    public String extractUserName(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }



    @Override
    public boolean isTokenValid(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println(ex);
//            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.err.println(ex);
//            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println(ex);
//            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println(ex);
//            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println(ex);
//            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDto> extractScopes(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        List<String> scopes = (List<String>) claims.get(SCOPES);
        return scopes.stream().map(s -> RolDto.builder().rolName(s).build()).collect(Collectors.toList());
    }

    @Override
    public LocalDateTime extractDate(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Claims claims = extractAllClaims(token);
        long epoch = (int) claims.get("exp");
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Claims extractAllClaims(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parserBuilder().setSigningKey(getSigningPublicKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private PublicKey getSigningPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicBytes = Base64.getDecoder().decode(tokenPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


}
