<%@ page import="com.models.Offre" %>
<%@ page import="com.models.Postulation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.Services.CandidatService" %>
<%
    Offre offre = (Offre) request.getAttribute("offre");

    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
    int etat = (int) request.getAttribute("etat");

    List<Postulation> listPostulations = (List<Postulation>) request.getAttribute("listPostulations");

    List<String> competences = Arrays.asList(offre.getCompetencesRequises().split("\\s*,\\s*"));

    String departement = "departement";


    // check if user logged in
    String userEmail = null;
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("user")) {
            userEmail = cookies[i].getValue();
            break;
        }
    }


%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">
                    Offer Details
                </h1>
                <div class="breadcrumb-row">
                    <ul class="list-inline">
                        <li><a href="/">Home</a></li>
                        <li><a href="/voir-offres">Browse Jobs</a></li>
                        <li><a href="/offre-details?id=<%=offre.getId()%>>">offre°<%=offre.getId()%></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- inner page banner END -->
    <!-- contact area -->
    <div class="content-block">
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

        <!-- Job Detail -->
        <div class="section-full content-inner-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="sticky-top">
                            <div class="row">
                                <div class="col-lg-12 col-md-6">
                                    <div class="m-b30">
                                        <img src="images/blog/grid/pic1.jpg" alt="">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-6">
                                    <div class="widget bg-white p-lr20 p-t20  widget_getintuch radius-sm">
                                        <h4 class="text-black font-weight-700 p-t10 m-b15">Job Details</h4>
                                        <ul>
                                            <li>
                                                <a href="/recruteur?id=<%=offre.getIdRecruteur()%>">
                                                    <i class="ti-location-pin"></i><strong
                                                        class="font-weight-700 text-black">Recruteur</strong>
                                                    <span class="text-black-light">Voir recruteur</span>
                                                </a>
                                            </li>

                                            <li><i class="ti-location-pin"></i><strong
                                                    class="font-weight-700 text-black">Emplacement</strong><span
                                                    class="text-black-light">
                                                <%= offre.getEmplacement().toUpperCase() %>
                                            </span>
                                            </li>
                                            <li><i class="ti-money"></i><strong class="font-weight-700 text-black">Salaire et primes</strong>
                                                <%= offre.getSalairePrimes() %> DH
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="job-info-box">
                            <h3 class="m-t0 m-b10 font-weight-700 title-head">
                                <%= offre.getTitre() %>
                            </h3>
                            <ul class="job-info">
                                <li><strong>Job Title : </strong> <%=offre.getMetier()%></li>
                                <li><strong>Creation Date : </strong> <%= offre.getDateCreation().toString().subSequence(0,10) %></li>
                                <li><i class="ti-location-pin text-black m-r5"></i> <%= offre.getEmplacement().toUpperCase() %></li>
                            </ul>

                            <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                            <p><%=offre.getDescription()%></p>

                            <h5 class="font-weight-600">Competences requires</h5>
                            <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>
                            <ul class="list-num-count no-round">
                                <%
                                    for (String c:competences) { %>
                                        <li><%=c%></li>
                                    <% } %>
                            </ul>

                            <h5 class="font-weight-600">Postuler</h5>
                            <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                            <form action="/postulation" method="post">
                                <input type="hidden" value="<%=offre.getId()%>" name="offreId">
                                <textarea class="form-control mb-3" name="body" placeholder="Body"></textarea>
                                <% if (userEmail != null) { %>
                                <% switch (etat){
                                    case 0://delete%>
                                        <a href="/offre-details/delete?id=<%=offre.getId()%>" class="site-button">Delete</a>
                                        <% break;
                                    case 1://apply%>
                                        <button type="submit" class="site-button my-3">Apply Now</button>
                                        <%break;
                                    case 2://employee see an offer doesn't belong to him%>
                                        <div class="row my-3">
                                            <div class="col-sm-12">
                                                <div class="app-alert alert alert-success alert-dismissible fade show" role="alert">
                                                    You haven't permission to edit or delete this offer
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <%break;
                                    case 3://already applied%>
                                        <div class="row my-3">
                                            <div class="col-sm-12">
                                                <div class="app-alert alert alert-success alert-dismissible fade show" role="alert">
                                                    You already applied to edit or delete this job offer
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <%break;
                                }%>
                                <%}else{%>
                                <div class="row my-3">
                                    <div class="col-sm-12">
                                        <div class="app-alert alert alert-success alert-dismissible fade show" role="alert">
                                            Sign-Up or Login to apply to this offer
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <%}%>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Job Detail -->

        <% if (userEmail == null || etat==3 || etat==1) {%>
            <div hidden="true" class="section-full content-inner-1">
        <%}else{%>
            <div class="section-full content-inner-1">
        <%}%>



                <!-- Postulations -->
                <!-- Our Jobs -->
        <div class="section-full content-inner">
            <div class="container">
                <div class="row">
                    <%for (Postulation p:listPostulations) { %>
                    <div class="col-xl-3 col-lg-6 col-md-6">
                        <div class="m-b30 blog-grid">
                            <div class="dez-post-media dez-img-effect ">
                                <a href="/candidat?id=<%=p.getIdCandidat()%>">

                                    <% try {
                                        if (CandidatService.getCandidatById(p.getIdCandidat()).getPhotoUrl().isEmpty() || CandidatService.getCandidatById(p.getIdCandidat()).getPhotoUrl() == null) { %>
                                        <img src="../../Assets/images/logo/icon1.png" alt="">
                                        <% } else { %>

                                    <img src="../../Assets/photos/<%=CandidatService.getCandidatById(p.getIdCandidat()).getPhotoUrl()%>">
                                        <% }
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    } %>


                                </a>
                            </div>
                            <div class="dez-info p-a20 border-1">
                                <div class="dez-post-title ">
                                    <h5 class="post-title"><a href="/candidat?id=<%=p.getIdCandidat()%>"><%=CandidatService.getCandidatById(p.getIdCandidat()).getNomComplet()%></a></h5>
                                </div>
                                <div class="dez-post-text">
                                    <p><%=CandidatService.getCandidatById(p.getIdCandidat()).getCivilite()%></p>
                                </div>
                                <div class="dez-post-readmore">
                                    <a href="/recrutement?idRecruteur=<%=offre.getIdRecruteur()%>&idCandidat=<%=p.getIdCandidat()%>&offreId=<%=p.getIdOffre()%>" class="site-button outline">Recruter ce candidat<i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% } %>

            </div>
        </div>
        <!-- Our Jobs END -->
    </div>
</div>
<!-- Content END-->

<button class="scroltop fa fa-chevron-up"></button>
</div>
