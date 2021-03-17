
const mapbounds = [[123.90100238675655, 32.622157846402175], [133.207154730507, 39.40342567614195]];
const kakaoHeader = { Authorization: "KakaoAK " + "ddc296c2f9f2c09e7b1c05df318b9cb0" }
// $('body').append('<form id="geocoder"></form>');
let map;
let addedMarkers=[];


// if (!savedidx) {
//     if ($('#mapcode')!=null && $('#mapcode').data('idx')!=undefined) savedidx = $('#mapcode').data('idx');
// } 
if (typeof savedidx != "undefined") {
    if (!($('#mapcode')==null || $('#mapcode').data('idx')==undefined)) savedidx = $('#mapcode').data('idx');
} 


$(function(){
    $('body').append('<form id="geocoder"></form>');
    mapboxgl.accessToken = 'pk.eyJ1IjoiZXVva3l1biIsImEiOiJja2szcTZmYzUxYWVlMzFueWFlM2tibmo4In0.U5h18zpNdAKDYjCZBsL8ag';
    map = new mapboxgl.Map({
        container: 'map', // container id
        // style: 'mapbox://styles/mapbox/streets-v11',
        style: 'mapbox://styles/euokyun/ckl7dhkmu55kh17pd35qc5l7j/draft',
        center: [126.978734, 37.494301], // starting position
        minZoom: 6,
        zoom: 13, // starting zoom
        defaultLanguage: 'ko',
        maxBounds: mapbounds
    });
    mapboxgl.setRTLTextPlugin('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.1.0/mapbox-gl-rtl-text.js');
    // Add geolocate control to the map.
    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));
    const mapboxsearch = document.createElement('div');
    mapboxsearch.className = "mapboxgl-ctrl mapboxgl-ctrl d-flex";
    $(mapboxsearch).prepend(
        `<div class="mapboxgl-ctrl-geocoder-options">
                <select id="mapboxgl-ctrl-geocoder-options" name="category_group_code" class="form-select" form='geocoder'>
                    <option value="">모두</option>
                    <option value="MT1">대형마트</option>
                    <option value="CS2">편의점</option>
                    <option value="PS3">어린이집, 유치원</option>
                    <option value="SC4">학교</option>
                    <option value="AC5">학원</option>
                    <option value="PK6">주차장</option>
                    <option value="OL7">주유소, 충전소</option>
                    <option value="SW8">지하철역</option>
                    <option value="BK9">은행</option>
                    <option value="CT1">문화시설</option>
                    <option value="AG2">중개업소</option>
                    <option value="PO3">공공기관</option>
                    <option value="AT4">관광명소</option>
                    <option value="AD5">숙박</option>
                    <option value="FD6">음식점</option>
                    <option value="CE7">카페</option>
                    <option value="HP8">병원</option>
                    <option value="PM9">약국</option>
                </select>
            </div>
            <div class="mapboxgl-ctrl-geocoder">
                <svg class="mapboxgl-ctrl-geocoder--icon mapboxgl-ctrl-geocoder--icon-search" viewBox="0 0 18 18" xml:space="preserve" width="18" height="18"><path d="M7.4 2.5c-2.7 0-4.9 2.2-4.9 4.9s2.2 4.9 4.9 4.9c1 0 1.8-.2 2.5-.8l3.7 3.7c.2.2.4.3.8.3.7 0 1.1-.4 1.1-1.1 0-.3-.1-.5-.3-.8L11.4 10c.4-.8.8-1.6.8-2.5.1-2.8-2.1-5-4.8-5zm0 1.6c1.8 0 3.2 1.4 3.2 3.2s-1.4 3.2-3.2 3.2-3.3-1.3-3.3-3.1 1.4-3.3 3.3-3.3z"></path></svg>
                <!-- <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-refresh-alert" width="18" height="18" viewBox="0 0 18 18" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><path d="M20 11a8.1 8.1 0 0 0 -15.5 -2m-.5 -4v4h4"></path><path d="M4 13a8.1 8.1 0 0 0 15.5 2m.5 4v-4h-4"></path><line x1="12" y1="9" x2="12" y2="12"></line><line x1="12" y1="15" x2="12.01" y2="15"></line></svg> -->
                <input type="text" class="mapboxgl-ctrl-geocoder--input" name="query" placeholder="검색" form='geocoder'>
                <div class="suggestions-wrapper"><ul class="suggestions"></ul></div>
                <div class="mapboxgl-ctrl-geocoder--pin-right"><button type="button" aria-label="Clear" class="mapboxgl-ctrl-geocoder--button"><svg class="mapboxgl-ctrl-geocoder--icon mapboxgl-ctrl-geocoder--icon-close" viewBox="0 0 18 18" xml:space="preserve" width="18" height="18"><path d="M3.8 2.5c-.6 0-1.3.7-1.3 1.3 0 .3.2.7.5.8L7.2 9 3 13.2c-.3.3-.5.7-.5 1 0 .6.7 1.3 1.3 1.3.3 0 .7-.2 1-.5L9 10.8l4.2 4.2c.2.3.7.3 1 .3.6 0 1.3-.7 1.3-1.3 0-.3-.2-.7-.3-1l-4.4-4L15 4.6c.3-.2.5-.5.5-.8 0-.7-.7-1.3-1.3-1.3-.3 0-.7.2-1 .3L9 7.1 4.8 2.8c-.3-.1-.7-.3-1-.3z"></path></svg></button><svg class="mapboxgl-ctrl-geocoder--icon mapboxgl-ctrl-geocoder--icon-loading" viewBox="0 0 18 18" xml:space="preserve" width="18" height="18" style="display: none;"><path fill="#333" d="M4.4 4.4l.8.8c2.1-2.1 5.5-2.1 7.6 0l.8-.8c-2.5-2.5-6.7-2.5-9.2 0z"></path><path opacity=".1" d="M12.8 12.9c-2.1 2.1-5.5 2.1-7.6 0-2.1-2.1-2.1-5.5 0-7.7l-.8-.8c-2.5 2.5-2.5 6.7 0 9.2s6.6 2.5 9.2 0 2.5-6.6 0-9.2l-.8.8c2.2 2.1 2.2 5.6 0 7.7z"></path></svg></div>
            </div>`
    );
    
    $('.mapboxgl-ctrl-top-right').append(mapboxsearch);
    
    $('.mapboxgl-ctrl-top-right').prepend(
        `
            <div class="form-selectgroup custom-checkbox-label mapboxgl-ctrl">
                <label class="form-selectgroup-item">
                <input type="checkbox" name="currentmap" class="form-selectgroup-input" form='geocoder'>
                <span class="form-selectgroup-label">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-map-2" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="18" y1="6" x2="18" y2="6.01"></line><path d="M18 13l-3.5 -5a4 4 0 1 1 7 0l-3.5 5"></path><polyline points="10.5 4.75 9 4 3 7 3 20 9 17 15 20 21 17 21 15"></polyline><line x1="9" y1="4" x2="9" y2="17"></line><line x1="15" y1="15" x2="15" y2="20"></line></svg>
                    화면내에서 검색
                </span>
                </label>
            </div>
                <div class="form-selectgroup custom-checkbox-label mapboxgl-ctrl">
                <label class="form-selectgroup-item">
                <input type="checkbox" name="nearbysearch" class="form-selectgroup-input" form='geocoder'>
                <span class="form-selectgroup-label">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-viewfinder" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><circle cx="12" cy="12" r="9"></circle><line x1="12" y1="3" x2="12" y2="7"></line><line x1="12" y1="21" x2="12" y2="18"></line><line x1="3" y1="12" x2="7" y2="12"></line><line x1="21" y1="12" x2="18" y2="12"></line><line x1="12" y1="12" x2="12" y2="12.01"></line></svg>
                    주변에서 검색
                </span>
                </label>
            </div>
        `
    );
    $('#geocoder').on('submit', function (e) { e.preventDefault(); })
    $('.spinner-border').toggle();
    $('.listings').sortable();
    $('.listings').disableSelection();
    //지도 거리에 따라 표시할지 말지 선택하는 기능
    map.on('moveend', function (e) {
        if (turf.length(turf.lineString(
            [map.unproject({ x: 0, y: 0 }).toArray(),
            map.unproject({
                x: map._canvas.style.width.split('px')[0],
                y: map._canvas.style.height.split('px')[0]
            })
                .toArray()
            ])) <= 20) {
            // console.log("TOO FAR!!!!!");
            MarkerBuilder();
        }
    });

    $('#savelist').on('click', function(e){
        console.log("savelistclicked")
        if (!!addedMarkers) listingtoDataBase();
    });


    map.on('load', function (e) {
        MarkerBuilder();
        psuedoMapboxGeocoding();
        if (!!savedidx) {loadSavedRoutes(savedidx);}
    });
});




//marker, layer 필터용
let filterGroup = $('#filter-group');

let inputQueryDelay;
function psuedoMapboxGeocoding() {
    function geocoderlistElementBuilder(feature) {
        let list = document.createElement('li');
        let currentActiveSearch = document.createElement('a');
        $(currentActiveSearch).append(
            `<div class="mapboxgl-ctrl-geocoder--suggestion">
                <div class="mapboxgl-ctrl-geocoder--suggestion-title">`+ feature.properties.place_name + `</div>
                <div class="mapboxgl-ctrl-geocoder--suggestion-address">`+ feature.properties.address_name + `</div>
                </div>`);
        currentActiveSearch.dataset.id = feature.properties.id;
        currentActiveSearch.dataset.coordinates = feature.geometry.coordinates;
        $(currentActiveSearch).on('click', function (e) {
            map.flyTo({ center: feature.geometry.coordinates, zoom: 15 });
            $('.mapboxgl-marker[data-id=' + e.currentTarget.dataset.id + ']').click();
        });
        $(list).append(currentActiveSearch);
        return list;
    }
    function keywordSearchClose() {
        // console.log("close");
        $('.mapboxgl-ctrl-geocoder--input').val('');
        $('.suggestions').empty().css('display', 'none');
        $('button.mapboxgl-ctrl-geocoder--button[aria-label=Clear]').css('display', 'none');
    }
    // keywordSearchClose();
    $('.mapboxgl-ctrl-geocoder--icon-close').on('click', keywordSearchClose);
    $('.mapboxgl-ctrl-geocoder--input').on('keyup', function (e) {
        $('.suggestions').empty();
        // let formdata = $("#geocoder").serialize();
        let query = $("input[name=query]").val();
        if (query == '' || query == null) {
            keywordSearchClose();
        } else {
            let psuedoFeatureForQuery = { "properties": { "place_name": query, "address_name": '', "id": '' }, "geometry": { 'coordinates': '' } };
            let currentActiveSearch = geocoderlistElementBuilder(psuedoFeatureForQuery);
            currentActiveSearch.className = 'active';
            $('button.mapboxgl-ctrl-geocoder--button[aria-label=Clear]').css('display', 'block');
            $('.suggestions').append(currentActiveSearch);
            $('.suggestions').css('display', 'block')
            //검색을 합쳐야 한다?
            //아니다 - 여기엔 keyword검색, featurebuilder에는 카테고리 검색을 넣자
            clearTimeout(inputQueryDelay);
            inputQueryDelay = setTimeout(function () {
                inputQueryDelay = undefined;
                let querydata = $('#geocoder').serializeArray();
                // let iscategoryempty;
                // let iskeywordempty;
                querydata.forEach(datas => {
                    if (datas.name == 'nearbysearch' && datas.value == 'on') {
                        datas.name = 'x';
                        datas.value = map.getCenter().lng.toFixed(6);
                        querydata.push(
                            { name: 'y', value: map.getCenter().lat.toFixed(6) }
                        );
                    }
                    if (datas.name == 'currentmap' && datas.value == 'on') {
                        datas.name = 'rect';
                        datas.value = [
                            map.unproject({ x: 0, y: 0 }).toArray(),
                            map.unproject({ x: map._canvas.style.width.split('px')[0], y: map._canvas.style.height.split('px')[0] }).toArray()
                        ];
                    };
                    // iscategoryempty = (datas.name == 'category' && (datas.value == '' || datas.value == null));
                    // iskeywordempty = (datas.name == 'keyword' && (datas.value == '' || datas.value == null));
                });
                querydata.push({ name: 'page', value: 1 });

                let features = [];
                //ajax영역 - 키워드가 있을 때만 작동(위에서 체크함), 검색범위에 영향을 받아 마커를 화면에 뿌림.
                //만약 키워드가 없고, 검색범위만 설정된 상태에서 
                $.ajax({
                    type: "get",
                    url: "https://dapi.kakao.com/v2/local/search/keyword.json",
                    // async:false,
                    contentType: 'application/json;charset=UTF-8',
                    headers: kakaoHeader,
                    data: querydata,
                    beforeSend(jqXHR, settings) {
                        //csrf setting
                        // jqXHR.setRequestHeader(csrf_header, csrf_token);
                        $('.spinner-border').toggle();
                        $('.mapboxgl-ctrl-geocoder--icon-search').toggle();
                    },
                    success: function (response) {
                        features = kakaoResultToFeatures(response);
                        createMarker(features);
                        for (let i = 0; i < features.length; i++) {
                            if (features[i].properties.place_name.toLowerCase().search(query.toLowerCase()) !== -1) {
                                let list = geocoderlistElementBuilder(features[i]);
                                $('.suggestions').append(list);
                            }
                        }
                    },
                    complete: function () {
                        $('.spinner-border').toggle();
                        $('.mapboxgl-ctrl-geocoder--icon-search').toggle();
                    }
                });
                // console.log("371", features);
                // createMarker(features);
            }, 250);
        }
    });
    $('#savenload').on('click', savenloadlist);
}




function savenloadlist(){
    $.ajax({
        type: "get",
        url: cp+"/map/api/myroutes",
        // dataType: "dataType",
        contentType:'application/json;charset=UTF-8',
        beforeSend(jqXHR, settings) {
            console.log(settings.url);
            jqXHR.setRequestHeader(csrf_headerName, csrf_token);
        },
        success: function (response) {
            $('.saveloadlist').empty();
            response.metadata.forEach(ele=>{
                let savenloadhtml = `
                <li id="saveload-`+ele.idx+`" class="d-plex">
                `+ele.route+`
                <button type="button" onclick="loadlist('`+ele.idx+`')" data-idx="`+ele.idx+`"class="btn btn-red">불러오기</button>
                <button type="button" onclick="deletelist('`+ele.idx+`')" data-idx="`+ele.idx+`"class="btn btn-red">삭제하기</button>
                </li>
                `;
                $('.saveloadlist').append(savenloadhtml);
            });
        },
        error:function(error) {
            console.log(error);
        }
    });
}

function loadlist(idx){
    console.log(idx);
    removeMarker();
    $('.listings').empty();
    addedMarkers=[];
    loadSavedRoutes(idx);
    $('#mapcode').data('idx', idx);
    $('#mapcode').attr('data-idx', idx);
}

function deletelist(idx){
    $.ajax({
        type: "get",
        url: cp+"/map/api/delete/"+idx,
        data: {idx: idx},
        contentType:'application/json;charset=UTF-8',
        beforeSend(jqXHR, settings) {
            jqXHR.setRequestHeader(csrf_headerName, csrf_token);
        },
        success: function (response) {
            if (response.success) savenloadlist();
        }
    });
}

function kakaoResultToFeatures(kakaoResponse) {
    let features = [];
    kakaoResponse.documents.forEach((p) => {
        //category가 없는 것들은 error로 간주, 제거
        if (p.category_group_code == null || p.category_group_code == '' || p.category_group_name == null || p.category_group_name == '') return;
        features.push(
            {
                "type": "Feature",
                "geometry": {
                    "type": "Point",
                    "coordinates": [p.x, p.y]
                },
                "properties": {
                    "address_name": p.address_name,
                    "category_group_code": p.category_group_code,
                    "category_group_name": p.category_group_name,
                    "category_name": p.category_name,
                    "distance": p.distance,
                    "id": p.id,
                    "phone": p.phone,
                    "place_name": p.place_name,
                    "place_url": p.place_url,
                    "road_address_name": p.road_address_name,
                }
            }
        );
    });
    return features;
};




//마커를 만드는 전체적인 기능
function MarkerBuilder() {
    //kakao 검색을 ajax로 검색해서 features를 얻어옴

    if (!$('select[name=category_group_code]').val()) {
        console.log('category is empty');
        return;
    } else {
        let querydata = $('#geocoder').serializeArray();
        querydata.forEach(datas => {
            if (datas.name == 'nearbysearch' && datas.value == 'on') {
                datas.name = 'x';
                datas.value = map.getCenter().lng.toFixed(6);
                querydata.push(
                    { name: 'y', value: map.getCenter().lat.toFixed(6) }
                );
            }
            if (datas.name == 'currentmap' && datas.value == 'on') {
                datas.name = 'rect';
                datas.value = [
                    map.unproject({ x: 0, y: 0 }).toArray(),
                    map.unproject({ x: map._canvas.style.width.split('px')[0], y: map._canvas.style.height.split('px')[0] }).toArray()
                ];
            };
        });

        let features = [];
        // if (addedMarkers.length!=0) {addedMarkers.forEach(feature=>{features.push(feature);});}
        let page = 1;
        while (page <= 3) {
            querydata.forEach(datas => { if (datas.name == 'page') { datas.value = page } });
            let isEnd;
            $.ajax({
                type: "get",
                url: "https://dapi.kakao.com/v2/local/search/category.json",
                data: querydata,
                dataType: "json",
                async: false,
                headers: kakaoHeader,
                beforeSend(jqXHR, settings) {
                    console.log(settings.url);
                },
                success: function (response) {
                    features = features.concat(kakaoResultToFeatures(response));
                    if (response.meta.is_end || !response.documents.length) isEnd = true;
                },
                complete: function (e) {
                    page++;
                }
            });
            if (isEnd) break;
        };
        createMarker(features);
    }
}


function createMarker(features) {
    removeMarker(features);
    if (addedMarkers.length != 0) { addedMarkers.forEach(feature => { features.push(feature); }); }
    //source를 만들어 layer에 집어넣는다. source, layer의 id가 중복되면 안되기 때문에 삭제처리를 먼저 한다.
    map.addSource('places', {
        'type': 'geojson',
        'data': {
            "type": "FeatureCollection",
            "features": features
        }
    });

    features.forEach(function (feature) {
        let category_group_code = feature.properties['category_group_code'];
        let category_group_name = feature.properties['category_group_name'];
        let layerID = category_group_code;
        if (layerID == null || layerID == '') {
            console.log('nulllayerid', feature);
        }
        if (!map.getLayer(layerID)) {
            map.addLayer({
                "id": layerID,
                "type": "symbol",
                "source": 'places',
                'layout': {
                    'text-field': ['get', 'place_name'],
                    'text-anchor': 'top'
                },
                'filter': ['==', 'category_group_code', category_group_code]
            });

            let input = document.createElement('input');
            input.type = 'checkbox';
            input.id = layerID;
            input.checked = true;
            filterGroup.append(input);

            let label = document.createElement('label');
            label.setAttribute('for', layerID);
            label.textContent = category_group_name;
            filterGroup.append(label);

            input.addEventListener('change', function (e) {
                map.setLayoutProperty(
                    layerID, 'visibility', e.target.checked ? 'visible' : 'none'
                );
                $('.mapboxgl-marker').each((idx, ele) => {
                    if (ele.dataset.category_group_code == layerID) { $(ele).toggle(); }
                });
            });
        };
    });
    addMarker(features);
}


//마커를 지움
function removeMarker(features) {
    filterGroup.empty();
    // $('.marker').remove();
    storedMarker = [];
    // console.log(features);
    // if (features!=[]&&features.length!=0&&features!=null){
    if (map.getSource('places')) {
        map.getSource('places')._options.data.features.forEach(mapfeature => {
            let LayerID = mapfeature.properties['category_group_code'];
            if (map.getLayer(LayerID)) map.removeLayer(LayerID);
            $('.mapboxgl-marker').each((idx, ele) => {
                if (ele.dataset.category_group_code == LayerID) { $(ele).remove(); }
            })
        });
    }
    // features.forEach(feature=>{
    //     let LayerID=feature.properties['category_group_code'];
    //     if (map.getLayer(LayerID)) map.removeLayer(LayerID);
    //     $('.mapboxgl-marker').each((idx,ele)=>{
    //         if (ele.dataset.category_group_code==LayerID) {$(ele).remove();}
    //     })
    // })
    // }
    if (map.getSource('places')) map.removeSource('places');
}

//마커를 추가하는 기능
let storedMarker = [];
function addMarker(features) {
    features.forEach(element => {
        // let div = document.createElement('div');
        // div.id = "marker-" + element.properties.id;
        // div.className = 'marker';
        // div.className = 'element.properties.category_group_code';
        let popupHtml =
            `<h3> ` + element.properties.place_name + ` </h3>
                <p> `+ element.properties.category_group_name + ` </p>
                <p> `+ element.properties.address_name + ` </p>
                <p> `+ element.properties.distance + ` </p>
                <button type="button" id='addmarker' class='btn' onclick="addToListings('`+ element.properties.id + `')">목적지에 추가하기</button>`
            ;
        //TODO: 목적지에 있으면 제거하기 버튼 보여주기
        let marker = new mapboxgl.Marker()
            .setLngLat(element.geometry.coordinates);
        let popup = new mapboxgl.Popup({ offset: 25 }).setHTML(popupHtml);
        let markerElement = marker.getElement();
        markerElement.dataset.category_group_code = element.properties.category_group_code;
        markerElement.dataset.id = element.properties.id;

        //마커를 누르면 줌해서 중앙에 표시하는 기능
        markerElement.addEventListener('click', () => {
            map.flyTo({ center: marker._lngLat, zoom: 15 });
        });
        marker.setPopup(popup).addTo(map);
        storedMarker.push(marker);
    });
};
//#region 테스트용

// map.on('click', function(e){console.log(e.lngLat);});

//#endregion

//map에서 map_sidebar로



//선택한 feature를 목적지 패널에 추가하는 기능
function addToListings(id) {
    let features = map.getSource('places')._options.data.features;
    let selectedFeature = features.find((e) => { if (e.properties.id == id) return true; })
    console.log('features', features)
    console.log('selectedFeature', selectedFeature)
    addedMarkers.push(selectedFeature);

    selectedFeatureToDataBase(selectedFeature);

    let addinglist = document.createElement('li');
    addinglist.innerHTML =
        `<div class="card">
            <div class='card-header d-flex'>
                <p class='m-0 text-muted'>`+ selectedFeature.properties.category_group_name + `</p>
                ><h3 class="m-0">`+ selectedFeature.properties.place_name + `</h3>
            </div>
            <div class="card-body">
                <p>`+ selectedFeature.properties.address_name + `</p>
                <button type="button" onclick="removeFromListings('`+ id + `')" class="btn btn-danger w-100">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24"
                        height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none"
                        stroke-linecap="round" stroke-linejoin="round">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                        <line x1="4" y1="7" x2="20" y2="7" />
                        <line x1="10" y1="11" x2="10" y2="17" />
                        <line x1="14" y1="11" x2="14" y2="17" />
                        <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12" />
                        <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3" />
                    </svg>삭제
                </button>
            </div>
        </div>`;
    addinglist.id = 'listings-' + id;
    addinglist.className = 'listingsDestination';
    addinglist.dataset.coordinates = selectedFeature.geometry.coordinates;
    addinglist.dataset.locationid = selectedFeature.properties.id;
    $('.listings').append(addinglist);
    if (addedMarkers.length>1) pathDrawer(addedMarkers);
}

let listingmouseupeventtimer;
//목적지 드래그 앤 드랍 순서변경
$(".listings").on('mouseup', 'li', function (e) {
    clearTimeout(listingmouseupeventtimer);
    listingmouseupeventtimer = setTimeout(function () {
        listingmouseupeventtimer = undefined;
        console.log('addedMarkers:', addedMarkers);
        let reorderAddedMarkers = [];
        console.log("id", e);
        $('.listingsDestination').each((idx, ele) => {
            if (ele.id.includes('listings-')) {
                // console.log('listingsDestination', idx, ele);
                let f = addedMarkers.find(marker => { if (ele.dataset.locationid == marker.properties.id) return true; })
                console.log(f);
                reorderAddedMarkers.push(f);
            }
        });
        addedMarkers = reorderAddedMarkers;
        if (addedMarkers.length>1) pathDrawer(addedMarkers);
    }, 250);
});

//저장된 목적지를 리스트에서 제거
function removeFromListings(listid) {
    console.log(listid);
    $('.listings').find("#listings-" + listid).remove();
    addedMarkers.splice(addedMarkers.findIndex(marker => { return marker.properties.id == listid }), 1);
}

//선택된 목적지들 사이에 선을 긋고 이동시키는 기능
function pathDrawer(markers) {
    const steps = 500;
    //속도조절
    let routes = {
        'type': 'FeatureCollection',
        'features': []
    };
    let points = {
        'type': 'FeatureCollection',
        'features': []
    };
    for (let i = 0; i < markers.length - 1;) {
        const origin = markers[i];
        const destination = markers[++i];
        // console.log(origin);
        let route = {
            'type': 'Feature',
            'geometry': {
                'type': 'LineString',
                'coordinates': [origin.geometry.coordinates, destination.geometry.coordinates]
            }
        };
        routes.features.push(route);
    }
    let point = {
        'type': 'Feature',
        'properties': {},
        'geometry': {
            'type': 'Point',
            'coordinates': markers[0].geometry.coordinates
        }
    }
    points.features.push(point);

    if (routes.features.length>1) {
        for (let i = 0; i < routes.features.length; i++) {
            let lineDistance=turf.length(routes.features[i]);
            let arc=[];
            
            for (let j = 0; j < lineDistance; j += lineDistance / steps) {
                let segment = turf.along(routes.features[i], j);
                // console.log(segment);
                arc.push(segment.geometry.coordinates);
            }
            routes.features[i].geometry.coordinates = arc;
        }
    }
    // console.log('routes',routes);
    if (map.getLayer('route')) map.removeLayer('route');
    if (map.getLayer('point')) map.removeLayer('point');
    if (map.getSource('route')) map.removeSource('route');
    if (map.getSource('point')) map.removeSource('point');

    map.addSource('route', {
        'type': 'geojson',
        'data': routes
    });

    map.addLayer({
        'id': 'route',
        'source': 'route',
        'type': 'line',
        'paint': {
            'line-width': 2,
            'line-color': '#007cbf'
        }
    });
    map.addSource('point', {
        'type': 'geojson',
        'data': points
    });
    map.addLayer({
        'id': 'point',
        'source': 'point',
        'type': 'circle',
        'paint': {
            'circle-radius': 10,
            'circle-color': '#007cbf'
        }
    });
    // console.log('points',points)
    // console.log('routes',routes)


    let counter = 0;
    let animateindex = 0;
    function animate() {
        let start = routes.features[animateindex].geometry.coordinates[counter >= steps ? counter - 1 : counter];
        let end = routes.features[animateindex].geometry.coordinates[counter >= steps ? counter : counter + 1];
        if (!start || !end) {
            // console.log('endstart')
            animateindex++;
            counter = 0;
            if (animateindex >= routes.features.length) return;
            requestAnimationFrame(animate);
        }
        // console.log('i', animateindex)
        // console.log('start', start)
        // console.log('end', end)
        // Update point geometry to a new position based on counter denoting
        // the index to access the arc
        points.features[0].geometry.coordinates =
            routes.features[animateindex].geometry.coordinates[counter];

        // Calculate the bearing to ensure the icon is rotated to match the route arc
        // The bearing is calculated between the current point and the next point, except
        // at the end of the arc, which uses the previous point and the current point
        points.features[0].properties.bearing = turf.bearing(turf.point(start), turf.point(end));

        // Update the source with this new data
        map.getSource('point').setData(points);

        // Request the next frame of animation as long as the end has not been reached
        if (counter < steps) {
            // console.log('next')
            requestAnimationFrame(animate);
        } else {
            animateindex++;
            // console.log('endend')
            counter = 0;
            if (animateindex >= routes.features.length) return;
            requestAnimationFrame(animate);

            // return;
        }
        // console.log('counter', counter);
        counter += 1;
    }
    animate(animateindex, counter);
}


function listingtoDataBase(){
    // TODO: 저장하기 버튼
    $.ajax({
        type: "post",
        url: cp+"/map/api/addroutes",
        data: JSON.stringify(addedMarkers),
        contentType:'application/json;charset=UTF-8',
        beforeSend(jqXHR, settings) {
            console.log(settings.url);
            jqXHR.setRequestHeader(csrf_headerName, csrf_token);
        },
        // dataType: "dataType",
        success: function (response) {
            console.log(response);
            if (response.success) {
                $('#mapcode').data('idx', response.idx);
                $('#mapcode').attr('data-idx', response.idx);
            }
        },
        error:function(error) {
            console.log(error);
        }
    });
}

//DB에 지도정보 저장하기(자동)
function selectedFeatureToDataBase(feature){
    // for (let index = 0; index < arguments.length; index++) {
        // console.log(arguments[index]);
        // console.log(JSON.stringify(arguments[index]));
        // console.log(JSON.parse(arguments[index]));
        $.ajax({
            type: "post",
            url: cp+"/map/api/addmap",
            data: JSON.stringify(feature),
            // dataType:'json',
            contentType:'application/json;charset=UTF-8',
            beforeSend(jqXHR, settings) {
                jqXHR.setRequestHeader(csrf_headerName, csrf_token);
            },
            success: function (response) {
                console.log(response);
            }
        });
    // }
}

//데이터 불러오기
function loadSavedRoutes(idx){
    $.ajax({
        type: "get",
        url: cp+"/map/api/routes/"+idx,
        // data: {idx: idx},
        // dataType: "dataType",
        beforeSend(jqXHR, settings) {
            jqXHR.setRequestHeader(csrf_headerName, csrf_token);
            //TODO:로딩애니메이션 시작
            console.log(settings.url);
        },
        success: function (response) {
            console.log('response',response);
            createMarker(response);
            response.forEach(feature=>{
                console.log("feature", feature)
                addToListings(feature.properties.id);
            });
            // addedMarkers=response;

        },
        complete: function(){
            //TODO:로딩애니메이션 끝
        },
        error:function(error) {
            console.log(error);
        }
    });
}

//DB에 지도정보와 플레이스 ID를 저장하기
//DB에 경로정보 저장하기

