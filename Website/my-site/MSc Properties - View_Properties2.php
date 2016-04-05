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

<div id="fullsearch" style="height: 1750px">
	<h3><strong>Property Search </strong></h3>
	
<form method="post" action="MSc Properties - View_Properties.php">
	
	<table border='0'>
	<tr><td><strong>City: </strong></td>
	<td><select name="town" id="town" size="1" >
	<option value="-">Select City</option>
	<?php
	
	include "msc_properties - Common_Functions.php";

	print cityDropDown();
	
	?>
	</td></tr></select>
	
	<tr><td><p><strong>Area: </strong></td>
	<td><select name="area" id="area" size="1" >
		<option value="-">Select Area</option>
	<?php
	
	print areaDropDown();
	
	?>
	</select></td></tr>
	
	<tr><td><strong>Min Price: <strong></td>
	<td><select name="min_price" size="1">
	<option value="-">Min Price</option>
	<?php
		for($i=2; $i<10; $i++) {
			print '<option value="'.$i.'00">£'.$i.'00 pcm</option>';
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
			print '<option value="'.$i.'00">£'.$i.'00 pcm</option>';
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
	</select></td></tr>
	
	<tr><td><strong>Type: </strong></td>
	<td><select name="type" id="type" size="1" >
	<option value="-">-</option>
	<?php
	
	print typeDropDown();
	
	?>
	</select></td></tr>
	
	<tr><td><strong>Sub Type: </strong></td>
	<td><select name="sub_type" id="sub_type" size="1" >
	<option value="-">-</option>
	<?php
	
	print subTypeDropDown();
	
	?>
	</select></td></tr>
	
	<tr><td><strong>Age: </strong></td>
	<td><select name="age" id="age" size="1" >
	<option value="-">-</option>
	<?php
	
	print ageDropDown();
	
	?>
	</select></td></tr>
	
	<tr><td><strong>Bedrooms: </strong></td>
	<td><select name="beds" id="beds" size="1" >
	<option value="-">-</option>
	<?php
		for($i=1; $i<=10; $i++) {
			print '<option value="'.$i.'">'.$i.' bed</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><strong>Driverway: </strong></td>
	<td><select name="driveway" id="driveway" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	</select></td></tr>
	
	<tr><td><strong>Garden: </strong></td>
	<td><select name="garden" id="garden" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	</select></td></tr>
	
	<tr><td><strong>Conservatory: </strong></td>
	<td><select name="conservatory" id="conservatory" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	</select></td></tr>
	
	<tr><td><strong>Bathrooms: </strong></td>
	<td><select name="baths" id="baths" size="1" >
	<option value="-">-</option>
	<?php
		for($i=1; $i<=5; $i++) {
			print '<option value="'.$i.'">'.$i.'</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><strong>Receptions: </strong></td>
	<td><select name="receptions" id="receptions" size="1" >
	<option value="-">-</option>
	<?php
		for($i=0; $i<=5; $i++) {
			print '<option value="'.$i.'">'.$i.'</option>';
		}
	?>
	</select></td></tr>
	
	<tr><td><p>&nbsp;</p></td>
	<td><input type="submit" value="Search" />
	</td></tr>
	</table>
	
	<p>
	
	</p>

</form>

</div>

<div id="viewproperties">
<h2><strong>Property Search<strong></h2>

<table border='1' cellpadding='10' >
	<col width="320">
	<col width="320"> 
		
	<?php
	
	$town = $_POST['town'];
	$area = $_POST['area'];
	$beds = $_POST['beds'];
	
	
	$min_price = '-';
	$max_price = '-';
	$kitchen = '-';
	$baths = '-';
	$driveway = '-';
	$garden = '-';
	$conservatory = '-';
	$receptions = '-';
	$type = '-';
	$sub_type = '-';
	$age = '-';
	
	$sql_statement  = "SELECT propRef, propTypeCode, propSubTypeCode, addressRef ";
	$sql_statement .= "FROM properties ";
	$sql_statement .= "WHERE propStatus='VOID' ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		print "<br />Error on DB<br><br />".$error_or_rows."<br /><br />".$sql_statement."<br /> ";
	}
	else
	{
		$returnedProps = 1;
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$outputDisplay = '';
			$propRef  = $sqlResults [$ii] ['propRef'];
			$propTypeCode  = $sqlResults [$ii] ['propTypeCode'];
			$propSubTypeCode  = $sqlResults [$ii] ['propSubTypeCode'];
			$propAddressRef  = $sqlResults [$ii] ['addressRef'];
			
			$string = searchProp($propRef, $propTypeCode, $propSubTypeCode, $propAddressRef, $town, $area, $min_price, $max_price, $kitchen, $beds, $baths, $driveway, $garden, $conservatory, $receptions, $type, $sub_type, $age, $returnedProps);
			
			if(!empty($string))
			{
				list($outputDisplay, $returnedProps) = explode('|', $string);
			}
			
			print $outputDisplay;
		}
		if((($returnedProps-1)%2)!==0)
		{
			$outputDisplay .= "</tr>";
		}
	}
	?>
	</table>
	</font>
</div>



<div id="endpage" style="top: 1980px;">
	<a href="http://localhost/assignment_6_files/assignment_6_guest_calc.php">Guest Book / Mortgage Calculator</a>
	<br /><br /><br /><br />
</div>


</body>
</html>