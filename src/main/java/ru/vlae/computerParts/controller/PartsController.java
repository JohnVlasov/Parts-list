package ru.vlae.computerParts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.vlae.computerParts.model.Part;
import ru.vlae.computerParts.service.PartsService;

import java.util.List;

@Controller
public class PartsController {

    private PartsService partsService;
    private int page; // текущая страница отображаемая mainparts
    private int partCount; // общее количество строк в таблице part
    private int pagesCount; // количество страниц
    private int countOfComputers; // количество возможных компьютеров
    private String needSelect = "all"; // переключатель ("all","need","notNeed")
    private String searchName = ""; // строка поиска по названию детали

    @Autowired
    public void setPartsService(PartsService partsService) {
        this.partsService = partsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allParts(@RequestParam(defaultValue = "1") int page) {
        this.page = page;
        List<Part> parts = partsService.allParts(page, needSelect, searchName); //получаем список строк для страницы page
        partCount = partsService.partCount(needSelect, searchName); // получаем количество записей соотв. отбору
        pagesCount = (partCount + 9) / 10; // вычисляем количество страниц
        countOfComputers = partsService.countOfComputers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainparts");
        modelAndView.addObject("searchName", searchName);
        modelAndView.addObject("needSelect", needSelect);
        modelAndView.addObject("page", page);
        modelAndView.addObject("partList", parts);
        modelAndView.addObject("partCount", partCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("countOfComputers", countOfComputers);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partsService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("part", part);
        modelAndView.setViewName("editpart");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addpart");
        return modelAndView;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    //получаем значение переключателя и заново формируем список
    public ModelAndView Select(@RequestParam("needSelect") String needSelect) {
        this.needSelect = needSelect;
        return allParts(1);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    //получаем шаблон для поиска и заново формируем список по шаблону
    public ModelAndView Search(@RequestParam String searchName) {
        this.searchName = searchName.trim();
        return allParts(1);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute("part") Part part) {
        partsService.edit(part);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        needSelect = "all";
        return modelAndView;
    }
    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public ModelAndView back(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    // добавляем элемент и показываем последнюю страницу списка сновым элементом
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        needSelect = "all";
        searchName = "";
        partCount = partsService.partCount(needSelect, searchName); //количество строк соответствующих отбору
        pagesCount = (partCount + 9) / 10;

        if (part.getName().equals("")) {
            modelAndView.setViewName("addpart");
            modelAndView.addObject("error", "Введите наименовение детали");
        } else {
            if (partsService.isPartExist(part.getName())) {
                modelAndView.setViewName("addpart");
                modelAndView.addObject("error", "Такая деталь уже есть в базе данных");
            } else {
                partsService.add(part);
                partCount = partsService.partCount(needSelect, searchName); //количество строк соответствующих отбору
                pagesCount = (partCount + 9) / 10;
                modelAndView.setViewName("redirect:/?page=" + pagesCount); //возврат на главную страницу (последняя страница списка)
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePart(@PathVariable("id") int id) {
        Part part = partsService.getById(id);
        partsService.delete(part);
        partCount = partsService.partCount(needSelect, searchName); //количество строк соответствующих отбору
        pagesCount = (partCount + 9) / 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + pagesCount);
        return modelAndView;
    }
}
