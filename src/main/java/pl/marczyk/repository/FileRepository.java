package pl.marczyk.repository;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.marczyk.core.Credentials;
import pl.marczyk.model.File;
import pl.marczyk.pages.UserFilesPage;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: marcin on 08.11.16.
 */
@Component
public class FileRepository {
    private final Logger LOGGER = Logger.getLogger(FileRepository.class.getName());

    private final int UPDATE_RATE_MINUTES = 5;

    @Value("${credentials.login}")
    private String login;
    @Value("${credentials.password}")
    private String password;

    private Collection<File> userFiles = Lists.newArrayList();

    public Collection<File> getUserFiles() {
        return userFiles;
    }

    @Scheduled(fixedRate = UPDATE_RATE_MINUTES * 60*1000)
    public void updateUserFiles(){
        LOGGER.log(Level.INFO, "Fetching user files periodically");
        UserFilesPage userFilesPage = new UserFilesPage(new WebClient());
        try {
            userFiles = userFilesPage.getStandardFiles(new Credentials(login, password));
            LOGGER.log(Level.INFO, "User files fetched succesfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during fetching user files: {0}", e.getStackTrace());
        }
    }

}
