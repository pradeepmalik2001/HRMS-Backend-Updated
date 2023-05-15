package com.ahom.hrms.serviceimpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class FCMInitializer {
//    @Value("${app.firebase-configuration-file}")
//   private String fireBaseConfigPath;
//
//    public String getFireBaseConfigPath() {
//        return fireBaseConfigPath;
//    }

//    public void setFireBaseConfigPath(String fireBaseConfigPath) {
//        this.fireBaseConfigPath = fireBaseConfigPath;
//    }

    Logger logger= LoggerFactory.getLogger(FCMInitializer.class);

    @PostConstruct
   public void initialize()
   {
       try {
           FileInputStream serviceAccount =
                   new FileInputStream("push-notification-2b507-firebase-adminsdk-jlcax-8ccd49b7d4.json");
           FirebaseOptions options = new FirebaseOptions.Builder()
                   .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                   .setDatabaseUrl("https://push-notification-2b507-default-rtdb.firebaseio.com/")
                   .build();

//           FirebaseOptions firebaseOptions= new FirebaseOptions.Builder().setCredentials(GoogleCredentials.
//                   fromStream(new ClassPathResource(fireBaseConfigPath).getInputStream())).build();
           if (FirebaseApp.getApps().isEmpty())
           {
               FirebaseApp.initializeApp(options);
               logger.info("Firebase application has been initialized");
           }
       } catch (IOException e) {
           logger.error(e.getMessage());
       }
   }
}
