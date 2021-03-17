<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript">
    var savedidx = '${idx}';
    var cp = '${cp}';
    var csrf_headerName = '${_csrf.headerName}';
    var csrf_token = '${_csrf.token}';
</script>
<!-- 
<script type="text/javascript" src="${cp }/resources/js/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src='https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.js'></script>
<link href='https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.css' rel='stylesheet' />
<script src='https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-language/v0.10.1/mapbox-gl-language.js'></script>
<script src="https://npmcdn.com/@turf/turf@5.1.6/turf.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css">
<link rel="stylesheet" href="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.css" type="text/css"/>
-->
<!-- <script src="${cp }/resources/map/routemap.js"></script>
<link rel="stylesheet" href="${cp }/resources/map/routemap.css"/>  -->


<body>
    <div class='card ratio ratio-16x9'>
        <div class="card-body d-flex">
            <div class="map_sidebar">
                <div class="map_sidebar_heading d-flex">
                    <ul class="nav nav-tabs nav-fill" data-bs-toggle="tabs">
                        <li class="nav-item">
                            <a href="#tabs-1" id="destinations" class="nav-link active" data-bs-toggle="tab">목적지</a>
                        </li>
                        <li class="nav-item">
                            <a href="#tabs-2" id="savenload" class="nav-link" data-bs-toggle="tab">Save/Load</a>
                        </li>
                    </ul>
                </div>
                    <div class="tab-content">
                        <div class="tab-pane show active" id="tabs-1">
                            <div>
                                <div class="listings-wrapper">
                                    <ul class="listings"></ul>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tabs-2">
                            <div>
                                <a href="#" id="savelist" class="btn btn-red">저장하기</a>
                                <ul class="saveloadlist"></ul>
                            </div>
                        </div>
                    </div>
                
            </div>
            <div id='map' class='map w-100 h-100'>
                <nav id="filter-group" class="filter-group"></nav>
                <div id="search-category" class="search-category"></div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">

    function load(filename, filetype) {
        if (filetype == "js") { //if filename is a external JavaScript file
            var fileref = document.createElement('script')
            fileref.setAttribute("type", "text/javascript")
            fileref.setAttribute("src", filename)
        }
        else if (filetype == "css") { //if filename is an external CSS file
            var fileref = document.createElement("link")
            fileref.setAttribute("rel", "stylesheet")
            fileref.setAttribute("type", "text/css")
            fileref.setAttribute("href", filename)
        }
        if (typeof fileref != "undefined")
            document.getElementsByTagName("head")[0].appendChild(fileref)
    }
    if (!$('#mapcode')[0]) {
        load('${cp }/resources/js/jquery-3.5.1.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-language/v0.10.1/mapbox-gl-language.js', 'js');
        load('https://npmcdn.com/@turf/turf@5.1.6/turf.min.js', 'js');
        load('https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js', 'js');
        // load('https://unpkg.com/@tabler/core@latest/dist/js/tabler.min.js', 'js');
        load('https://api.mapbox.com/mapbox-gl-js/v2.1.1/mapbox-gl.css', 'css');
        load('https://unpkg.com/@tabler/core@latest/dist/css/tabler.min.css', 'css');
        load('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.5.1/mapbox-gl-geocoder.css', 'css');
        // load('${cp }/resources/map/routemap.js', 'js');
        // load('${cp }/resources/map/routemap.css', 'css');
    }
    load('${cp }/resources/map/routemap.js', 'js');
    load('${cp }/resources/map/routemap.css', 'css');
    
</script>