<%@ page import="com.models.Compte" %>
<%@ page import="com.models.Recruteur" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.Postulation" %>
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

                    <div class="text-center mt-4 d-flex justify-content-center">
                        <button id="dashboard-change-compte-action" class="site-button mr-2">Changer mon compte</button>
                    </div>

                </div>
                <div class="col-md-8">
                    <h1 class="mb-1"><%= recruteur.getNom() %></h1>
                    <p style="opacity: 0.7;"><%= recruteur.getDescription() %></p>
                </div>
            </div>

            <!-- Change Information -->
            <form id="dashboard-change-info" action="/dashboard" method="post" enctype="multipart/form-data" class="row mb-5">
                <input type="hidden" name="type_compte" value="<%= compte.getTypeCompte() %>" />

                <div class="col-md-4">
                    <div class="photo-profile-box mb-3">
                        <label for="uploadPhoto" class="edit-layer">
                            <i class="ti-image"></i>
                        </label>

                        <input type="file" id="uploadPhoto" name="photo">
                        <% if (recruteur.getLogoUrl().isEmpty() || recruteur.getLogoUrl() == null) { %>
                        <img class="img-fluid d-block mx-auto mb-4" src="https://via.placeholder.com/250x250" />
                        <% } else { %>
                        <img class="img-fluid d-block mx-auto mb-4" src="<%=request.getContextPath()%>Assets/photos/<%=recruteur.getLogoUrl()%>" />
                        <% } %>
                    </div>

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

                        <li class="list-group-item">
                            <input name="siteweb" class="form-control" type="text" value="<%= recruteur.getSiteweb() %>" />
                        </li>
                    </ul>

                    <div class="text-center mt-4 d-flex justify-content-center">
                        <button id="dashboard-voir-compte-action" class="site-button mr-2" type="button">Voir mon compte</button>
                        <button class="site-button mr-2" type="submit">Save</button>
                    </div>

                </div>
                <div class="col-md-8">
                    <div class="mb-2">
                        <input name="nom" class="form-control text-capitalize" type="text" value="<%= recruteur.getNom() %>" />
                    </div>

                    <div style="opacity: 0.7;">
                        <textarea name="desc" style="min-height: 150px;" class="form-control"><%= recruteur.getDescription() %></textarea>
                    </div>

                </div>
            </form>

            <div class="d-flex justify-content-between align-items-center mb-4 mt-5">
                <h3 class="mb-0">Mes offres</h3>
                <a href="/ajouter-offre" class="site-button ml-2">Nouveau offre</a>
            </div>

            <ul class="post-job-bx">
                <% if (mesOffres != null && mesOffres.size() > 0) { %>
                <% for (Offre offre:mesOffres) { %>
                <li>
                    <a href="/offre-details?id=<%=offre.getId()%>">
                        <div class="d-flex m-b30">
                            <div class="job-post-company">
                                <span>
                                    <img src="../../Assets/images/logo/icon1.png" />
                                </span>
                            </div>
                            <div class="job-post-info">
                                <h4><%= offre.getTitre() %></h4>
                                <ul>
                                    <li><i class="fa fa-map-marker"></i> <%= offre.getEmplacement() %></li>
                                    <li><i class="fa fa-bookmark-o"></i> <%= offre.getTypeContrat() %></li>
                                    <li><i class="fa fa-clock-o"></i> Published <%= offre.getDateCreation().toString().subSequence(0,10) %></li>
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
                                <span><%= offre.getSalairePrimes() %> DH</span>
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