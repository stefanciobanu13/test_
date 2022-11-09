package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.controller.dao.service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.controller.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class DemoController {

    private EmployeeService employeeService;

    public DemoController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "addEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/list";
    }


    @GetMapping("/showFormForUpdate")
        public String update(@RequestParam("employeeId") int theId, Model model){

        model.addAttribute(employeeService.findById(theId));

       return "addEmployee";
    }


    @RequestMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){

        employeeService.deleteById(theId);

        return "redirect:/list";
    }


}












