

<div class="w3-row-padding w3-padding-64 w3-container">
    <h2>Our contacts</h2>
</div>
<%--Google maps--%>
<%--<div id="map" style="width: 400px; height: 300px;"></div>--%>

<%--<script>--%>
    <%--// Initialize and add the map--%>
    <%--function initMap() {--%>
        <%--// The location of Uluru--%>
        <%--var uluru = {lat: -25.344, lng: 131.036};--%>
        <%--// The map, centered at Uluru--%>
        <%--var map = new google.maps.Map(--%>
            <%--document.getElementById('map'), {zoom: 4, center: uluru});--%>
        <%--// The marker, positioned at Uluru--%>
        <%--var marker = new google.maps.Marker({position: uluru, map: map});--%>
    <%--}--%>
<%--</script>--%>

<%--<script async defer--%>
        <%--src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initMap">--%>
<%--</script>--%>

<div id="mapDiv" style="width: 800px; height: 500px"></div>
<script>
    // position we will use later
    // 53.910106, 27.634165

    var lat = 53.910030;
    var lon = 27.634197;
    // initialize map
    map = L.map('mapDiv').setView([lat, lon], 17);
    // set map tiles source
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
        maxZoom: 18,
    }).addTo(map);
    // add marker to the map
    marker = L.marker([lat, lon]).addTo(map);
    // add popup to the marker
    // marker.bindPopup("<b>Асоба</b><br />Студия красоты<br />Филимонова, 12").openPopup();
</script>
