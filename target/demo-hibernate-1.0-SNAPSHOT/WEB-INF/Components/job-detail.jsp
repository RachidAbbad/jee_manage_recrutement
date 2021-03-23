<%@ page import="com.models.Offre" %>
<%@ page import="com.models.Postulation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %><%
    Offre offre = (Offre) request.getAttribute("offre");

    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");

    List<Postulation> listPostulations = (List<Postulation>) request.getAttribute("listPostulations");

    List<String> competences = Arrays.asList(offre.getCompetencesRequises().split("\\s*,\\s*"));

    String departement = "departement";

%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">
                    Offre
                </h1>
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
                                                <%= offre.getEmplacement() %>
                                            </span>
                                            </li>
                                            <li><i class="ti-money"></i><strong class="font-weight-700 text-black">Salaire et primes</strong>
                                                <%= offre.getSalairePrimes() %>
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
                                <li><strong><%=departement%></strong> <%=offre.getMetier()%></li>
                                <li><strong>Date de creation:</strong> <%= offre.getDateCreation() %></li>
                                <li><i class="ti-location-pin text-black m-r5"></i> <%= offre.getEmplacement() %></li>
                            </ul>

                            <div class="dez-divider divider-2px bg-gray-dark mb-4 mt-0"></div>

                            <p><%=offre.getDescription()%></p>

                            <h5 class="font-weight-600">Competences requises</h5>
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
                                <button type="submit" class="site-button">Apply This Job</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Job Detail -->


        <!-- Postulations -->
        <div class="section-full content-inner-1">
            <div class="container">
                <%
                    for (Postulation p:listPostulations) { %>
                    <div class="border p-4 mb-3">
                        <a href="/candidat?id=<%=p.getIdCandidat()%>"><i class="ti-user"></i> Candidat</a>
                        <p>
                            <%= p.getBody() %>
                        </p>
                        <a href="/recrutement?idRecruteur=<%=offre.getIdRecruteur()%>&idCandidat=<%=p.getIdCandidat()%>&offreId=<%=p.getIdOffre()%>" class="site-button">Recruter ce candidat</a>
                    </div>
                <% } %>
            </div>
        </div>
        <!-- Postulations -->


        <!-- Our Jobs -->
        <div class="section-full content-inner">
            <div class="container">
                <div class="row">
                    <div class="col-xl-3 col-lg-6 col-md-6">
                        <div class="m-b30 blog-grid">
                            <div class="dez-post-media dez-img-effect "><a href="#"><img src="images/blog/grid/pic1.jpg"
                                                                                         alt=""></a></div>
                            <div class="dez-info p-a20 border-1">
                                <div class="dez-post-title ">
                                    <h5 class="post-title"><a href="#">Title of blog post</a></h5>
                                </div>
                                <div class="dez-post-meta ">
                                    <ul>
                                        <li class="post-date"><i class="ti-location-pin"></i> London</li>
                                        <li class="post-author"><i class="ti-user"></i>By <a href="#">Jone</a></li>
                                    </ul>
                                </div>
                                <div class="dez-post-text">
                                    <p>All the Lorem Ipsum generators on the Internet tend to repeat predefined
                                        chunks.</p>
                                </div>
                                <div class="dez-post-readmore">
                                    <a href="#" class="site-button outline">Apply Now <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6">
                        <div class="m-b30 blog-grid">
                            <div class="dez-post-media dez-img-effect "><a href="#"><img src="images/blog/grid/pic2.jpg"
                                                                                         alt=""></a></div>
                            <div class="dez-info p-a20 border-1">
                                <div class="dez-post-title ">
                                    <h5 class="post-title"><a href="#">Title of blog post</a></h5>
                                </div>
                                <div class="dez-post-meta ">
                                    <ul>
                                        <li class="post-date"><i class="ti-location-pin"></i> London</li>
                                        <li class="post-author"><i class="ti-user"></i>By <a href="#">Jone</a></li>
                                    </ul>
                                </div>
                                <div class="dez-post-text">
                                    <p>All the Lorem Ipsum generators on the Internet tend to repeat predefined
                                        chunks.</p>
                                </div>
                                <div class="dez-post-readmore">
                                    <a href="#" class="site-button outline">Apply Now <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6">
                        <div class="m-b30 blog-grid">
                            <div class="dez-post-media dez-img-effect "><a href="#"><img src="images/blog/grid/pic3.jpg"
                                                                                         alt=""></a></div>
                            <div class="dez-info p-a20 border-1">
                                <div class="dez-post-title ">
                                    <h5 class="post-title"><a href="#">Title of blog post</a></h5>
                                </div>
                                <div class="dez-post-meta ">
                                    <ul>
                                        <li class="post-date"><i class="ti-location-pin"></i> London</li>
                                        <li class="post-author"><i class="ti-user"></i>By <a href="#">Jone</a></li>
                                    </ul>
                                </div>
                                <div class="dez-post-text">
                                    <p>All the Lorem Ipsum generators on the Internet tend to repeat predefined
                                        chunks.</p>
                                </div>
                                <div class="dez-post-readmore">
                                    <a href="#" class="site-button outline">Apply Now <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6">
                        <div class="m-b30 blog-grid">
                            <div class="dez-post-media dez-img-effect "><a href="#"><img src="images/blog/grid/pic4.jpg"
                                                                                         alt=""></a></div>
                            <div class="dez-info p-a20 border-1">
                                <div class="dez-post-title ">
                                    <h5 class="post-title"><a href="#">Title of blog post</a></h5>
                                </div>
                                <div class="dez-post-meta ">
                                    <ul>
                                        <li class="post-date"><i class="ti-location-pin"></i> London</li>
                                        <li class="post-author"><i class="ti-user"></i>By <a href="#">Jone</a></li>
                                    </ul>
                                </div>
                                <div class="dez-post-text">
                                    <p>All the Lorem Ipsum generators on the Internet tend to repeat predefined
                                        chunks.</p>
                                </div>
                                <div class="dez-post-readmore">
                                    <a href="#" class="site-button outline">Apply Now <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Our Jobs END -->
    </div>
</div>
<!-- Content END-->

<button class="scroltop fa fa-chevron-up"></button>
</div>
