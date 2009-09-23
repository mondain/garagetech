package net.sziebert.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service("sprocketService")
public class SprocketService {

    private static final Logger logger = LoggerFactory.getLogger(SprocketService.class);
    
    private List<Sprocket> sprockets;

    @PostConstruct
    public void init() {
        logger.debug("Initializing SprocketService...");
        sprockets = new ArrayList<Sprocket>();
        for (int i = 0; i < 10; i++) {
            sprockets.add(new Sprocket(i, "Sprocket: " + i));    
        }
    }

    public List<Sprocket> list() {
        logger.debug("Listing available sprockets.");
        return sprockets;
    }

    public Sprocket find(int sprocketId) {
        logger.debug("Retrieving sprocket for id: {}", sprocketId);
        for (Sprocket s : sprockets) {
            if (s.getId() == sprocketId)
                return s;
        }
        return null;
    }
}