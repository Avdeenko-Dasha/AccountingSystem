package com.avdeenko.controller;

import com.avdeenko.controller.command.Command;
import com.avdeenko.controller.command.CommandProvider;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class MainController extends HttpServlet {
    public static final String COMMAND = "command";
    private final CommandProvider commandProvider = new CommandProvider();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String currentCommandName;
        Command command;

        request.setCharacterEncoding("UTF-8");
        currentCommandName = request.getParameter(COMMAND);

        command = commandProvider.getCommand(currentCommandName);

        command.execute(request, response);
    }

    @Override
    public void destroy() {
    }

}
