package com.university.uch_university.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(WebRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code", WebRequest.SCOPE_REQUEST);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("statusCode", statusCode);

            String message = "";
            switch (statusCode) {
                case 400:
                    message = "Неверный запрос.";
                    break;
                case 404:
                    message = "Страница не найдена.";
                    break;
                case 500:
                    message = "Внутренняя ошибка сервера.";
                    break;
                default:
                    message = "Произошла ошибка.";
                    break;
            }
            model.addAttribute("message", message);
        }
        return "error";
    }
}