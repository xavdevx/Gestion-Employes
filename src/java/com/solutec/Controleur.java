/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutec;


import com.employes.modele.EmployeBean;
import com.employes.modele.EmployesPersistance;
import com.employes.modele.UtilisateurBean;
import com.employes.modele.UtilisateurPersistance;
import com.employes.utils.EmployesConstantes;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;



/**
 *
 * @author esic
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
          // DEBUT BLOC DE DECLARATION
        String loginForm = request.getParameter(EmployesConstantes.FRM_LOGIN);
        String mdpForm = request.getParameter(EmployesConstantes.FRM_MDP);
        String action = request.getParameter(EmployesConstantes.ACTION);

        ArrayList<EmployeBean> listeEmployes;
        EmployeBean employe;
        UtilisateurBean userBean;
        ResultSet rs;

        String idEmploye = EmployesConstantes.FRM_ID_EMPL_SELECT;

        EmployesPersistance employeBD = new EmployesPersistance();
        UtilisateurPersistance utilisateurBD = new UtilisateurPersistance();
        // FIN BLOC DE DECLARATION

        if (action != null) {

            if (action.equals(EmployesConstantes.ACTION_LOGIN)) {
                //Si le nom d'utilisateur et le mot de passe sont vide, renvoyer vers l'index
                //avec un message d'erreur.
                if (loginForm != null && mdpForm != null) {
                    userBean = new UtilisateurBean();

                    if (loginForm.isEmpty() || mdpForm.isEmpty()) {
                        request.setAttribute(EmployesConstantes.CLE_MESSAGE_ERREUR, EmployesConstantes.ERREUR_SAISIE_VIDE);
                        request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                    } else {
                        try {
                            //Récupération des utilisateurs
                            rs = utilisateurBD.getUtilisateurs();
                            //Récupération du login et du mot de passe
                            while (rs.next()) {
                                userBean.setLogin(rs.getString("LOGIN"));
                                userBean.setMdp(rs.getString("MDP"));
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }

                        //Si le login et le mot de passe entrées correspondent aux login et mot
                        //de passe présents en base, créer un utilisateur et l'envoyer
                        //vers la page de tableauEmployes.
                        if (userBean.getLogin().equals(loginForm) && userBean.getMdp().equals(mdpForm)) {
                            listeEmployes = employeBD.getListeEmployes();
                            request.setAttribute(EmployesConstantes.CLE_LISTE_EMPLOYES, listeEmployes);
                            request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
                        } //Sinon envoyer vers la page d'accueil avec un message d'erreur.
                        else {
                            request.setAttribute(EmployesConstantes.CLE_MESSAGE_ERREUR, EmployesConstantes.ERREUR_INFOS_CONN_KO);
                            request.getRequestDispatcher(EmployesConstantes.PAGE_INDEX).forward(request, response);
                        }
                    }
                }
            } else if (action.equals(EmployesConstantes.ACTION_SUPPRIMER)) {
                if (request.getParameter(idEmploye) != null) {
                    int idClientASupprimer = Integer.parseInt(request.getParameter(idEmploye));
                    employeBD.supprimerEmployeParId(idClientASupprimer);
                    listeEmployes = employeBD.getListeEmployes();
                    request.setAttribute(EmployesConstantes.CLE_LISTE_EMPLOYES, listeEmployes);
                    request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
                }
            } else if (action.equals(EmployesConstantes.ACTION_MODIFIER)) {
                HttpSession session = request.getSession();
                employe = (EmployeBean) session.getAttribute(EmployesConstantes.CLE_EMPLOYE_SELECT);

                employe.setAdresse(request.getParameter(EmployesConstantes.CHAMP_ADRESSE));
                employe.setCodePostal(request.getParameter(EmployesConstantes.CHAMP_CODEPOSTAL));
                employe.setEmail(request.getParameter(EmployesConstantes.CHAMP_EMAIL));
                employe.setNom(request.getParameter(EmployesConstantes.CHAMP_NOM));
                employe.setPrenom(request.getParameter(EmployesConstantes.CHAMP_PRENOM));
                employe.setTelDomicile(request.getParameter(EmployesConstantes.CHAMP_TELDOMICILE));
                employe.setTelPortable(request.getParameter(EmployesConstantes.CHAMP_TELPORTABLE));
                employe.setTelPro(request.getParameter(EmployesConstantes.CHAMP_TELPRO));
                employe.setVille(request.getParameter(EmployesConstantes.CHAMP_VILLE));

                employeBD.modifierEmploye(employe);

                listeEmployes = employeBD.getListeEmployes();
                request.setAttribute(EmployesConstantes.CLE_LISTE_EMPLOYES, listeEmployes);
                request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
            } else if (action.equals(EmployesConstantes.ACTION_DETAILS)) {
                if (request.getParameter(idEmploye) != null) {
                    int idEmployeSelect = Integer.parseInt(request.getParameter(idEmploye));
                    employe = employeBD.getEmployeParId(idEmployeSelect);
                    HttpSession session = request.getSession();
                    session.setAttribute(EmployesConstantes.CLE_EMPLOYE_SELECT, employe);
                    request.getRequestDispatcher(EmployesConstantes.PAGE_DETAIL_EMPLOYE).forward(request, response);

                } 
            } else if (action.equals(EmployesConstantes.ACTION_VOIR_LISTE)) {
                listeEmployes = employeBD.getListeEmployes();
                request.setAttribute(EmployesConstantes.CLE_LISTE_EMPLOYES, listeEmployes);
                request.getRequestDispatcher(EmployesConstantes.PAGE_TOUS_LES_EMPLOYES).forward(request, response);
            }
              
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
