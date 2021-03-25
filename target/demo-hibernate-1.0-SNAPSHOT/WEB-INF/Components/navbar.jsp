<%
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="site-header mo-left header fullwidth">
    <!-- main header -->
    <div class="sticky-header main-bar-wraper navbar-expand-lg">
        <div class="main-bar clearfix">
            <div class="container clearfix">
                <!-- website logo -->
                <div class="logo-header mostion">
                    <a href="/"><img src="../../Assets/images/logo.png" class="logo" alt=""></a>
                </div>
                <!-- nav toggle button -->
                <!-- nav toggle button -->
                <button class="navbar-toggler collapsed navicon justify-content-end" type="button"
                        data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span></span>
                    <span></span>
                    <span></span>
                </button>
                <!-- extra nav -->
                <div class="extra-nav">
                    <div class="extra-cell">
                        <% if (userEmail == null) { %>
                            <a href="/register" class="site-button"><i class="fa fa-user"></i> Sign Up</a>
                            <a href="/login" class="site-button"><i class="fa fa-lock"></i> Login</a>
                        <% } else { %>
                            <a href="/dashboard" class="site-button"><i class="fa fa-tachometer"></i> Dashboard</a>
                        <a href="/logout" class="site-button"><i class="fa fa-sign-out"></i> Logout</a>
                        <% } %>
                    </div>
                </div>
                <!-- Quik search -->
                <div class="dez-quik-search bg-primary">
                    <form action="#">
                        <input name="search" value="" type="text" class="form-control" placeholder="Type to search">
                        <span id="quik-search-remove"><i class="flaticon-close"></i></span>
                    </form>
                </div>
                <!-- main nav -->
                <div class="header-nav navbar-collapse collapse justify-content-start" id="navbarNavDropdown">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/">Home</a>
                        </li>
                        <li>
                            <a href="#">Pour les candidats <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="/voir-offres" class="dez-page">Voir les offres</a></li>
                                <li><a href="/liste-entreprises" class="dez-page">Voir les entreprises</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">Pour les recruteurs <i class="fa fa-chevron-down"></i></a>
                            <ul class="sub-menu">
                                <li><a href="/liste-candidats" class="dez-page">Voir les candidats</a></li>
                                <li><a href="/ajouter-offre" class="dez-page">Ajouter un offre</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- main header END -->
</header>
