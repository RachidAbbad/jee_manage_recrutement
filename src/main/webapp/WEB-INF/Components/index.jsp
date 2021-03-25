<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.Offre" %>
<%
    List<Offre> offreList = (List<Offre>) request.getAttribute("listOffres");
%>

<!-- Content -->
<div class="page-content">
    <!-- Section Banner -->
    <div class="dez-bnr-inr dez-bnr-inr-md" style="background-image:url(../../Assets/images/main-slider/slide2.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry align-m ">
                <div class="find-job-bx">
                    <p class="site-button button-sm">Find Jobs, Employment & Career Opportunities</p>
                    <h2>Search Between More Them <br/> <span class="text-primary">50,000</span> Open Jobs.</h2>
                    <form class="dezPlaceAni">
                        <div class="row">
                            <div class="col-lg-4 col-md-6">
                                <div class="form-group">
                                    <label>Job Title, Keywords, or Phrase</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="">
                                        <div class="input-group-append">
                                            <span class="input-group-text"><i class="fa fa-search"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="form-group">
                                    <label>City, State or ZIP</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="">
                                        <div class="input-group-append">
                                            <span class="input-group-text"><i class="fa fa-map-marker"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="form-group">
                                    <select>
                                        <option>Select Sector</option>
                                        <option>Construction</option>
                                        <option>Corodinator</option>
                                        <option>Employer</option>
                                        <option>Financial Career</option>
                                        <option>Information Technology</option>
                                        <option>Marketing</option>
                                        <option>Quality check</option>
                                        <option>Real Estate</option>
                                        <option>Sales</option>
                                        <option>Supporting</option>
                                        <option>Teaching</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-2 col-md-6">
                                <button type="submit" class="site-button btn-block">Find Job</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Section Banner END -->
    <!-- About Us -->
    <div class="section-full job-categories content-inner-2 bg-white"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/pattern/pic1.html);">
        <div class="container">
            <div class="section-head d-flex head-counter clearfix">
                <div class="mr-auto">
                    <h2 class="m-b5">Departements</h2>
                    <h6 class="fw3">20+ Catetories work wating for you</h6>
                </div>
                <div class="head-counter-bx">
                    <h2 class="m-b5 counter"><%=offreList.size()%></h2>
                    <h6 class="fw3">Offre publies</h6>
                </div>
                <div class="head-counter-bx">
                    <h2 class="m-b5 counter">4500</h2>
                    <h6 class="fw3">Entreprises</h6>
                </div>
                <div class="head-counter-bx">
                    <h2 class="m-b5 counter">1500</h2>
                    <h6 class="fw3">Candidats</h6>
                </div>
            </div>
            <div class="row sp20">
                <c:forEach items="${listDepartements}" var="departement">
                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="icon-bx-wraper">
                            <div class="icon-content">
                                <div class="icon-md text-primary m-b20"><i class="${departement.getIcon()}"></i></div>
                                <a href="#" class="dez-tilte">${departement.getNom()}</a>
                                <p class="m-a0"></p>
                                <div class="rotate-icon"><i class="ti-location-pin"></i></div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="col-lg-12 text-center m-t30">
                    <button class="site-button radius-xl">All Categories</button>
                </div>
            </div>
        </div>
    </div>
    <!-- About Us END -->
    <!-- Call To Action -->
    <div class="section-full content-inner bg-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 section-head text-center">
                    <h2 class="m-b5">Candidats Récents</h2>
                    <h5 class="fw4 m-b0">20+ Featured Cities Added Jobs</h5>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${listCandidats}" var="candidat">
                    <div class="col-lg-3 col-sm-6 col-md-6 m-b30">
                        <div class="city-bx align-items-end  d-flex"
                             style="background-image:url(<%=request.getContextPath()%>Assets/photos/${candidat.getPhotoUrl()})">
                            <div class="city-info">
                                <h5>${candidat.getNomComplet()}</h5>
                                <span>${candidat.getTitreEmploi()}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- Call To Action END -->
    <!-- Our Job -->
    <div class="section-full bg-white content-inner-2">
        <div class="container">
            <div class="d-flex job-title-bx section-head">
                <div class="mr-auto">
                    <h2 class="m-b5">Recent Jobs</h2>
                    <h5 class="fw4 m-b0">20+ Recently Added Jobs</h5>
                </div>
                <div class="align-self-end">
                    <a href="#" class="site-button button-sm">Browse All Jobs <i class="fa fa-long-arrow-right"></i></a>
                </div>
            </div>


            <ul class="post-job-bx">
                <% if (offreList != null && offreList.size() > 0) { %>
                <% for (Offre offre:offreList) { %>
                <li>
                    <a href="/offre-details?id=<%=offre.getId()%>">
                        <div class="d-flex m-b30">
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

            <div class="m-t30">
                <div class="d-flex">
                    <a class="site-button button-sm mr-auto" href="#"><i class="ti-arrow-left"></i> Prev</a>
                    <a class="site-button button-sm" href="#">Next <i class="ti-arrow-right"></i></a>
                </div>
            </div>
        </div>
    </div>
    <!-- Our Job END -->
    <!-- Call To Action -->
    <div class="section-full p-tb70 overlay-black-dark text-white text-center bg-img-fix"
         style="background-image: url(<%=request.getContextPath()%>/WEB-INF/Assets/images/background/bg4.jpg);">
        <div class="container">
            <div class="section-head text-center text-white">
                <h2 class="m-b5">Testimonials</h2>
                <h5 class="fw4">Few words from candidates</h5>
            </div>
            <div class="blog-carousel-center owl-carousel owl-none">
                <div class="item">
                    <div class="testimonial-5">
                        <div class="testimonial-text">
                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                                has been the industry...</p>
                        </div>
                        <div class="testimonial-detail clearfix">
                            <div class="testimonial-pic radius shadow">
                                <img src="images/testimonials/pic1.jpg" width="100" height="100" alt="">
                            </div>
                            <strong class="testimonial-name">David Matin</strong>
                            <span class="testimonial-position">Student</span>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="testimonial-5">
                        <div class="testimonial-text">
                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                                has been the industry...</p>
                        </div>
                        <div class="testimonial-detail clearfix">
                            <div class="testimonial-pic radius shadow">
                                <img src="images/testimonials/pic2.jpg" width="100" height="100" alt="">
                            </div>
                            <strong class="testimonial-name">David Matin</strong>
                            <span class="testimonial-position">Student</span>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="testimonial-5">
                        <div class="testimonial-text">
                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
                                has been the industry...</p>
                        </div>
                        <div class="testimonial-detail clearfix">
                            <div class="testimonial-pic radius shadow">
                                <img src="images/testimonials/pic3.jpg" width="100" height="100" alt="">
                            </div>
                            <strong class="testimonial-name">David Matin</strong>
                            <span class="testimonial-position">Student</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Call To Action END -->
</div>

<!-- scroll top button -->
<button class="scroltop fa fa-arrow-up"></button>
</div>
