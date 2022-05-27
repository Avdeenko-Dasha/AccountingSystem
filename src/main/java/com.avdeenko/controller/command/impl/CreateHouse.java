package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;
import com.avdeenko.service.HouseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateHouse implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("numberOfHouse", request.getParameter("numberOfHouse"));
        session.setAttribute("numberOfFloors", request.getParameter("numberOfFloors"));
        session.setAttribute("numberOfApartments", request.getParameter("numberOfApartments"));

        HouseService houseService = HouseService.getHouseService();
        int numberOfHouse = Integer.parseInt(request.getParameter("numberOfHouse"));
        int check = houseService.checkingHouseNumber(numberOfHouse);

        if(check == -1){
            request.getRequestDispatcher("jsp/create-apartments.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("jsp/house-found.jsp").forward(request,response);
        }
    }
}
