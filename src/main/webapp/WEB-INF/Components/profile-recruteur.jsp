<%@ page import="com.models.Compte" %>
<%@ page import="com.models.Recruteur" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.Offre" %>
<%
    Compte compte = (Compte) request.getAttribute("compte");
    Recruteur recruteur = (Recruteur) request.getAttribute("recruteur");

    List<Offre> mesOffres = (List<Offre>) request.getAttribute("listOffres");

    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
%>

<!-- Content -->
<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">
                    <%= recruteur.getNom() %>
                </h1>
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

            <div class="row">
                <!-- Information -->
                <div class="col-md-4">
                    <div class="photo-profile-box">
                        <% if (recruteur.getLogoUrl().isEmpty() || recruteur.getLogoUrl() == null) { %>
                        <img class="img-fluid d-block mx-auto mb-4" src="https://via.placeholder.com/250x250" />
                        <% } else { %>
                        <img class="img-fluid d-block mx-auto mb-4" src="<%=request.getContextPath()%>Assets/photos/<%=recruteur.getLogoUrl()%>" />
                        <% } %>
                    </div>

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
                        <li class="list-group-item"><i class="fa fa-window-maximize" aria-hidden="true"></i> <%= recruteur.getSiteweb() %></li>
                    </ul>
                </div>
                <div class="col-md-8">
                    <h1 class="mb-1"><%= recruteur.getNom() %></h1>
                    <p style="opacity: 0.7;"><%= recruteur.getDescription() %></p>

                    <hr>


                    <h3>Les offres</h3>
                    <ul class="post-job-bx">
                        <% if (mesOffres != null && mesOffres.size() > 0) { %>
                        <% for (Offre offre:mesOffres) { %>
                        <li>
                            <a href="/offre-details?id=<%=offre.getId()%>">
                                <div class="d-flex m-b30">
                                    <div class="job-post-company">
                                        <span>
                                            <% if (recruteur.getLogoUrl().isEmpty() || recruteur.getLogoUrl() == null) { %>
                                            <img src="https://via.placeholder.com/250x250" />
                                            <% } else { %>
                                            <img src="<%=request.getContextPath()%>Assets/photos/<%=recruteur.getLogoUrl()%>" />
                                            <% } %>
                                        </span>
                                    </div>
                                    <div class="job-post-info">
                                        <h4><%= offre.getTitre() %></h4>
                                        <ul>
                                            <li><i class="fa fa-map-marker"></i> <%= offre.getEmplacement() %></li>
                                            <li><i class="fa fa-bookmark-o"></i> <%= offre.getTypeContrat() %></li>
                                            <li><i class="fa fa-clock-o"></i> Published <%= offre.getDateCreation() %></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="d-flex">
                                    <div class="job-time mr-auto">
                                        <span><%= offre.getMetier() %></span>
                                        <span>
                                                        <% if (offre.getEtat() == 0) { %>
                                                            Closed
                                                        <% } else { %>
                                                            Opened
                                                        <% } %>
                                                    </span>
                                    </div>
                                    <div class="salary-bx">
                                        <span><%= offre.getSalairePrimes() %></span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <% } %>
                        <% } else { %>
                        <li>No offres found</li>
                        <% } %>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>