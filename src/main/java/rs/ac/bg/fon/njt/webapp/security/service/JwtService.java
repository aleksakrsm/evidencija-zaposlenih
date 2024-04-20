/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;

/**
 *
 * @author aleks
 */
@Service
public class JwtService {
    //ubacio sam 3 dependency

    private static final String signingKey = "7438b64832221d5c254a08192ef45701743f46b9d4ec2a69e84b2b0959e43338";

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(
                        getSignInKey()
                )
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSignInKey() {
//        System.out.println("kljuc pre____________" + signingKey);
        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
//        System.out.println("___________Decoders.BASE64.decode(kljuc) ____________" + keyBytes);
        Key key = Keys.hmacShaKeyFor(keyBytes);
//        System.out.println("kljuc posle hmacShaKeyFor____________" + key);
        return key;//vraca tajni kljuc
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 60 * 60 * 1000))//12 dana
                //                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);//pa kako moze prvi uslov da bude F
    }

    public String generateSimpleExpirationToken(String randomString) {
        Map<String, String> extraClaims = new HashMap<>();
        extraClaims.put("randomString", randomString);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 3 * 1000))
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }
//    public String generateSimpleExpirationToken(String randomString,String email) {
//        Map<String, String> extraClaims = new HashMap<>();
//        extraClaims.put("randomString", randomString);
//        extraClaims.put("email", email);
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + 3 * 1000))
//                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    public String extractEmailAdress(String jwt){
//        final Claims claims = extractAllClaims(jwt);
//        if (claims.containsKey("email")) {
//            System.out.println((String)claims.get("email"));
//            return (String)claims.get("email");
//        } else {
//            throw new InvalidDataException("no email claim in jwt");
//        }
//    }
    
    public boolean isSimpleExpirationTokenValid(String jwt, String randomString) {
        final Claims claims = extractAllClaims(jwt);
//        System.out.println("exsp??????????????/");
//        System.out.println(isTokenExpired(jwt));
//        System.out.println("random string");
//        System.out.println(claims.get("randomString"));
//        System.out.println("RESULT");
//        System.out.println(randomString.equals(claims.get("randomString")) && !isTokenExpired(jwt));
        if (claims.containsKey("randomString")) {
            return randomString.equals(claims.get("randomString")) && !isTokenExpired(jwt);
        } else {
            return false;
        }
    }
    
    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
