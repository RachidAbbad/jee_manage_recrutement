<%@ page import="java.util.List" %>
<%@ page import="com.models.Offre" %>
<%@ page import="com.Services.DepartementService" %>
<%@ page import="com.models.Departement" %>
<%
    List<Offre> offreList = (List<Offre>) request.getAttribute("listOffres");
%>

<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Browse Jobs</h1>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <ul class="list-inline">
                        <li><a href="/">Home</a></li>
                        <li><a href="/voir-offres">Browse Jobs</a></li>
                    </ul>
                </div>
                <!-- Breadcrumb row END -->
            </div>
        </div>
    </div>
    <!-- inner page banner END -->
    <!-- contact area -->
    <div class="content-block">
        <!-- Browse Jobs -->
        <div class="section-full bg-white browse-job content-inner-2">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 col-lg-8">
                        <h5 class="widget-title font-weight-700 text-uppercase">Recent Jobs</h5>
                        <ul class="post-job-bx">
                            <% if (offreList != null && offreList.size() > 0) { %>
                                <% for (Offre offre:offreList) { %>
                                    <li>
                                        <a href="/offre-details?id=<%=offre.getId()%>">
                                            <div class="d-flex m-b30">
                                                <div class="job-post-company">
                                                    <span><img src="../../Assets/images/logo/icon1.png"/></span>
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
                    <div class="col-xl-3 col-lg-4">
                        <form action="/search" method="get" class="sticky-top">
                            <div class="clearfix m-b30">
                                <h5 class="widget-title font-weight-700 text-uppercase">Keywords</h5>
                                <div class="">
                                    <input name="jobName" type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                            <div class="clearfix m-b10">
                                <h5 class="widget-title font-weight-700 m-t0 text-uppercase">Location</h5>
                                <input name="cityName" type="text" class="form-control m-b30" placeholder="Location">
                            </div>
                            <div class="clearfix">
                                <h5 class="widget-title font-weight-700 text-uppercase">Category</h5>
                                <select name="departement_id">
                                    <% for (Departement departement: DepartementService.getListDepartement()) { %>
                                    <option value="<%= departement.getId()%>">
                                        <%= departement.getNom()%>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="clearfix">
                                <button type="submit" class="site-button my-3">Search</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Browse Jobs END -->
    </div>
</div>
<!-- Content END-->
<button class="scroltop fa fa-chevron-up"></button>
</div>
