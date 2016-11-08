package pl.marczyk.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.marczyk.core.Credentials;
import pl.marczyk.model.File;
import pl.marczyk.pages.UserFilesPage;

import java.util.Collection;
import java.util.List;

/**
 * Author: marcin on 08.11.16.
 */
@RestController
@RequestMapping(method = RequestMethod.GET, value = "/api")
public class RapideoController {

    @Value("${credentials.login}")
    private String login;
    @Value("${credentials.password}")
    private String password;

    @RequestMapping("/userFiles")
    public Collection<File> getUserFiles(){
        List<File> standardFiles = Lists.newArrayList();
        UserFilesPage userFilesPage = new UserFilesPage(new WebClient());
        try {
            standardFiles = userFilesPage.getStandardFiles(new Credentials(login, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return standardFiles;
    }
}
