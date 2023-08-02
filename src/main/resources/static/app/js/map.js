// Đoạn mã trong app.js
let map;
let infoWindow;
let locationInfoDiv;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 10.7769, lng: 106.7009 },
        zoom: 14,
    });

    infoWindow = new google.maps.InfoWindow();
    locationInfoDiv = document.getElementById('locationInfo');

    map.addListener('click', onMapClick);
}

function onMapClick(event) {
    const latLng = event.latLng;
    const latitude = latLng.lat();
    const longitude = latLng.lng();

    const locationText = `Địa điểm đã chọn: Latitude: ${latitude}, Longitude: ${longitude}`;

    displayLocationInfo(locationText);
}

function displayLocationInfo(text) {
    locationInfoDiv.innerText = text;
}
