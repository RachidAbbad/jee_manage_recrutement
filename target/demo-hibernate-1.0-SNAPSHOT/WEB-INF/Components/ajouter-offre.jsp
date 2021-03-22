<%@ page import="com.models.Departement" %>
<%@ page import="java.util.List" %><%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");

    List<Departement> departements = (List<Departement>) request.getAttribute("listDepartements");
%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Ajouter offre</h1>
            </div>
        </div>
    </div>
    <!-- inner page banner END -->

    <div class="container content-inner-2">
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

        <form action="/ajouter-offre" method="post">
            <div class="form-group">
                <label>Titre</label>
                <input type="text" name="offre_titre" class="form-control" placeholder="Titre">
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea name="offre_desc" class="form-control" placeholder="Description"></textarea>
            </div>
            <div class="form-group">
                <label>Emplacement</label>
                <select name="offre_emplacement">
                    <option>New York</option>
                    <option>London</option>
                    <option>Los Angeles</option>
                </select>
            </div>
            <div class="form-group">
                <label>Type de contrat</label>
                <select name="offre_type_contrat">
                    <option>New York</option>
                    <option>London</option>
                    <option>Los Angeles</option>
                </select>
            </div>
            <div class="form-group">
                <label>Metier</label>
                <input type="text" name="offre_metier" class="form-control" placeholder="Metier">
            </div>
            <div class="form-group">
                <label>Salaire et primes</label>
                <input type="number" name="offre_salaire" class="form-control" placeholder="Salaire et primes">
            </div>
            <div class="form-group">
                <label>Competences requises</label>
                <input type="text" name="offre_competences" class="form-control" placeholder="Competences requises">
            </div>
            <div class="form-group">
                <label>Departement</label>
                <%--
                <select name="departement_id">
                    <% for (Departement departement:departements) { %>
                    <option value="<%= departement.getId() %>">
                        <%= departement.getNom() %>
                    </option>
                    <% } %>
                </select>
                --%>

                <select name="departement_id">
                    <option value="1">New York</option>
                    <option value="2">London</option>
                    <option value="3">Los Angeles</option>
                </select>
            </div>
            <button type="submit" class="site-button">Ajouter offre</button>
        </form>
    </div>
</div>