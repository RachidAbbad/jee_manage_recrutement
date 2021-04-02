<%
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle bg-pt" style="background-image:url(images/banner/bnr2.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Register</h1>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <ul class="list-inline">
                        <li><a href="#">Home</a></li>
                        <li>Register</li>
                    </ul>
                </div>
                <!-- Breadcrumb row END -->
            </div>
        </div>
    </div>
    <!-- inner page banner END -->
    <!-- contact area -->
    <div class="section-full content-inner shop-account">
        <!-- Product -->
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h3 class="font-weight-700 m-t0 m-b20">Create An Account</h3>
                </div>
            </div>

            <!-- Choose account type -->
            <ul class="nav nav-pills justify-content-center mb-3" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Candidat</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Recruteur</a>
                </li>
            </ul>

            <div class="row">
                <div class="col-sm-12">
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
                </div>
            </div>

            <form id="login" action="/register" method="post">
                <input type="hidden" value="Candidat" id="typeCompte" name="type_compte">
                <div class="row">
                    <div class="col-md-6">
                        <div class="p-a30 border-1">
                            <h4 class="font-weight-700">INFORMATIONS GENERALES</h4>
                            <p class="font-weight-600">
                                If you have an account with us, please <a href="/login">log in</a> .
                            </p>
                            <div class="form-group">
                                <label class="font-weight-700">Telephone *</label>
                                <input name="register_numtel"  class="form-control" placeholder="Telephone"
                                       type="text">
                            </div>
                            <div class="form-group">
                                <label class="font-weight-700">Ville *</label>
                                <select name="register_ville" >
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
                                <label class="font-weight-700">E-MAIL *</label>
                                <input name="register_email"  class="form-control" placeholder="Email"
                                       type="email">
                            </div>
                            <div class="form-group">
                                <label class="font-weight-700">MOT DE PASSE *</label>
                                <input name="register_password"  class="form-control " placeholder="Mot de passe"
                                       type="password">
                            </div>
                            <div class="text-left">
                                <button class="site-button button-lg outline outline-2">CREATE</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="tab-content" id="myTabContent">
                            <!-- Candidat -->
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="p-a30 border-1">
                                    <h4 class="font-weight-700">CANDIDAT</h4>
                                    <div class="form-group">
                                        <label class="font-weight-700">Nom complet *</label>
                                        <input name="register_nomcomplet"  class="form-control" placeholder="Nom complet"
                                               type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="font-weight-700">Titre d'emploi *</label>
                                        <input name="register_titreemploi"  class="form-control" placeholder="Titre d'emploi"
                                               type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="font-weight-700">Civilite *</label>
                                        <select name="register_civilite" >
                                            <option value="monsieur">Monsieur</option>
                                            <option value="madame">Madame</option>
                                            <option value="mademoiselle">Mademoiselle</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <!-- Recruteur -->
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div class="p-a30 border-1">
                                    <h4 class="font-weight-700">RECRUTEUR</h4>
                                    <div class="form-group">
                                        <label class="font-weight-700">Nom *</label>
                                        <input name="register_nom_rec" class="form-control" placeholder="Nom"
                                               type="text">
                                    </div>
                                    <div class="form-group">
                                        <label class="font-weight-700">Description *</label>
                                        <textarea name="register_desc_rec" class="form-control" placeholder="Description"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label class="font-weight-700">Siteweb *</label>
                                        <input name="register_siteweb" class="form-control" placeholder="Siteweb"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- Product END -->
    </div>
    <!-- contact area  END -->
</div>
<!-- Content END-->

<button class="scroltop fa fa-chevron-up"></button>
</div>

