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
		
		include "MSc_Properties - Common_Functions.php";

		$office = $_POST['office'];
		
		$address = '';
		
		
		$sql_statement = "SELECT buildingNumber, buildingName, streetNumber, ";
		$sql_statement .= "subStreetNumber, subStreet, street, area, ";
		$sql_statement .= "town, country, postcode ";
		$sql_statement .= "FROM  offices JOIN addresses ";
		$sql_statement .= "USING(addressRef) ";
		
		$sqlResults = selectResults($sql_statement);
		
		$error_or_rows = $sqlResults[0];
		
		if (substr($error_or_rows, 0 , 5) == 'ERROR')
		{
			print "<br />Error on DB<br><br>".$error_or_rows."<br><br>".$sql_statement;
		}
		else
		{
			for ($ii = 1; $ii <= $error_or_rows; $ii++)
			{
				$buildingNumber  = $sqlResults [$ii] ['buildingNumber'];
				$buildingName  = $sqlResults [$ii] ['buildingName'];
				$subStreetNumber  = $sqlResults [$ii] ['subStreetNumber'];
				$subStreet  = $sqlResults [$ii] ['subStreet'];
				$streetNumber  = $sqlResults [$ii] ['streetNumber'];
				$street  = $sqlResults [$ii] ['street'];
				$area  = $sqlResults [$ii] ['area'];
				$town  = $sqlResults [$ii] ['town'];
				$country  = $sqlResults [$ii] ['country'];
				$postcode  = $sqlResults [$ii] ['postcode'];
				
				if (isset($isBuildingNumber) && isset($buildingName))
				{
					$address .= $buildingNumber." ".$buildingName."<br>";
				}
				else if (isset($buildingName))
				{
					$address .= $buildingName."<br>";
				}
				
				if (isset($subStreetNumber) && isset($subStreet))
				{
					$address .= $subStreetNumber." ".$subStreet."<br>";
				}
				else if (isset($subStreet))
				{
					$address .= $subStreet."<br>";
				}
				
				if (isset($streetNumber) && isset($street))
				{
					$address .= $streetNumber." ".$street."<br>";
				}
				else if (isset($street))
				{
					$address .= $street."<br>";
				}
				
				if (isset($area))
				{
					$address .= $area."<br>";
				}
				
				if (isset($town))
				{
					$address .= $town."<br>";
				}
				
				if (isset($country))
				{
					$address .= $country."<br>";
				}
				
				if (isset($postcode))
				{
					$address .= $postcode."<br>";
				}
			}
		}
		
		$telephone = '';
		
		$sql_statement1 = "SELECT contactValue ";
		$sql_statement1 .= "FROM  officeContacts ";
		$sql_statement1 .= "WHERE officeCode='".$office."' ";
		
		$sqlResults1 = selectResults($sql_statement1);
		
		$error_or_rows1 = $sqlResults1[0];
		
		if (substr($error_or_rows1, 0 , 5) == 'ERROR')
		{
			print "<br />Error on DB<br><br>".$error_or_rows1."<br><br>".$sql_statement1;
		}
		else
		{
			for ($ii = 1; $ii <= $error_or_rows1; $ii++)
			{
				$telephone  = $sqlResults1 [$ii] ['contactValue'];
			}
		}
		
		
		print $address;
		print "<h2>MSc Properties - ".$office."<br>";
	?>
	<br>
	<table border='1'>
	<tr><th>Address</th><th>Oppening Times</th><th>Location</th></tr>
	<tr><td style="	padding: 20px 20px 20px 20px;" valign="top">
	
	<p>5 Brook Crescent<br>
	Edmonton<br>
	London<br>
	N9 0DJ</p>
	<p>Tel: 020 8123 456</p>
	<p>E-Mail: test@msc-properties.com</p>
	
	
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

<div id="endpage" style="top: 735px;">
	<a href="http://localhost/assignment_6_files/assignment_6_guest_calc.php">Guest Book / Mortgage Calculator</a>
	<br /><br /><br /><br />
</div>


</body>
</html>