

<%@page import="com.employes.utils.EmployesConstantes"%>
<%-- 
    Document   : index
    Created on : 8 juil. 2016, 09:51:05
    Author     : Jacques Augustin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>Login - Gestion Employés</title>
    </head>
    <body>      



        <div class="container" style="margin-top:30px">
            <div class="col-md-4">

                <span style="color:red">         
                    
                    <%--
<%
                        if (request.getAttribute(EmployesConstantes.CLE_MESSAGE_ERREUR) != null) {
                            out.println(request.getAttribute(EmployesConstantes.CLE_MESSAGE_ERREUR));
                        }%>
                    --%>
                    
                </span>

                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
                    <div class="panel-body">
                        <form action="Controleur" method="POST">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Login" name="login" autofocus="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Mot de passe" name="mdp" type="password">
                                </div>

                                <input type="submit" name="action" value="Login" class="btn btn-primary"/>
                                <input type='submit' name="bouton" value="Quitter" class="btn btn-primary" onclick="twFermer ()"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
