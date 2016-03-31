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

<div id="fullsearch">
	<h3><strong>Property Search </strong></h3>
	
<form method="post" action="MSc Properties - View_Properties.php">
	
	<table border='0'>
	<tr><td><strong>City: </strong></td>
	<td><select name="town" id="town" size="1" >
	<option value="-">Select City</option>
	
	<?php
	
	include "msc_properties - Common_Functions.php";

	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT name ";
	$sql_statement .= "FROM city ";
	$sql_statement .= "ORDER BY name ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		print "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$name  = $sqlResults [$ii] ['name'];
			
			//print "<br>N: $name";
			
			$outputDisplay .= "<option value='".$name."'>".$ii.":".$name."</option>\n";
		}
	}
	
	print $outputDisplay;
	
	?>
	</td></tr></select>
	
	<tr><td><p><strong>Area: </strong></td>
	<td><select name="area" id="area" size="1" >
		<option value="-">Select Area</option>
	<?php
	
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT name ";
	$sql_statement .= "FROM city ";
	$sql_statement .= "ORDER BY name ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		print "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$name  = $sqlResults [$ii] ['name'];
			
			//print "<br>N: $name";
			
			$outputDisplay .= "<option value='".$name."'>".$ii.":".$name."</option>\n";
		}
	}
	
	print $outputDisplay;
	
	?>
	</select></td></tr>
	
	<tr><td><strong>Min Price: <strong></td>
	<td><select name="min_price" size="1">
	<option value="-">Min Price</option>
	<?php
		for($i=2; $i<10; $i++) {
			print '<option value="'.$i.'00">'.$i.'00 pcm</option>';
		}
	?>
	<option value="1000">£1000 pcm</option>
	<option value="1250">£1250 pcm</option>
	<option value="1500">£1500 pcm</option>
	<option value="1750">£1750 pcm</option>
	<option value="2000">£2000 pcm</option>
	<option value="2500+">£2500+ pcm</option>
	</select></td></tr>
	
	<tr><td><strong>Max Price: <strong></td>
	<td><select name="max_price" size="1">
	<option value="-">Max Price</option>
	<?php
		for($i=2; $i<10; $i++) {
			print '<option value="'.$i.'00">'.$i.'00 pcm</option>';
		}
	?>
	<option value="1000">£1000 pcm</option>
	<option value="1250">£1250 pcm</option>
	<option value="1500">£1500 pcm</option>
	<option value="1750">£1750 pcm</option>
	<option value="2000">£2000 pcm</option>
	<option value="2500">£2500+ pcm</option>
	</select></td></tr>
	
	<tr><td><strong>Kitchen: </strong></td>
	<td><select name="kitchen" id="kitchen" size="1" >
	<option value="-">-</option>
	<option value="S">Small</option>
	<option value="M">Medium</option>
	<option value="L">Large</option>
	<option value="XL">Extra Large</option>
	
	<tr><td><strong>Bedrooms: </strong></td>
	<td><select name="beds" id="beds" size="1" >
	<option value="-">-</option>
	<?php
		for($i=1; $i<=10; $i++) {
			print '<option value="'.$i.'">'.$i.' beds</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><strong>Bathrooms: </strong></td>
	<td><select name="baths" id="baths" size="1" >
	<option value="-">-</option>
	<?php
		for($i=0; $i<=5; $i++) {
			print '<option value="'.$i.'">'.$i.' beds</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><strong>Driverway: </strong></td>
	<td><select name="driveway" id="driveway" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	
	<tr><td><strong>Garden: </strong></td>
	<td><select name="garden" id="garden" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	
	<tr><td><strong>Conservatory: </strong></td>
	<td><select name="conservatory" id="conservatory" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	
	<tr><td><strong>Receptions: </strong></td>
	<td><select name="receptions" id="receptions" size="1" >
	<option value="-">-</option>
	<?php
		for($i=0; $i<=5; $i++) {
			print '<option value="'.$i.'">'.$i.' receptions</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><strong>Type: </strong></td>
	<td><select name="type" id="type" size="1" >
	<option value="-">-</option>
	<option value="house">House</option>
	<option value="flat">Flat</option>
	<option value="bungalow">Bungalow</option>
	<option value="maisonette">Maisonette</option>
	
	<tr><td><strong>Sub Type: </strong></td>
	<td><select name="sub_type" id="sub_type" size="1" >
	<option value="-">-</option>
	<option value="terrace">Terrace</option>
	<option value="detached">Detached</option>
	<option value="semi-detached">Semi-Detached</option>
	
	<tr><td><strong>Age: </strong></td>
	<td><select name="age" id="age" size="1" >
	<option value="-">-</option>
	<option value="new">New Build</option>
	<option value="victorian">Victorian</option>
	
	<tr><td><p>&nbsp;</p></td>
	<td><input type="submit" value="Search" />
	</td></tr>
	</table>
	
	<p>
	
	</p>

</form>

</div>

<div id="viewproperties">
	
	<?php
	
	$sql_statement  = "SELECT *";
	$sql_statement .= "FROM city ";
	
	$where_clause = "";
	
	$town = $_POST['town'];
	$area = $_POST['area'];
	$beds = $_POST['beds'];
	
	if ($town !== '-')
	{
		$where_clause .= "WHERE name='".$town."' ";
	}
	if ($area !== '-')
	{
		if(empty($where_clause))
		{
			$where_clause .= "WHERE area='".$area."' ";
		}
		else
		{
			$where_clause .= "AND area='".$area."' ";
		}
	}
	if ($beds !== '-')
	{
		if(empty($where_clause))
		{
			$where_clause .= "WHERE beds=".$beds." ";
		}
		else
		{
			$where_clause .= "AND beds=".$beds." ";
		}
	}
	$sql_statement .= $where_clause;
	$sql_statement .= "ORDER BY name ";
	
	$outputDisplay = '';
	
	
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		print "<br />Error on DB<br><br>".$error_or_rows."<br><br>".$sql_statement;
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			// Function to extract property info into String (with HTML tags) to display each property
			
			$name  = $sqlResults [$ii] ['name'];
			
			//print "<br>N: $name";
			
			$outputDisplay .= "<option value='".$name."'>".$ii.":".$name."</option>\n";
		}
	}
	
	print $outputDisplay;
	?>
</div>



<div id="endpage" style="top: 915px;">
	<a href="http://localhost/assignment_6_files/assignment_6_guest_calc.php">Guest Book / Mortgage Calculator</a>
	<br /><br /><br /><br />
</div>


</body>
</html>