package com.ahom.hrms.serviceimpl;

//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//
//@Configuration
//public class FCMInitializer {
////    @Value("${app.firebase-configuration-file}")
////   private String fireBaseConfigPath;
////
////    public String getFireBaseConfigPath() {
////        return fireBaseConfigPath;
////    }
//
////    public void setFireBaseConfigPath(String fireBaseConfigPath) {
////        this.fireBaseConfigPath = fireBaseConfigPath;
////    }
//
//    Logger logger= LoggerFactory.getLogger(FCMInitializer.class);
//
//    @PostConstruct
//   public void initialize()
//   {
//       try {
//           FileInputStream serviceAccount =
//                   new FileInputStream("push-notification-2b507-firebase-adminsdk-jlcax-8ccd49b7d4.json");
//           FirebaseOptions options = new FirebaseOptions.Builder()
//                   .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                   .build();
//
////           FirebaseOptions firebaseOptions= new FirebaseOptions.Builder().setCredentials(GoogleCredentials.
////                   fromStream(new ClassPathResource(fireBaseConfigPath).getInputStream())).build();
//           if (FirebaseApp.getApps().isEmpty())
//           {
//               FirebaseApp.initializeApp(options);
//               logger.info("Firebase application has been initialized");
//           }
//       } catch (IOException e) {
//           logger.error(e.getMessage());
//       }
//   }
//}

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {


    public FirebaseApp firebaseApp() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("push-notification-2b507-firebase-adminsdk-jlcax-6d8c4d7f0f.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        try {
            FirebaseApp.initializeApp(options);
        } catch (IllegalStateException e) {
            FirebaseApp app = FirebaseApp.getInstance();
            if (app == null) {
                throw e;
            }
        }
        return null;
    }
}