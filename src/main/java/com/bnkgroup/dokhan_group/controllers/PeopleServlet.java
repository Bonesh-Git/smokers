package com.bnkgroup.dokhan_group.controllers;

import com.bnkgroup.dokhan_group.models.entities.People;
import com.bnkgroup.dokhan_group.models.services.implementations.PeopleService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

//@RequestScoped
@WebServlet(name = "PeopleServlet", value = "/people/list")
public class PeopleServlet extends HttpServlet {

    @Inject
    private PeopleService peopleService;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        // نمونه‌سازی سرویس (در صورت استفاده از DI با Spring یا CDI، این بخش متفاوت است)
//        peopleService = new PeopleService();
//    }
    @Override
    public void init() throws ServletException {
        super.init();
        peopleService = CDI.current().select(PeopleService.class).get();  // دستی گرفتن Bean از CDI
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // دریافت لیست حساب‌ها
        List<People> peopleList = peopleService.find();
        for (People plp : peopleList) {
            plp.getClass().getName(); // force fetch
        }

        // ارسال به JSP
        request.setAttribute("peopleList", peopleList);
        request.getRequestDispatcher("/WEB-INF/views/people/list.jsp").forward(request, response);
    }
}

