package com.bnkgroup.dokhan_group.controllers;

import com.bnkgroup.dokhan_group.models.services.implementations.AccountsService;
import com.bnkgroup.dokhan_group.models.services.implementations.CardsService;
import com.bnkgroup.dokhan_group.models.services.implementations.PeopleService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DemoDataServlet", value = "/demo/load-sample-data")
public class DemoDataServlet extends HttpServlet {

    @Inject
    private PeopleService peopleService;

    @Inject
    private AccountsService accountsService;

    @Inject
    private CardsService cardsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        peopleService.makeSamplePeople();
        accountsService.makeSampleAccounts();
        cardsService.makeSampleCards();

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
