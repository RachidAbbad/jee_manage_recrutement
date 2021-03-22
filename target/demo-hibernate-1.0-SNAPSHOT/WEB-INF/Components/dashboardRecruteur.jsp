<%@ page import="com.models.Compte" %>
<%@ page import="com.models.Recruteur" %>
<%
    Compte compte = (Compte) request.getAttribute("compte");
    Recruteur recruteur = (Recruteur) request.getAttribute("recruteur");
%>

<!-- Content -->
<!-- Content -->
<div class="page-content bg-white">
    <div class="section-full content-inner overlay-white-middle">
        <div class="container">
            <div class="row">
                <div class="col-md-10">
                    <div class="row">
                        <!-- Information -->
                        <div class="col-md-4">
                            <ul>
                                <li>
                                    <% if (compte.getVerified() == 1) { %>
                                    <span class="badge badge-primary">Verified</span>
                                    <% } else { %>
                                    <span class="badge badge-light">Not verified</span>
                                    <% } %>
                                </li>
                                <li><%= compte.getVille() %></li>
                                <li><%= compte.getNumTel() %></li>
                            </ul>
                        </div>
                        <div class="col-md-8">
                            <h1><%= recruteur.getNom() %></h1>
                            <h3><%= recruteur.getDescription() %></h3>
                            <h3><%= recruteur.getSiteweb() %></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div>
                        <a href="/ajouter-offre" class="site-button"><i class="fa fa-lock"></i> Ajouter offre</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>