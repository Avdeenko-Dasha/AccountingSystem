package com.avdeenko.controller.command.impl;

import com.avdeenko.controller.command.Command;
import com.avdeenko.factory.ManualHouseCreationFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateResidents implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        List<Integer> residents = new ArrayList<>();
        int allApartments = Integer.parseInt(String.valueOf(session.getAttribute("allApartments")));

        for(int i = 1; i <= allApartments; i++){
            residents.add(Integer.valueOf(request.getParameter("residentApartment" + i)));
            session.removeAttribute("residentApartment" + i);
        }

        int numberOfHouse = Integer.parseInt(String.valueOf(session.getAttribute("numberOfHouse")));
        int numberOfApartments = Integer.parseInt(String.valueOf(session.getAttribute("numberOfApartments")));
        int numberOfFloors = Integer.parseInt(String.valueOf(session.getAttribute("numberOfFloors")));
        List<Double> square = (List<Double>) session.getAttribute("square");

        new ManualHouseCreationFactory(numberOfHouse, numberOfFloors, numberOfApartments,
            square, residents).createHouse();

        session.removeAttribute("numberOfApartments");
        session.removeAttribute("numberOfFloors");
        session.removeAttribute("numberOfHouse");
        session.removeAttribute("square");
        session.removeAttribute("allApartments");

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
