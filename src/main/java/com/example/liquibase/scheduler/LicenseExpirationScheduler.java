///*
//package com.example.liquibase.scheduler;
//
//
//        import com.example.liquibase.domain.User;
//        import com.example.liquibase.domain.enums.Role;
//        import com.example.liquibase.repository.UserRepository;
//        import lombok.RequiredArgsConstructor;
//        import lombok.extern.slf4j.Slf4j;
//        import org.springframework.scheduling.annotation.Scheduled;
//        import org.springframework.stereotype.Component;
//
//        import java.time.LocalDateTime;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class LicenseExpirationScheduler {
//
//    private final UserRepository userRepository;
//    @Scheduled(fixedRate = 60000) // 60000 ms = 1 minute
//    public void checkLicenses() {
//        LocalDateTime now = LocalDateTime.now();
//        log.info("Checking licenses at: " + now);
//
//        userRepository.findAll().forEach(user -> {
//            if (user.getLicenseExpirationDate() != null && user.getLicenseExpirationDate().isBefore(now)) {
//                log.info("License expired for user: {} {} ({})",
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getEmail());
//            } else {
//                log.info("License still valid for user: {} {} - Expires: {}",
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getLicenseExpirationDate());
//            }
//        });
//    }
//}*/
