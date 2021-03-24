<%@ page import="java.util.List" %>
<%@ page import="com.models.*" %>
<%
    Compte compte = (Compte) request.getAttribute("compte");
    Candidat candidat = (Candidat) request.getAttribute("candidat");
    Cv cv = (Cv) request.getAttribute("cv");
    List<Formation> formations = (List<Formation>) request.getAttribute("formations");
    List<Experience> experiences = (List<Experience>) request.getAttribute("experiences");
    List<Projet> projets = (List<Projet>) request.getAttribute("projets");
    List<Competence> competences = (List<Competence>) request.getAttribute("competences");

    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Mon Compte</h1>
            </div>
        </div>
    </div>
    <!-- inner page banner END -->

    <div class="section-full content-inner overlay-white-middle">
        <div class="container">

            <!-- Success Message -->
            <% if (successMessage != null) { %>
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
            <%}%>

            <!-- Error Message -->
            <% if (errorMessage != null) { %>
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
            <%}%>

            <!-- Information -->
            <div id="dashboard-voir-info" class="row">
                <div class="col-md-4">
                    <img class="img-fluid rounded-circle d-block mx-auto mb-4" src="https://via.placeholder.com/250x250" />

                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <% if (compte.getVerified() == 1) { %>
                            <span class="badge badge-primary"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Verified</span>
                            <% } else { %>
                            <span class="badge badge-light"><i class="fa fa-times-circle-o" aria-hidden="true"></i> Not verified</span>
                            <% } %>
                        </li>
                        <li class="list-group-item" style="text-transform: capitalize;"><i class="fa fa-building-o" aria-hidden="true"></i> <%= compte.getVille() %></li>
                        <li class="list-group-item"><i class="fa fa-phone" aria-hidden="true"></i> <%= compte.getNumTel() %></li>
                    </ul>

                    <div class="text-center mt-4 d-flex justify-content-center">
                        <button id="dashboard-change-compte-action" class="site-button mr-2">Changer mon compte</button>
                    </div>
                </div>
                <div class="col-md-8">
                    <h1 class="mb-1" style="text-transform: capitalize;"><%= candidat.getCivilite() + " " + candidat.getNomComplet() %></h1>
                    <p style="opacity: 0.5;"><%= candidat.getTitreEmploi() %></p>
                </div>
            </div>

            <!-- Change Information -->
            <form id="dashboard-change-info" action="/dashboard" method="post" class="row">

                <input type="hidden" name="type_compte" value="<%= compte.getTypeCompte() %>" />

                <div class="col-md-4">
                    <img class="img-fluid rounded-circle d-block mx-auto mb-4" src="https://via.placeholder.com/250x250" />

                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <input style="text-transform: capitalize;" name="ville" class="form-control" type="text" value="<%= compte.getVille() %>" />
                        </li>
                        <li class="list-group-item">
                            <input name="num_tel" class="form-control" type="text" value="<%= compte.getNumTel() %>" />
                        </li>
                        <li class="list-group-item">
                            <input name="password" class="form-control" type="password" placeholder="New Password" />
                        </li>
                    </ul>

                    <div class="text-center mt-4 d-flex justify-content-center">
                        <button id="dashboard-voir-compte-action" class="site-button mr-2" type="button">Voir mon compte</button>
                        <button class="site-button mr-2" type="submit">Save</button>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="mb-1">
                        <select name="civilite">
                            <option class="text-capitalize" selected><%= candidat.getCivilite() %></option>
                        </select>
                        <input name="nom_complet" class="form-control text-capitalize" type="text" value="<%= candidat.getNomComplet() %>" />
                    </div>

                    <input style="opacity: 0.5;" name="titre_emploi" class="form-control text-capitalize mb-3" type="text" value="<%= candidat.getTitreEmploi() %>" />
                </div>
            </form>

            <!-- CV -->
            <div class="border p-5 mt-5">
                <% if (cv == null) { %>
                <div class="text-center">
                    <h3 class="mb-2 display-4" style="opacity: 0.5;"><i class="fa fa-file-text-o" aria-hidden="true"></i></h3>
                    <h4 class="mb-4">No cv found</h4>
                    <a href="/ajouter-cv" class="site-button"><i class="fa fa-plus"></i> Ajouter CV</a>
                </div>
                <% } else { %>
                <div>
                    <!-- description -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Description</h5>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <p><%=cv.getDescription()%></p>

                    <!-- formations -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Formations</h5>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <!-- experiences -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Experiences</h5>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <!-- projets -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Projets</h5>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <!-- competences -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Competences</h5>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>