package edu.sjsu.cmpe295.resonance;

import edu.sjsu.cmpe295.resonance.Utils.EmailNotification;
import edu.sjsu.cmpe295.resonance.Utils.S3Connector;
import edu.sjsu.cmpe295.resonance.services.Song.SongService;
import edu.sjsu.cmpe295.resonance.services.Song.SongServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean
    public EmailNotification getEmailNotification(){
        return new EmailNotification();
    }

    @Bean
    public SongService getSongService(){
        return new SongServiceImpl();
    }

    @Bean
    public S3Connector getS3Connector(){
        return new S3Connector();
    }

}
