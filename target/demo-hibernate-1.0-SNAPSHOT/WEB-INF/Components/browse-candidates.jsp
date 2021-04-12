<%@ page import="com.models.Candidat" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
    List<Candidat> candidatList = (List<Candidat>) request.getAttribute("candidats");
%>
<!-- Content -->
<div class="page-content bg-white">
    <!-- inner page banner -->
    <div class="dez-bnr-inr overlay-black-middle"
         style="background-image:url(<%=request.getContextPath()%>/WEB-INF/Assets/images/banner/bnr1.jpg);">
        <div class="container">
            <div class="dez-bnr-inr-entry">
                <h1 class="text-white">Browse Candidates</h1>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <ul class="list-inline">
                        <li><a href="#">Home</a></li>
                        <li>Browse Candidates</li>
                    </ul>
                </div>
                <!-- Breadcrumb row END -->
            </div>
        </div>
    </div>
    <!-- inner page banner END -->
    <div class="content-block">
        <div class="section-full bg-white browse-job content-inner-2">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 col-lg-8">
                        <div class="m-b30">
                            <input type="text" class="form-control" placeholder="Search freelancer services">
                        </div>
                        <ul class="post-job-bx">
                            <%for (Candidat candidat:candidatList) {%>
                                <li>
                                    <a href="/candidat?id=<%=candidat.getId()%>">
                                        <div class="d-flex m-b30">
                                            <div class="job-post-company">
                                                <span>
                                                    <% if (candidat.getPhotoUrl().isEmpty() || candidat.getPhotoUrl() == null) { %>
                                                        <img src="../../Assets/images/logoPerson.png"/>
                                                    <% } else { %>
                                                        <img src="<%=request.getContextPath()%>Assets/photos/<%=candidat.getPhotoUrl()%>">
                                                    <% } %>

                                                </span>
                                            </div>
                                            <div class="job-post-info">
                                                <h4><%=candidat.getNomComplet()%></h4>
                                                <ul>
                                                    <li><%=candidat.getTitreEmploi()%></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                            <%}%>
                        </ul>
                        <div class="pagination-bx m-t30">
                            <ul class="pagination">
                                <li class="previous"><a href="#"><i class="ti-arrow-left"></i> Prev</a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li class="next"><a href="#">Next <i class="ti-arrow-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-4">
                        <div class="sticky-top">
                            <div class="clearfix m-b30">
                                <h5 class="widget-title font-weight-700 text-uppercase">Keywords</h5>
                                <div class="">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                            <div class="clearfix m-b10">
                                <h5 class="widget-title font-weight-700 m-t0 text-uppercase">Location</h5>
                                <input type="text" class="form-control m-b30" placeholder="Location">
                                <div class="input-group m-b20">
                                    <input type="text" class="form-control" placeholder="120">
                                    <select>
                                        <option>Km</option>
                                        <option>miles</option>
                                    </select>
                                </div>
                            </div>
                            <div class="clearfix m-b30">
                                <h5 class="widget-title font-weight-700 text-uppercase">Job type</h5>
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-6">
                                        <div class="product-brand">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check1"
                                                       name="example1">
                                                <label class="custom-control-label" for="check1">Freelance</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check2"
                                                       name="example1">
                                                <label class="custom-control-label" for="check2">Full Time</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check3"
                                                       name="example1">
                                                <label class="custom-control-label" for="check3">Internship</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check4"
                                                       name="example1">
                                                <label class="custom-control-label" for="check4">Part Time</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check5"
                                                       name="example1">
                                                <label class="custom-control-label" for="check5">Temporary</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-6">
                                        <div class="product-brand">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check8"
                                                       name="example1">
                                                <label class="custom-control-label" for="check8">Internship</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check9"
                                                       name="example1">
                                                <label class="custom-control-label" for="check9">Part Time</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check10"
                                                       name="example1">
                                                <label class="custom-control-label" for="check10">Temporary</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check6"
                                                       name="example1">
                                                <label class="custom-control-label" for="check6">Freelance</label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="check7"
                                                       name="example1">
                                                <label class="custom-control-label" for="check7">Full Time</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix">
                                <h5 class="widget-title font-weight-700 text-uppercase">Category</h5>
                                <select>
                                    <option>Any Category</option>
                                    <option>Automotive Jobs</option>
                                    <option>Construction Facilities</option>
                                    <option>Design, Art & Multimedia</option>
                                    <option>Food Services</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Content END-->

<button class="scroltop fa fa-chevron-up"></button>
</div>
