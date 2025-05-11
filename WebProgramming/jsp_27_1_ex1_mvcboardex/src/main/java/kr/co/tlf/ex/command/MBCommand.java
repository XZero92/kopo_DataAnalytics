package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MBCommand {
    // Define the methods that will be implemented by the command classes
    void execute(HttpServletRequest request, HttpServletResponse response);
}
