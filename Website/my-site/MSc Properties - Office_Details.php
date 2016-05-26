<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>MSc Properties</title>

	<link rel="stylesheet" type="text/css" href="../css/msc_properties.css" />

</head>

<body>

<div>
	<a href="MSc Properties - Home.php" border="0">
	<img src="../images/msc_properties_logo.jpg" alt="msc_properties_logo" width=983 >
	</a>
</div>

<div id="toolbar">
	<a href="MSc Properties - Home.php" border="0">Home</a> | 
	<a href="MSc Properties - Rent.php" border="0">Rent</a> | 
	<a href="MSc Properties - Let.php" border="0">Let</a> | 
	<a href="MSc Properties - Careers.php" border="0">Careers</a> | 
	<a href="MSc Properties - About_us.php" border="0">About us</a>
	
</div>

<div id="officedetails">

	<?php

		global $office;
		
		$office = $_POST['office'];
		
		print "<h2>MSc Properties - ".$office."<br>";
		
	?>
	<br>
	<table border='1'>
	<tr><th>Address</th><th>Oppening Times</th><th>Location</th></tr>
	<tr><td style="	padding: 20px 20px 20px 20px;" valign="top">
	
	<?php
		include "MSc_Properties - Common_Functions.php";
		
		$town = '';
		$area = '';
		
		if ($office !== '-')
		{
			
			list($town, $area) = explode('-', $office);
			$town = trim($town);
			$area = trim($area);
		}
		
		$address = '';
		
		$sql_statement = "SELECT officeCode, buildingNumber, buildingName, ";
		$sql_statement .= "streetNumber, subStreetNumber, subStreet, ";
		$sql_statement .= "street, area, town, country, postcode ";
		$sql_statement .= "FROM  offices JOIN addresses ";
		$sql_statement .= "USING(addressRef) ";
		$sql_statement .= "WHERE town ='".$town."' ";
		$sql_statement .= "AND area ='".$area."' ";
		
		
		$sqlResults = selectResults($sql_statement);
		
		$error_or_rows = $sqlResults[0];
		
		if (substr($error_or_rows, 0 , 5) == 'ERROR')
		{
			print "<br />Error on DB<br><br>".$error_or_rows."<br><br>".$sql_statement;
		}
		else
		{
			$officeCode = $sqlResults [1] ['officeCode'];
			$buildingNumber  = $sqlResults [1] ['buildingNumber'];
			$buildingName  = $sqlResults [1] ['buildingName'];
			$subStreetNumber  = $sqlResults [1] ['subStreetNumber'];
			$subStreet  = $sqlResults [1] ['subStreet'];
			$streetNumber  = $sqlResults [1] ['streetNumber'];
			$street  = $sqlResults [1] ['street'];
			$area  = $sqlResults [1] ['area'];
			$town  = $sqlResults [1] ['town'];
			$country  = $sqlResults [1] ['country'];
			$postcode  = $sqlResults [1] ['postcode'];
			
			$address = printAddress($buildingNumber, $buildingName, $subStreetNumber, $subStreet, $streetNumber, $street, $area, $town, $country, $postcode);
		}
		
		$telephone = getOfficeContact($officeCode, 'PHNE');
		
		$fax = getOfficeContact($officeCode, 'FAX');
		
		$email = getOfficeContact($officeCode, 'EMAIL');
		
		print $address."<br><br>\n";
		print "Tel: ".$telephone."<br>\n";
		print "Fax: ".$fax."<br><br>\n";
		print "E-Mail: ".$email."<br>\n";
	?>
	
	</td>
	
	<td style="	padding: 20px 20px 20px 20px;" valign="top">
	<table border='0'>
	<tr><td>Monday</td><td>09:00 to 19:00</td></tr>
	<tr><td>Tuesday</td><td>09:00 to 19:00</td></tr>
	<tr><td>Wednesday</td><td>09:00 to 19:00</td></tr>
	<tr><td>Thursday</td><td>09:00 to 19:00</td></tr>
	<tr><td>Friday</td><td>09:00 to 20:00</td></tr>
	<tr><td>Saturday</td><td>10:00 to 17:00</td></tr>
	<tr><td>Sunday</td><td>CLOSED</td></tr>
	</table>
	</td>
	
	<td style="	padding: 20px 20px 20px 20px;">
	<script src='https://maps.googleapis.com/maps/api/js?v=3.exp'></script>

	<div style='overflow:hidden;height:300px;width:380px;'>
	<div id='gmap_canvas' style='height:300px;width:380px;'></div>
	<div><small><a href="http://embedgooglemaps.com">embed google map</a></small></div>
	<div><small><a href="http://www.freedirectorysubmissionsites.com/">www.freedirectorysubmissionsites.com</a></small></div>
	<style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div>
	</td>
	</table>
	
	<script type='text/javascript'>
	function init_map()
	{
		var myOptions = {zoom:15,center:new google.maps.LatLng(51.621148, -0.062838),mapTypeId: google.maps.MapTypeId.ROADMAP};
		map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
		marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(51.621148, -0.062838)});
		infowindow = new google.maps.InfoWindow(
		{
			content:'<strong>address</strong><br>5 Brook Crescent, London, N9 0DJ<br>'
		});google.maps.event.addListener(marker, 'click', function()
		{
			infowindow.open(map,marker);
		});
		infowindow.open(map,marker);
	}
	google.maps.event.addDomListener(window, 'load', init_map);
	</script>
	
</div>

<div id="endpage" style="top: 730px;">
	<p> MSc Properties (c) </p>
</div>


</body>
</html>