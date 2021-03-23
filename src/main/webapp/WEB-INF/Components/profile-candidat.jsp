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
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">
                    <%= candidat.getNomComplet() %>
                </h1>
            </div>
        </div>
    </div>
    <!-- inner page banner END -->

    <div class="section-full content-inner overlay-white-middle">
        <div class="container">
            <div class="row">
                <!-- Information -->
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
                </div>
                <div class="col-md-8">
                    <h1 class="mb-1" style="text-transform: capitalize;"><%= candidat.getCivilite() + " " + candidat.getNomComplet() %></h1>
                    <p style="opacity: 0.5;"><%= candidat.getTitreEmploi() %></p>

                    <!-- CV -->
                    <div class="border p-5">
                        <% if (cv == null) { %>
                        <div class="text-center">
                            <h3 class="mb-2 display-4" style="opacity: 0.5;"><i class="fa fa-file-text-o" aria-hidden="true"></i></h3>
                            <h4 class="mb-4">No cv found</h4>
                        </div>
                        <% } else { %>
                        <div>
                            CV info :
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>