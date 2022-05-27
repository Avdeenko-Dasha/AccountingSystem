package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateApartments implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        List<Double> square = new ArrayList<>();
        int numberOfApartments = Integer.parseInt(String.valueOf(session.getAttribute("numberOfApartments")));
        int numberOfFloors = Integer.parseInt(String.valueOf(session.getAttribute("numberOfFloors")));
        for(int i = 1; i <= numberOfApartments; i++){
            square.add(Double.valueOf(request.getParameter("squareApartment" + i)));
            session.removeAttribute("squareApartment" + i);
        }
        session.setAttribute("square", square);
        session.setAttribute("allApartments", numberOfApartments*numberOfFloors);
        request.getRequestDispatcher("jsp/create-residents.jsp").forward(request,response);
    }
}
