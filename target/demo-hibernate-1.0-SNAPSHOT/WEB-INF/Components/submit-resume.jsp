<%@ page import="java.util.List" %>
<%@ page import="com.models.*" %><%
    Cv cv = (Cv) request.getAttribute("cv");
    List<Formation> formations = (List<Formation>) request.getAttribute("formations");
    List<Experience> experiences = (List<Experience>) request.getAttribute("experiences");
    List<Projet> projets = (List<Projet>) request.getAttribute("projets");
    List<Competence> competences = (List<Competence>) request.getAttribute("competences");
%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-dark" style="background-image:url(images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Ajouter cv</h1>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <ul class="list-inline">
                        <li><a href="#">Home</a></li>
                        <li>Ajouter cv</li>
                    </ul>
                </div>
                <!-- Breadcrumb row END -->
            </div>
        </div>
    </div>
    <!-- inner page banner END -->
    <!-- contact area -->
    <div class="content-block">
        <!-- Submit Resume -->
        <div class="section-full bg-white submit-resume content-inner-2">
            <div class="container">
                <form action="/ajouter-cv" method="post" id="ajouterCvForm">
                    <div class="form-group">
                        <label>Description</label>
                        <textarea name="desc" class="form-control" placeholder="Description"><% if (cv != null) { %><%=cv.getDescription()%><% } %></textarea>
                    </div>

                    <!-- formations -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Les formations</h5>
                        <button id="addFormation" type="button" class="site-button">Ajouter</button>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <div id="formationsList">
                        <% if (formations != null) { %>
                                <% for (Formation f:formations) { %>
                                    <div class="item">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Etablissement</label>
                                                    <input type="text" name="nom_etablissement" class="form-control" value="<%=f.getNomEtablissement()%>" placeholder="Etablissement">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Diplome</label>
                                                    <input type="text" name="nom_diplome" class="form-control" value="<%=f.getNomDiplome()%>" placeholder="Diplome">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Start date</label>
                                                    <input type="date" name="start_date" class="form-control" value="<%=f.getStartDate()%>" placeholder="Start date">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>End date</label>
                                                    <input type="date" name="end_date" class="form-control" value="<%=f.getEndDate()%>" placeholder="End date">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <% } %>
                        <% } else { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Etablissement</label>
                                        <input type="text" name="nom_etablissement" class="form-control" placeholder="Etablissement">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Diplome</label>
                                        <input type="text" name="nom_diplome" class="form-control" placeholder="Diplome">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Start date</label>
                                        <input type="date" name="start_date" class="form-control" placeholder="Start date">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>End date</label>
                                        <input type="date" name="end_date" class="form-control" placeholder="End date">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>

                    <!-- experiences -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Les experiences</h5>
                        <button id="addExperience" type="button" class="site-button">Ajouter</button>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <div id="experiencesList">
                        <% if (experiences != null) { %>
                        <% for (Experience e:experiences) { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Entreprise</label>
                                        <input type="text" name="nom_entreprise" value="<%=e.getNomEntreprise()%>" class="form-control" placeholder="Entreprise">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Sujet</label>
                                        <input type="text" name="sujet" value="<%=e.getSujet()%>" class="form-control" placeholder="Sujet">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Start date</label>
                                        <input type="date" name="start_date" value="<%=e.getStartDate()%>" class="form-control" placeholder="Start date">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>End date</label>
                                        <input type="date" name="end_date" value="<%=e.getEndDate()%>" class="form-control" placeholder="End date">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                        <% } else { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Entreprise</label>
                                        <input type="text" name="nom_entreprise" class="form-control" placeholder="Entreprise">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Sujet</label>
                                        <input type="text" name="sujet" class="form-control" placeholder="Sujet">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Start date</label>
                                        <input type="date" name="start_date" class="form-control" placeholder="Start date">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>End date</label>
                                        <input type="date" name="end_date" class="form-control" placeholder="End date">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>


                    <!-- projets -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Les projets</h5>
                        <button id="addProjet" type="button" class="site-button">Ajouter</button>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <div id="projetsList">
                        <% if (projets != null) { %>
                        <% for (Projet p:projets) { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Titre</label>
                                        <input type="text" name="titre_projet" value="<%=p.getTitre()%>" class="form-control" placeholder="Titre">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Type</label>
                                        <input type="text" name="type_projet" value="<%=p.getType()%>" class="form-control" placeholder="Type">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                        <% } else { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Titre</label>
                                        <input type="text" name="titre_projet" class="form-control" placeholder="Titre">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Type</label>
                                        <input type="text" name="type_projet" class="form-control" placeholder="Type">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>

                    <!-- competences -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="font-weight-600 mb-0">Les competences</h5>
                        <button id="addCompetence" type="button" class="site-button">Ajouter</button>
                    </div>
                    <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                    <div id="competencesList">
                        <% if (competences != null) { %>
                        <% for (Competence c:competences) { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Nom</label>
                                        <input type="text" name="nom_competence" value="<%=c.getNom()%>" class="form-control" placeholder="Nom">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Niveau</label>
                                        <input type="number" name="niveau_competence" value="<%=c.getNiveau()%>" class="form-control" placeholder="Niveau">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                        <% } else { %>
                        <div class="item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Nom</label>
                                        <input type="text" name="nom_competence" class="form-control" placeholder="Nom">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Niveau</label>
                                        <input type="number" name="niveau_competence" class="form-control" placeholder="Niveau">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>

                    <!-- final inputs -->
                    <input type="hidden" name="formationsInput" id="formationsInput">
                    <input type="hidden" name="experiencesInput" id="experiencesInput">
                    <input type="hidden" name="projetsInput" id="projetsInput">
                    <input type="hidden" name="competencesInput" id="competencesInput">

                    <button type="submit" class="site-button">Submit</button>
                </form>
            </div>
        </div>
        <!-- Submit Resume END -->
    </div>
</div>
<!-- Content END-->
<button class="scroltop fa fa-chevron-up"></button>
</div>