package com.testsdemo.testcrud.util;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    private String publicKeyPath ;
    private String privateKeyPath ;
    private final String alg = "RSA";
    private final int expireIn = 1000;
    private final String id = "TESTTTT555";

    public JwtUtil(){
    }
    public JwtUtil(String publicKeyPath)  throws Exception {
        this.publicKeyPath = publicKeyPath;
    }
    public JwtUtil(String publicKeyPath,String privateKeyPath)  throws Exception {
        this.publicKeyPath = publicKeyPath;
    }


    public String enCode(HashMap<String, String> mapData ) throws ParseException {
        String token = null;
        Date exp = PemUtil.getExpirationTime(expireIn);
//		System.out.println("exp :"+exp);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("JWT", "typ");
        Map<String, Object> claims = new HashMap<String, Object>();

        mapData.forEach((k,v)->claims.put(k,v));

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS512;
        try{
            PrivateKey privateKey = PemUtil.readPrivateKeyFromFile(publicKeyPath, privateKeyPath);
            token = Jwts.builder()
                    .setHeader(header)
                    .setClaims(claims)
                    .setId(id)
                    .setExpiration(exp)
                    .signWith(signatureAlgorithm, privateKey)
                    .compact();
        }catch(Exception ex){
            System.out.println("ERROR MAGSAG :"+ex.getMessage());
            ex.printStackTrace();
        }
        return token;
    }

    public Claims deCode(String token)throws Exception{
        Claims claims = null;
        try{
            PublicKey publicKey = PemUtil.readPublicKeyFromFile(this.publicKeyPath,this.alg);
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();

            //System.out.println("Header :"+Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getHeader());
            //System.out.println("Body :"+Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody());
            //System.out.println("ToKen :"+Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getSignature().toString());

        }catch (ExpiredJwtException expired) {
            throw expired;
        } catch (MalformedJwtException malformedJwt) {
            throw malformedJwt;
        } catch (SignatureException signature) {
            throw signature;
        } catch (Exception e) {
            throw e;
        }
        return claims;
    }
}

