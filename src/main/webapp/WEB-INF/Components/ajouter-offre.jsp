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
                    <option value="">Ville</option>
                    <option value="agadir">Agadir</option>
                    <option value="casablanca">Casablanca</option>
                    <option value="el-jadida">El Jadida</option>
                    <option value="fes">Fes</option>
                    <option value="kenitra">Kenitra</option>
                    <option value="marrakech">Marrakech</option>
                    <option value="meknes">Meknes</option>
                    <option value="mohammedia">Mohammedia</option>
                    <option value="rabat">Rabat</option>
                    <option value="sale">Sale</option>
                    <option value="tanger">Tanger</option>
                    <option value="temara">Temara</option>
                </select>
            </div>
            <div class="form-group">
                <label>Type de contrat</label>
                <select name="offre_type_contrat">
                    <option>CDI</option>
                    <option>CDD</option>
                    <option>CTT</option>
                    <option>CUI</option>
                    <option>CAE</option>
                    <option>CIE</option>
                    <option>Contrat d’apprentissage</option>
                    <option>Contrat de professionnalisation</option>
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
                <select name="departement_id">
                    <% for (Departement departement:departements) { %>
                    <option value="<%= departement.getId() %>">
                        <%= departement.getNom() %>
                    </option>
                    <% } %>
                </select>
            </div>
            <button type="submit" class="site-button">Ajouter offre</button>
        </form>
    </div>
</div>