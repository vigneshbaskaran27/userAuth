package com.example.userauthorization.services;

import com.example.userauthorization.enums.SessionStatus;
import com.example.userauthorization.models.Session;
import com.example.userauthorization.models.User;
import com.example.userauthorization.repository.SessionRepository;
import com.example.userauthorization.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IuserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SecretKey secretKey;

    public User signUpService(String email , String password)
    {
        //1.Checking whether user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
           return optionalUser.get();
        }
        //if not creating the user and returning
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public Pair<User, MultiValueMap<String, String>> loginService(String email , String password)
    {
        //check user Existence
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            return null;
        }

        //password validation
        if(!bCryptPasswordEncoder.matches(password, optionalUser.get().getPassword()))
        {
            return null;
        }
        User user = optionalUser.get();
        //generate token
        //1.payload
//        String message = "{\n" +
//                    " \"email\": \"vignesh.com\" , \n" +
//                    "\"roles\": \"[student]\" , \n"+
//                    "\"expiryTime\": \"2ndJuly2024\" , \n" +
//                        "}";

        //1a creating payload as per user
        Map<String , Object> jwtHeaders= new HashMap<>();
        jwtHeaders.put("email" , user.getEmail());
        jwtHeaders.put("roles", user.getRoles());
        long millis = System.currentTimeMillis();
        jwtHeaders.put("iat" ,millis);
        jwtHeaders.put("exp" ,millis+1000000);

        //2.encoded 64-bit payload
        //byte[] content = message.getBytes(StandardCharsets.UTF_8);
        //3/token generation
        //String token= Jwts.builder().content(content).compact();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        //using algo and secret for generation of sign

        //String token= Jwts.builder().content(content).signWith(secretKey).compact();
        //creating token asper user with the jwtHeader map
        String token = Jwts.builder().claims(jwtHeaders).signWith(secretKey).compact();
        headers.set(HttpHeaders.SET_COOKIE , token);

        //creating and persisting the session in db
        Session session = new Session();
        session.setUser(user);;
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

      // return new Pair<User , MultiValueMap<String, String>(user, headers)>();
        return new Pair<User, MultiValueMap<String, String>>(user,headers);
    }

    @Override
    public boolean validateToken(String token, Long userId)
    {
        //check whether token is validsession
        Optional<Session> optionalSession= sessionRepository.findByTokenEquals(token);
        if(!optionalSession.isPresent()){
            return false;
        }
        Session session = optionalSession.get();

        //parsing the token to get the payload in which timestamp details present
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        System.out.println("passed 1");
        Claims claims=jwtParser.parseSignedClaims(token).getPayload();
        System.out.println("passed 2");

        Long expiryTimeinEpoch = (long) claims.get("exp");
        Long currentTime = System.currentTimeMillis();
        System.out.println(expiryTimeinEpoch +"  "+currentTime);
        if(expiryTimeinEpoch < currentTime){
            System.out.println("expiryTimeinEpoch is lesser than currentTime");
            return false;
        }
//
//        Optional<User> optionalUser = userRepository.findUserById(userId);
//        if(!optionalUser.isPresent())
//        {
//            return false;
//        }
//
//        User user = optionalUser.get();
//        if(!user.getEmail().equals(claims.get("email")) || !user.getRoles().equals(claims.get("roles")))
//        {
//            return false;
//        }


        return true;

    }

}
