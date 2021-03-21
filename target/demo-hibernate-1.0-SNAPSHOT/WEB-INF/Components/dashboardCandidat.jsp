<%@ page import="com.models.Compte" %>
<%@ page import="com.models.Candidat" %>
<%@ page import="com.models.Cv" %>
<%
    Compte compte = (Compte) request.getAttribute("compte");
    Candidat candidat = (Candidat) request.getAttribute("candidat");
    Cv cv = (Cv) request.getAttribute("cv");
%>

<!-- Content -->
<div class="page-content bg-white">
    <div class="section-full content-inner overlay-white-middle">
        <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="row">
                    <!-- Information -->
                    <div class="col-md-4">
                        <img class="img-fluid" src="https://via.placeholder.com/250x250" />

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
                        <h1><%= candidat.getCivilite() + " " + candidat.getNomComplet() %></h1>
                        <h3><%= candidat.getTitreEmploi() %></h3>
                    </div>

                    <!-- CV -->
                    <% if (cv == null) { %>
                        <div class="text-center">
                            No cv found
                            <a href="/dashboard" class="site-button"><i class="fa fa-lock"></i> Ajouter CV</a>
                        </div>
                    <% } else { %>
                        <div>
                            CV info :
                        </div>
                    <% } %>
                </div>
            </div>
            <div class="col-md-2">
                <div>
                    <a href="/dashboard" class="site-button"><i class="fa fa-lock"></i> Voir offres</a>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>