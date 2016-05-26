	<script src='https://maps.googleapis.com/maps/api/js?v=3.exp'></script>

	<div style='overflow:hidden;height:320px;width:400px;'>
	<div id='gmap_canvas' style='height:320px;width:400px;'></div>
	<div><small><a href="http://embedgooglemaps.com">embed google map</a></small></div>
	<div><small><a href="http://www.freedirectorysubmissionsites.com/">www.freedirectorysubmissionsites.com</a></small></div>
	<style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div>
	
	<script type='text/javascript'>
	function init_map()
	{
		var myOptions = {zoom:15,center:new google.maps.LatLng(51.621148, -0.062838),mapTypeId: google.maps.MapTypeId.ROADMAP};
		map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
		marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(51.621148, -0.062838)});
		infowindow = new google.maps.InfoWindow(
		{
			content:'<strong>address</strong><br>5 Brook Crescent NEW, London, N9 0DJ<br>'
		});
		google.maps.event.addListener(marker, 'click', function()
		{
			infowindow.open(map,marker);
		});
		infowindow.open(map,marker);
	}
	google.maps.event.addDomListener(window, 'load', init_map);
	</script>