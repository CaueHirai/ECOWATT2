/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
 * @author caueh
 */
@WebServlet(name = "BuscarEquipamento", urlPatterns = {"/BuscarEquipamento"})
public class BuscarEquipamento extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONObject json;
        Equipamento equipamento;
        EquipamentoDAO eqpDAO;
        int codigo;
        
        try{
            json = new JSONObject();
            equipamento = new Equipamento();
            eqpDAO = new EquipamentoDAO();
            codigo = Integer.parseInt(request.getParameter("codigo"));
            
            equipamento = eqpDAO.get(codigo);
            
            JSONObject eqJson = new JSONObject();

            eqJson.put("codigo", codigo);
            eqJson.put("nome", equipamento.getNome());
            eqJson.put("horasDiarias", equipamento.getHorasDiarias());
            eqJson.put("potencia", equipamento.getPotencia());
            eqJson.put("diasMes", equipamento.getDiasMes());
            eqJson.put("qtde", equipamento.getQtde());
            eqJson.put("imagem", equipamento.getImagem());
            eqJson.put("bloco", equipamento.getBloco());
            eqJson.put("sala", equipamento.getSala());

            json.put("equipamento", eqJson);
            out.println(json.toString());
            
        }catch (Exception ex) {
            JSONObject erro = new JSONObject();
            erro.put("erro", ex.getMessage());
            out.print(erro.toString());
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
