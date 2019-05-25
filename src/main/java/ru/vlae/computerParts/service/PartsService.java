package ru.vlae.computerParts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.vlae.computerParts.dao.PartsDAO;
import ru.vlae.computerParts.model.Part;


import java.util.List;

@Service
public class PartsService {
    private PartsDAO partsDAO;

    @Autowired
    @Transactional
    public void setPartsDAO(PartsDAO partsDAO) {
        this.partsDAO = partsDAO;
    }

    @Transactional
    public int partCount(String needSelect, String searchName) {
        return partsDAO.partCount(needSelect, searchName);
    }

    @Transactional
    public int countOfComputers() {
        return partsDAO.countOfComputers();
    }

    @Transactional
    public List<Part> allParts(int page, String needSelect, String searchName) {
        return partsDAO.allParts(page, needSelect, searchName);
    }
    @Transactional
    public boolean isPartExist(String searchName){
        return partsDAO.isPartExist(searchName);
    }

    @Transactional
    public void add(Part part) {
        partsDAO.add(part);
    }

    @Transactional
    public void delete(Part part) {
        partsDAO.delete(part);
    }

    @Transactional
    public void edit(Part part) {
        partsDAO.edit(part);
    }

    @Transactional
    public Part getById(int id) {
        return partsDAO.getById(id);
    }
}
