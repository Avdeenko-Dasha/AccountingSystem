package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;
import com.avdeenko.factory.AutoHouseCreationFactory;
import com.avdeenko.model.model.House;
import com.avdeenko.service.HouseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateHouseAuto implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        HouseService houseService = HouseService.getHouseService();

        int numberOfHouse = Integer.parseInt(request.getParameter("numberOfHouse"));
        int check = houseService.checkingHouseNumber(numberOfHouse);

        if(check == -1){
            House house = new AutoHouseCreationFactory(numberOfHouse).createHouse();
            session.setAttribute("house", house);
            request.getRequestDispatcher("jsp/display-house-step-2.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("jsp/house-found.jsp").forward(request, response);
        }
    }
}
