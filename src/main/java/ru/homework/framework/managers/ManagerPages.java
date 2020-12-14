package ru.homework.framework.managers;

import ru.homework.framework.pages.MortgagePage;
import ru.homework.framework.pages.StartPage;

public class ManagerPages {
    private static ManagerPages managerPages;

    StartPage startPage;

    MortgagePage mortgagePage;


    private ManagerPages(){
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }
}
