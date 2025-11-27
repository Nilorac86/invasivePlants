//package com.carolin.invasiveplants.SecurityConfig;
//
//import com.carolin.invasiveplants.Entity.User;
//import com.carolin.invasiveplants.Repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import java.util.List;
//
//@Component
//public class PasswordHashMigration implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public PasswordHashMigration(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            String pwd = user.getPassword();
//
//            // Hoppa över lösenord som redan är hashade (BCrypt börjar alltid med $2)
//            if (pwd != null && !pwd.startsWith("$2")) {
//                String hashed = passwordEncoder.encode(pwd);
//                user.setPassword(hashed);
//                userRepository.save(user);
//            }
//        }
//
//        System.out.println("Password migration complete.");
//    }
//}

