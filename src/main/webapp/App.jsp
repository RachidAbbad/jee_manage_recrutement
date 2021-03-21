<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <meta name="robots" content=""/>
    <meta name="description" content="JobBoard - HTML Template"/>
    <meta property="og:title" content="JobBoard - HTML Template"/>
    <meta property="og:description" content="JobBoard - HTML Template"/>
    <meta property="og:image" content="JobBoard - HTML Template"/>
    <meta name="format-detection" content="telephone=no">
    <!-- FAVICONS ICON -->
    <link rel="icon" href="<%=request.getContextPath()%>Assets/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>Assets/images/favicon.png"/>

    <!-- PAGE TITLE HERE -->
    <title>JobBoard - ${title}</title>

    <!-- MOBILE SPECIFIC -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--[if lt IE 9]>
    <script src="<%=request.getContextPath()%>/Assets/js/html5shiv.min.js"></script>
    <script src="<%=request.getContextPath()%>/Assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- STYLESHEETS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>Assets/css/plugins.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>Assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>Assets/css/templete.css">
    <link class="skin" rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>Assets/css/skin/skin-1.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>Assets/plugins/datepicker/css/bootstrap-datetimepicker.min.css"/>
    <!-- Revolution Slider Css -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>Assets/plugins/revolution/revolution/css/layers.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>Assets/plugins/revolution/revolution/css/settings.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>Assets/plugins/revolution/revolution/css/navigation.css">
    <!-- Revolution Navigation Style -->
</head>
<body id="bg">
<div id="loading-area"></div>
<div class="page-wraper">
    <!-- NavBar -->
    <jsp:include page="/WEB-INF/Components/navbar.jsp"/>
    <!-- Content -->
    <jsp:include page="/WEB-INF/Components/${component}.jsp"/>
    <!-- Footer -->
    <jsp:include page="/WEB-INF/Components/footer.jsp"/>
</div>
<!-- JAVASCRIPT FILES ========================================= -->
<script src="<%=request.getContextPath()%>Assets/js/jquery.min.js"></script><!-- JQUERY.MIN JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/wow/wow.js"></script><!-- WOW JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/bootstrap/js/popper.min.js"></script><!-- BOOTSTRAP.MIN JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- BOOTSTRAP.MIN JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<!-- FORM JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
<!-- FORM JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/magnific-popup/magnific-popup.js"></script>
<!-- MAGNIFIC POPUP JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/counter/waypoints-min.js"></script><!-- WAYPOINTS JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/counter/counterup.min.js"></script><!-- COUNTERUP JS -->
<script src="<%=request.getContextPath()%>Assets/plugins/imagesloaded/imagesloaded.js"></script><!-- IMAGESLOADED -->
<script src="<%=request.getContextPath()%>Assets/plugins/masonry/masonry-3.1.4.js"></script><!-- MASONRY -->
<script src="<%=request.getContextPath()%>Assets/plugins/masonry/masonry.filter.js"></script><!-- MASONRY -->
<script src="<%=request.getContextPath()%>Assets/plugins/owl-carousel/owl.carousel.js"></script><!-- OWL SLIDER -->
<script src="<%=request.getContextPath()%>Assets/plugins/rangeslider/rangeslider.js"></script><!-- Rangeslider -->
<script src="<%=request.getContextPath()%>Assets/js/custom.js"></script><!-- CUSTOM FUCTIONS  -->
<script src="<%=request.getContextPath()%>Assets/js/dz.carousel.js"></script><!-- SORTCODE FUCTIONS  -->
<script src='<%=request.getContextPath()%>Assets/js/recaptcha/api.js'></script> <!-- Google API For Recaptcha  -->
<script src="<%=request.getContextPath()%>Assets/js/dz.ajax.js"></script><!-- CONTACT JS  -->
<script src="<%=request.getContextPath()%>Assets/plugins/paroller/skrollr.min.js"></script><!-- PAROLLER -->
<script src="<%=request.getContextPath()%>Assets/js/main.js"></script>
</body>

</html>