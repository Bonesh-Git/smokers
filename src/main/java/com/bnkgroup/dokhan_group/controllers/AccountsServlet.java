package com.bnkgroup.dokhan_group.controllers;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import com.bnkgroup.dokhan_group.models.services.implementations.AccountsService;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountsServlet", value = "/accounts/list")
public class AccountsServlet extends HttpServlet {

    @Inject
    private AccountsService accountsService;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        // نمونه‌سازی سرویس (در صورت استفاده از DI با Spring یا CDI، این بخش متفاوت است)
//        //AccountsRepository accountsRepository = new AccountsRepository();
//        accountsService = new AccountsService();
//        //accountsService.setAccountsRepository(accountsRepository);
//    }
    @Override
    public void init() throws ServletException {
        super.init();
        accountsService = CDI.current().select(AccountsService.class).get();  // دستی گرفتن Bean از CDI
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // دریافت لیست حساب‌ها
        List<Accounts> accountList = accountsService.find();
        for (Accounts acc : accountList) {
            acc.getPeople().getPeopleName(); // force fetch
        }

        // ارسال به JSP
        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("/WEB-INF/views/accounts/list.jsp").forward(request, response);
    }
}

