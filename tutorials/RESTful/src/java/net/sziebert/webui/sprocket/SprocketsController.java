package net.sziebert.webui.sprocket;

import net.sziebert.service.SprocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SprocketsController {

    private static final Logger logger = LoggerFactory.getLogger(SprocketsController.class);

    private final SprocketService service;

    @Autowired
    public SprocketsController(SprocketService service) {
        this.service = service;
    }

    @RequestMapping("/sprockets/list.do")
    public String list(ModelMap model) {
        logger.debug("Rendering sprockets list.");
        model.addAttribute("sprockets", service.list());
        return "sprocket/list";
    }

    @RequestMapping("/sprocket/display.do")
    public String display(@RequestParam("sprocketId") int sprocketId, ModelMap model) {
        logger.debug("Displaying sprocket with id: {}", sprocketId);
        model.addAttribute("sprocket", service.find(sprocketId));
        return "sprocket/display";
    }

    @RequestMapping("/sprocket/edit.do")
    public String edit(@RequestParam("sprocketId") int sprocketId, ModelMap model) {
        logger.debug("Editing sprocket with id: {}", sprocketId);
        model.addAttribute("sprocket", service.find(sprocketId));
        return "sprocket/edit";
    }
}