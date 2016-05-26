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

<div id="quicksearch">
	<h3><strong>Quick Search </strong></h3>
	
<form method="post" action="MSc Properties - View_Properties2.php">
	
	<table border='0'>
	<tr><td><strong>City: </strong></td>
	<td><select name="town" id="town" size="1" >
	<option value="-">-</option>
	
	<?php
	
	include "msc_properties - Common_Functions.php";

	print cityDropDown();
	
	?>
	</select>
	</td></tr>
	
	<tr><td><p><strong>Area: </strong></td>
	<td><select name="area" id="area" size="1" >
		<option value="-">-</option>
	<?php
	
	print areaDropDown();
	
	?>
	</select>
	</td></tr>
	
	<tr><td><strong>Beds: </strong></td>
	<td><select name="beds" id="beds" size="1" >
	<option value="-">-</option>
	<option value="Y">studio</option>
	<?php
		for($i=1; $i<=10; $i++) {
			print '<option value="'.$i.'">'.$i.' beds</option>';
		}
	?>
	</select>
	</td></tr>
	
	</table>
	
	<div align="right">
		<p>
		<input type="submit" value="Search" />
		</p>
	</div>

</form>

</div>

<div id="featuredhome">
	<h2><strong>Featured Home!</strong></h2>
	
	<table border='0'>
	<tr><td><img src="../featured_house/bayview_2.jpg" /></td>
	<td style="padding-left: 20px"><p><strong>As Far as the Eye Can See!</strong></p>
	<p>Spectacular Ocean and Canyon views!!<br />
	This large estate has room to entertain with<br />
	1200 sq. ft. "ballroom" that features modern<br />
	stone and wood work, vaulted ceilings and <br />
	huge bay windows.  Large Master Suites<br />
	featuring "His" and "Her" bathrooms. </p>
	</td></tr>
	</table>
	
</div>

<div id="whylet">
	<h2><strong>Why Let your property with MSc Properties?</strong></h2>
	
	<p>At MSc Properties we have unmatched knowledge about a number of Englands major cities property, ensuring landlords secure the best valuations for their rents and high quality tenants, at the right cost<br></p>
	
	<p>175,000 landlords have been using MSc Properties since 2012 to let and manage their properties. With outstanding service and exceptional value – including our 5.5% no let, no fee – MSc Properties is leading the lettings market in London, Manchester, Liverpool and growing.
	
	<a href="MSc Properties - Let.php" border="0"> Find out more here.</a> </p>
	
</div>

<div id="locatestore">
	
	<table border='0'>
	<tr><td><h2>Locate an Office</h2>
	<form method="post" action="MSc Properties - Office_Details.php">
	<strong>Office: <br></strong>
	<select name="office" id="office" size="1" >
	<option value="-">-</option>
	
	<?php
	
	print officeDropDown();
	
	?>
	</select>
	<div align="right">
		<p>
		<input type="submit" value="View Office" />
		</p>
	</div>

	</form>
	</td>
	<td style="padding-left: 20px"><img src="../images/office_front.jpg" height="120" width="180"/></td>
	</tr>
	</table>
</div>

<div id="careers">
	<table border='0'>
	<tr><td><h2>Careers/Vacancies</h2> 
	<p>Ever thought of a Career with<br>an Estate Agent?<br></p>
	<p><a href="MSc Properties - Careers.php" border="0"> Find out more here.</a> </p>
	</td>
	<td style="padding-left: 20px"><br><img src="../images/careers_logo2.jpg" height="120" width="180"/></td>
	</tr>
	</table>
</div>

<div id="endpage" style="top: 965px;">
	<p> MSc Properties (c) </p>
</div>


</body>
</html>