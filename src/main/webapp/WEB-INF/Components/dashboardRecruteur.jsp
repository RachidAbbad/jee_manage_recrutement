<%@ page import="com.models.Compte" %>
<%@ page import="com.models.Recruteur" %>
<%
    Compte compte = (Compte) request.getAttribute("compte");
    Recruteur recruteur = (Recruteur) request.getAttribute("recruteur");
%>

<!-- Content -->
<div class="page-content bg-white">
    Type: <%= compte.getTypeCompte() %>
    Recruteur Nom: <%= recruteur.getNom() %>
</div>