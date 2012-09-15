package com.siplan.newpolice.controllers;

import com.siplan.newpolice.dal.MessageRepository;
import com.siplan.newpolice.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-10
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class HelloWorldController {

    private static final Logger log = Logger.getLogger(HelloWorldController.class.getName());

    @Resource(name = "messageRepository")
    private MessageRepository messageRepository;

    @RequestMapping(value="/saveText", method=RequestMethod.GET)
	public ModelAndView usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
        return new ModelAndView("index");
	}

    @RequestMapping(value = "/formText", method = RequestMethod.GET)
    public ModelAndView helloWorldGet(HttpServletRequest request,
                       HttpServletResponse response,Model model) throws IOException {
        // delete
        if (request.getParameter("id") != null) {
            deleteMessage(request);

        }

        // get
        Collection<Message> messages = messageRepository.getAll();
        request.setAttribute("messages", messages);

        return new ModelAndView("index");
    }


    @RequestMapping(value = "/")
    public ModelAndView indexGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // get
        Collection<Message> messages = messageRepository.getAll();
        request.setAttribute("messages", messages);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/formText", method = RequestMethod.POST)
    public ModelAndView helloWorldPost(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        log.info("doPost");

        // create
        createMessage(request);
        return new ModelAndView(new RedirectView("/formText"));
    }


    protected void createMessage(HttpServletRequest request) {
        String text = request.getParameter("text");
            log.info("creating message with text: " + text);

        Message message = new Message();
        message.setText(text);
        messageRepository.create(message);
    }

    protected void deleteMessage(HttpServletRequest request) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
            log.info("deleting message with id: " + id);
        messageRepository.deleteById(id);
    }

    /**
     * Forwards request and response to given path. Handles any exceptions
     * caused by forward target by printing them to logger.
     *
     * @param request
     * @param response
     * @param path
     */
    protected void forward(HttpServletRequest request,
                           HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        } catch (Throwable tr) {
                log.warning("Cought Exception: " + tr.getMessage());
                log.info("StackTrace");
        }
    }
}
