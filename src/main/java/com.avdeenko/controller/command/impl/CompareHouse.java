package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;
import com.avdeenko.service.HouseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompareHouse implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        HouseService houseService = HouseService.getHouseService();

        int numberOfHouse1 = Integer.parseInt(request.getParameter("numberOfHouse1"));
        int numberOfHouse2 = Integer.parseInt(request.getParameter("numberOfHouse2"));
        int check1 = houseService.checkingHouseNumber(numberOfHouse1);
        int check2 = houseService.checkingHouseNumber(numberOfHouse2);

        if(check1 == -1 || check2 == -1){
            request.getRequestDispatcher("jsp/house-not-found.jsp").forward(request, response);
        } else {
            String compare = houseService.compareHouses(numberOfHouse1, numberOfHouse2);
            session.setAttribute("compare", compare);
            request.getRequestDispatcher("jsp/display-compare.jsp").forward(request, response);
        }
    }
}
