package pl.marczyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.marczyk.model.File;
import pl.marczyk.repository.FileRepository;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: marcin on 08.11.16.
 */
@RestController
@RequestMapping(method = RequestMethod.GET, value = "/api")
public class RapideoController {
    private final Logger LOGGER = Logger.getLogger(RapideoController.class.getName());

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping("/userFiles")
    public Collection<File> getUserFiles(){
        LOGGER.log(Level.INFO, "Serving user files");
        return fileRepository.getUserFiles();
    }
}
