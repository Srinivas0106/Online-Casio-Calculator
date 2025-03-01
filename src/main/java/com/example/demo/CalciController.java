package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalciController {
    @Autowired
    CalsiService calculatorService;
    
    @GetMapping("/")
    String home() {
        return "calsi";
    }
    
    @RequestMapping("/calculate")
    String calculate(@RequestParam ("num1") double n1,
                    @RequestParam ("num2") double n2,
                    @RequestParam ("operation") String operation,
                    Model model) { 
        
        double result = 0;

        switch (operation) {
            case "add":
                result = calculatorService.addition(n1, n2);
                break;
            case "sub":
                result = calculatorService.subtraction(n1, n2);
                break;
            case "mul":
                result = calculatorService.multiplication(n1, n2);
                break;
            case "div":
                if (n2 != 0) {
                    result = calculatorService.division(n1, n2);
                } else {
                    model.addAttribute("error", "Division by zero is not allowed.");
                    model.addAttribute("num1", n1);
                    model.addAttribute("num2", n2);
                    return "resultPage"; // Changed to "resultPage"
                }
                break;
            default:
                model.addAttribute("error", "Invalid operation.");
                return "resultPage"; // Changed to "resultPage"
        }

        model.addAttribute("num1", n1);
        model.addAttribute("num2", n2);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        return "resultPage";
    }
}