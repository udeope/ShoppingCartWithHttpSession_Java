package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        //creamos o recuperamos el objeto httpsession
        HttpSession sesion = request.getSession();

        //Recuperamos lal lista de articulos previos si es que existe
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");//lo casteamos a una lista generica

        //Verificamos si la lista de articulos existe
        if (articulos == null) {
            articulos = new ArrayList<>();//iniciamos nuestra lista
            sesion.setAttribute("articulos", articulos);//agregamos la lista a la sesion
        }

        //Procesamos el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");//el parametro del name del html

        //revisamos y agregamos el articulo nuevo
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }

        try (
                //Imprimimos la lista de articulos
                PrintWriter out = response.getWriter()) {
            out.print("<h1>Lista de Articulos</h1>");
            out.print("<br/>");
            //iteramos todos los articulos
            for (String articulo : articulos) {
                out.print("<li>" + articulo + "</li>");
            }
            //agregamos un link para regresar al inicio
            out.print("<br/>");
            out.print("<a href='/EjemploCarritoCompras'>Regresar al inicio</a>");
        }
    }
}
