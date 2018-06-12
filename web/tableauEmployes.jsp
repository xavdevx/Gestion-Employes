<%-- 
    Document   : tableauEmployes
    Created on : 3 nov. 2016, 11:13:04
    Author     : Jacques Augustin
--%>

<%@page import="com.employes.utils.EmployesConstantes"%>
<%@page import="com.employes.modele.EmployeBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>Liste des Employés</title>
    </head>
    <body>

        <div class="container" >
            <div class="row col-md-6 col-md-offset-0 custyle">

                <form method="post" action="Controleur" name="suppression">
                    <table class="table table-striped custab">

                        <thead>
                        <h1>Liste des employés</h1>
                        <tr class="text-center">
                            <th>Sél</th>
                            <th>NOM</th>
                            <th>PRENOM</th>
                            <th>TEL DOMICILE</th>
                            <th>TEL PORTABLE</th>
                            <th>TEL PRO</th>
                            <th>ADRESSE</th>
                            <th>CODE POSTAL</th>
                            <th>VILLE</th>
                            <th>EMAIL</th>
                        </tr>
                        </thead>

                        </tr>
                        <%
                            ArrayList<EmployeBean> listeEmployes = (ArrayList<EmployeBean>) request.getAttribute(EmployesConstantes.CLE_LISTE_EMPLOYES);
                            for (EmployeBean employe : listeEmployes) {
                        %>
                        <tr>
                            <td><input type="radio" name="idEmploye" value="<%=employe.getId()%>"></td>
                            <td><% out.println(employe.getNom()); %></td>
                            <td><% out.println(employe.getPrenom()); %></td>
                            <td><% out.println(employe.getTelDomicile()); %></td>
                            <td><% out.println(employe.getTelPortable()); %></td>
                            <td><% out.println(employe.getTelPro()); %></td>
                            <td><% out.println(employe.getAdresse()); %></td>
                            <td><% out.println(employe.getCodePostal()); %></td>
                            <td><% out.println(employe.getVille()); %></td>
                            <td><% out.println(employe.getEmail()); %></td>

                        </tr>
                        <% }%>
                    </table>
                    <input type="submit" name="action" value="Supprimer" class="btn btn-primary"/>
                    <input type="submit" name="action" value="Details" class="btn btn-primary"/>
                    <input type='submit' name="bouton" value="Quitter" class="btn btn-primary" onclick="twFermer ()"/>
                </form>
            </div>
        </div>         
    </body>
</html>
