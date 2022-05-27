package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;
import com.avdeenko.model.model.House;
import com.avdeenko.service.HouseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DisplayHouse implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        HouseService houseService = HouseService.getHouseService();

        int numberOfHouse = Integer.parseInt(request.getParameter("numberOfHouse"));
        int check = houseService.checkingHouseNumber(numberOfHouse);

        if(check == -1){
            request.getRequestDispatcher("jsp/house-not-found.jsp").forward(request, response);
        } else {
            House house = houseService.findHouse(numberOfHouse);
            session.setAttribute("house", house);
            request.getRequestDispatcher("jsp/display-house-step-2.jsp").forward(request, response);
        }
    }
}
