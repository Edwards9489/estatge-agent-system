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

<div id="aboutus">
	
	<h2>About MSc Properties</h2>
	
	<p>What sets MSc Properties apart is our belief in modern work practices coupled with traditional, old-fashioned values like professionalism, quality service and an absolute commitment to keeping a promise.</p>
	
	<p>Our definition of good service is that when we say we are available at all hours of the day, we don’t just mean the answer phone is on for you to leave a message.  Members of our dedicated and experienced team are often in the office at daybreak and also work late into the night when required.</p>
	
	<p>The same holds true when it comes to property viewings.  Because we believe you should not have to take time off work to find or sell your home, we organise viewings at a time to suit you, whether that is first thing in the morning or last thing at night.</p>
	
</div>

<div id="locatestore2">
	
	<table border='0'>
	<tr><td><h2>Locate an Office</h2>
	<form method="post" action="MSc Properties - Office_Details.php">
	<strong>Office: </strong>
	<select name="office" id="office" size="1" >
	<option value="-">-</option>
	
	<?php
	
	include 'MSc_Properties - Common_Functions.php';
	
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
	<td style="padding-left: 40px">
	<p><br>MSc Properties have a number of offices<br>
	across most major cities in England, including<br>
	London, Manchester, Liverpool and more.</p></td>
	<td style="padding-left: 40px"><img src="../images/office_front.jpg" height="100" width="200"/></td>
	</tr>
	</table>
</div>

<div id="endpage" style="top: 630px;">
	<p> MSc Properties (c) </p>
</div>


</body>
</html>