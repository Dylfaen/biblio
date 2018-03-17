package controller;

import model.DAO.OeuvreDAO;
import model.beans.Oeuvre;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmpruntAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OeuvreDAO oeuvreDAO = new OeuvreDAO();
        ArrayList<Oeuvre> oeuvres = oeuvreDAO.getOeuvres();

        JsonArrayBuilder oeuvresBuilder = Json.createArrayBuilder();

        for(Oeuvre oeuvre: oeuvres) {
            oeuvresBuilder.add(oeuvre.toJson());
        }

        JsonObject json = Json.createObjectBuilder().add("oeuvres", oeuvresBuilder).build();

        PrintWriter out = response.getWriter();

        System.out.println(json.toString());

        out.print(json.toString());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OeuvreDAO oeuvreDAO = new OeuvreDAO();
        ArrayList<Oeuvre> oeuvres = oeuvreDAO.getOeuvres();

        JsonArrayBuilder oeuvresBuilder = Json.createArrayBuilder();

        for(Oeuvre oeuvre: oeuvres) {
            oeuvresBuilder.add(oeuvre.toJson());
        }

        JsonObject json = Json.createObjectBuilder().add("oeuvres", oeuvresBuilder).build();

        PrintWriter out = response.getWriter();

        System.out.println(json.toString());

        out.print(json.toString());
    }
}
