$(document).ready(function(){
    // register type compte
    $("#myTab a").click(function(e) {
        $("#typeCompte").val($(this).text());
    });

    // dashboard change information/voire information
    $("#dashboard-change-info").hide();

    $("#dashboard-change-compte-action").click(function () {
        $("#dashboard-change-info").show();
        $("#dashboard-voir-info").hide();
    });
    $("#dashboard-voir-compte-action").click(function () {
        $("#dashboard-change-info").hide();
        $("#dashboard-voir-info").show();
    });

    ////////////////////////// cv inputs

    // form submit
    $("#ajouterCvForm").submit(function (event) {
        event.preventDefault();

        // list formations
        const formations = [];
        $('#formationsList').children('.item').each(function () {
            // each formation
            const etablissement = $(this).find("input[name='nom_etablissement']").val();
            const diplome = $(this).find("input[name='nom_diplome']").val();
            const startDate = $(this).find("input[name='start_date']").val();
            const endDate = $(this).find("input[name='end_date']").val();

            formations.push({etablissement, diplome, startDate, endDate});
        });

        // list experiences
        const experiences = [];
        $('#experiencesList').children('.item').each(function () {
            // each experience
            const entreprise = $(this).find("input[name='nom_entreprise']").val();
            const sujet = $(this).find("input[name='sujet']").val();
            const startDate = $(this).find("input[name='start_date']").val();
            const endDate = $(this).find("input[name='end_date']").val();

            experiences.push({entreprise, sujet, startDate, endDate});
        });

        // list projets
        const projets = [];
        $('#projetsList').children('.item').each(function () {
            // each projet
            const titre = $(this).find("input[name='titre_projet']").val();
            const type = $(this).find("input[name='type_projet']").val();

            projets.push({titre, type});
        });

        // list competences
        const competences = [];
        $('#competencesList').children('.item').each(function () {
            // each competence
            const nom = $(this).find("input[name='nom_competence']").val();
            const niveau = $(this).find("input[name='niveau_competence']").val();

            competences.push({nom, niveau});
        });

        // inputs
        $("#formationsInput").val(JSON.stringify(formations));
        $("#experiencesInput").val(JSON.stringify(experiences));
        $("#projetsInput").val(JSON.stringify(projets));
        $("#competencesInput").val(JSON.stringify(competences));

        // submit form
        $("#ajouterCvForm")[0].submit();
    });

    // formations
    $("#addFormation").click(function () {
        var strVar="";
        strVar += "<hr />";
        strVar += "<div class=\"row\">";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Etablissement<\/label>";
        strVar += "                                    <input type=\"text\" name=\"nom_etablissement\" class=\"form-control\" placeholder=\"Etablissement\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Diplome<\/label>";
        strVar += "                                    <input type=\"text\" name=\"nom_diplome\" class=\"form-control\" placeholder=\"Diplome\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Start date<\/label>";
        strVar += "                                    <input type=\"date\" name=\"start_date\" class=\"form-control\" placeholder=\"Start date\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>End date<\/label>";
        strVar += "                                    <input type=\"date\" name=\"end_date\" class=\"form-control\" placeholder=\"End date\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                        <\/div>";

        var newDiv = document.createElement('div');
        newDiv.innerHTML = strVar;
        newDiv.setAttribute('class', 'item');
        document.getElementById('formationsList').appendChild(newDiv);
    });

    // experiences
    $("#addExperience").click(function () {
        var strVar="";
        strVar += "<hr />";
        strVar += "<div class=\"row\">";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Entreprise<\/label>";
        strVar += "                                    <input type=\"text\" name=\"nom_entreprise\" class=\"form-control\" placeholder=\"Entreprise\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Sujet<\/label>";
        strVar += "                                    <input type=\"text\" name=\"sujet\" class=\"form-control\" placeholder=\"Sujet\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Start date<\/label>";
        strVar += "                                    <input type=\"date\" name=\"start_date\" class=\"form-control\" placeholder=\"Start date\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>End date<\/label>";
        strVar += "                                    <input type=\"date\" name=\"end_date\" class=\"form-control\" placeholder=\"End date\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                        <\/div>";

        var newDiv = document.createElement('div');
        newDiv.innerHTML = strVar;
        newDiv.setAttribute('class', 'item');
        document.getElementById('experiencesList').appendChild(newDiv);
    });

    // projets
    $("#addProjet").click(function () {
        var strVar="";
        strVar += "<hr />";
        strVar += "<div class=\"row\">";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Titre<\/label>";
        strVar += "                                    <input type=\"text\" name=\"titre_projet\" class=\"form-control\" placeholder=\"Titre\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Type<\/label>";
        strVar += "                                    <input type=\"text\" name=\"type_projet\" class=\"form-control\" placeholder=\"Type\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                        <\/div>";

        var newDiv = document.createElement('div');
        newDiv.innerHTML = strVar;
        newDiv.setAttribute('class', 'item');
        document.getElementById('projetsList').appendChild(newDiv);
    });

    // competences
    $("#addCompetence").click(function () {
        var strVar="";
        strVar += "<hr />";
        strVar += "<div class=\"row\">";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Nom<\/label>";
        strVar += "                                    <input type=\"text\" name=\"nom_competence\" class=\"form-control\" placeholder=\"Nom\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                            <div class=\"col-md-6\">";
        strVar += "                                <div class=\"form-group\">";
        strVar += "                                    <label>Niveau<\/label>";
        strVar += "                                    <input type=\"number\" name=\"niveau_competence\" class=\"form-control\" placeholder=\"Niveau\">";
        strVar += "                                <\/div>";
        strVar += "                            <\/div>";
        strVar += "                        <\/div>";

        var newDiv = document.createElement('div');
        newDiv.innerHTML = strVar;
        newDiv.setAttribute('class', 'item');
        document.getElementById('competencesList').appendChild(newDiv);
    });
});