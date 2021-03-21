$(document).ready(function(){
    $("#myTab a").click(function(e) {
        $("#typeCompte").val($(this).text());
    });
});