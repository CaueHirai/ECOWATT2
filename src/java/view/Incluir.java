/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view;

import controller.EquipamentoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Equipamento;
import org.json.JSONObject;

/**
 *
 * @author aluno
 */
@WebServlet(name = "Incluir", urlPatterns = {"/Incluir"})
public class Incluir extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        
        String s = "Não encontrado";
        EquipamentoDAO objDAO;
        Equipamento obj;
        JSONObject json;
        
        try{
            json = new JSONObject();
            obj = new Equipamento();
            objDAO = new EquipamentoDAO();
            
            obj.setPotencia(request.getParameter("txtPotencia").toLowerCase().trim());
            obj.setNome(request.getParameter("txtNome").toLowerCase().trim());
            obj.setHorasDiarias(request.getParameter("txtHd").toLowerCase().trim());
            obj.setDiasMes(request.getParameter("txtDm").toLowerCase().trim());
            obj.setQtde(request.getParameter("txtQtde").toLowerCase().trim());
            
            //obj.setSala(request.getParameter("txtSala").toLowerCase().trim());
            //obj.setBloco(request.getParameter("txtBloco").toLowerCase().trim());
            objDAO.gravar(obj);
            
        }catch(Exception ex){
            json = new JSONObject();
            json.put("nome", ex.getMessage());
            out.println(json.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
