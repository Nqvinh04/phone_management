package com.codegym.controller;

import com.codegym.model.SmartPhone;
import com.codegym.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/smartphones")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createSmartphone(){
        ModelAndView mav = new ModelAndView("phones/new_phone");
        mav.addObject("phones", new SmartPhone());
        return mav;
    }

    @RequestMapping(value="/createNewPhone", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SmartPhone createSmartPhone(@RequestBody SmartPhone smartPhone){
        return smartphoneService.save(smartPhone);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<SmartPhone> allPhones(){
        return smartphoneService.findAll();
    }

    @GetMapping("")
    public ModelAndView allPhonesPage(){
        ModelAndView modelAndView = new ModelAndView("phones/all_phone");

        modelAndView.addObject("allPhones", allPhones());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SmartPhone deleteSmartPhone(@PathVariable Integer id){
     return smartphoneService.remove(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSmartphonePage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("phones/edit");
        SmartPhone smartphone = smartphoneService.findById(id);
        mav.addObject("phones", smartphone);
        return mav;
    }

    @RequestMapping(value ="/edit/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SmartPhone EditSmartPhone(@PathVariable int id, @RequestBody SmartPhone smartPhone){
        smartPhone.setId(id);
        return smartphoneService.save(smartPhone);
    }
}
