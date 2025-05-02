package com.bnkgroup.dokhan_group.controllers;

import com.bnkgroup.dokhan_group.models.entities.Cards;
import com.bnkgroup.dokhan_group.models.services.implementations.CardsService;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CardsServlet", value = "/cards/list")
public class CardsServlet extends HttpServlet {

    @Inject
    private CardsService cardsService;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        // نمونه‌سازی سرویس (در صورت استفاده از DI با Spring یا CDI، این بخش متفاوت است)
//        cardsService = new CardsService();
//    }
    @Override
    public void init() throws ServletException {
        super.init();
        cardsService = CDI.current().select(CardsService.class).get();  // دستی گرفتن Bean از CDI
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // دریافت لیست حساب‌ها
        List<Cards> cardsList = cardsService.find();
        for (Cards crd : cardsList) {
            crd.getClass().getName(); // force fetch
        }

        // ارسال به JSP
        request.setAttribute("cardsList", cardsList);
        request.getRequestDispatcher("/WEB-INF/views/cards/list.jsp").forward(request, response);
    }
}

