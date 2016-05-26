<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>MSc Properties</title>

	<link rel="stylesheet" type="text/css" href="../css/msc_properties.css" />
	
	<script>

		function validateForm()
		{
			var errmsg = '';

			var firstname = document.getElementById('firstname').value;

			if (firstname == null || firstname == "")
			{
			  errmsg += "<br />You must enter a Firstname";
  			}
			
			var lastname = document.getElementById('lastname').value;

			if (lastname == null || lastname == "")
			{
			  errmsg += "<br />You must enter a Lastname";
  			}
			
			var phone = document.getElementById('phone').value;

			if (phone == null || phone == "")
			{
			  errmsg += "<br />You must enter a Phone Number";
  			}
			
			var beds = document.getElementById('beds').value;

			if (beds == null || beds == "-")
			{
			  errmsg += "<br />You must select the number of Beds required";
			  document.write('TEST 4');
  			}
			
			var office = document.getElementById('office').value;

			if (office == null || office == "-")
			{
			  errmsg += "<br />You must select the Office";
			  document.write('TEST 5');
  			}
			
			/* 

			var age = document.getElementById('age').value;

			if (age == null || age == "")
			{
			  errmsg += "<br />You must enter your Age";

  			} else {

  				if (isNaN(age))
  				{
					errmsg += "<br />Age must be a number";
  				}

  			} */

			if (errmsg == '')
			{
				document.write('TRUE');
				return true;
				
			} else {
				var registerDiv = document.getElementById('register');
				registerDiv.innerHTML .= "<br><br>".errmsg;
				document.write('FALSE');
				return false;
			}
		}

	</script>

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

<div id="fullsearch" style="height: 520px">
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

</form>

</div>


<div id="lookingtorent">
	
	<h2>Looking to Rent a Property in England?</h2>
	
	<p><br>MSc Properties has a vast supply of rental properties across London, Manchester, Liverpool, and more, on its books.  Our landlords value responsible tenants so if you’re looking for an apartment or house to rent you have come to the right place!<br>
	</p>
	
	<p>When you rent through MSc Properties you have the security of knowing your deposit is protected under a Tenancy Deposit Scheme.<br>
	</p>
	
	<p>Find what you are looking for, wheter it’s a studio apartment or a five-bedroom family home… We have properties available.<br>
	</p>
	
</div>

<div id="register">
	<h2>To Rent a Property... Register with us now.</h2>
	
	<form method="post" action="MSc Properties - Send E-Mail.php">
	
	<table border='0'>
	
	<tr><td><strong>First Name: <strong></td>
	<td><input type="text" name="firstname" id="firstname" size="17" /></td></tr>

	<tr><td><strong>Last Name: <strong></td>
	<td><input type="text" name="lastname" id="lastname" size="17"/></td></tr>
	
	<tr><td><strong>E:Mail: </strong></td>
	<td><textarea name="email" cols="15" rows="2"></textarea>
	</td></tr>
	
	<tr><td><strong>Phone: <strong></td>
	<td><input type="text" name="phone" id="phone" size="17"/></td></tr>
	
	<tr><td><strong>Bedrooms: </strong></td>
	<td><select name="beds" id="beds" size="1" >
	<option value="-">-</option>
	<?php
		for($i=0; $i<=5; $i++) {
			print '<option value="'.$i.'">'.$i.' beds</option>';
		}
	?>
	</select></td></tr>
	
	
	</table>
	
	<br><strong>Office: </strong><br>
	<select name="office" id="office" size="1" >
	<option value="-">Select Office</option>
	<?php
	
	print officeDropDown();
	
	?>
	</select>
	
	<div align="right">
		<p>
		<input type="submit" value="Submit" onclick="return validateForm();" />
		<input type="reset" />
		</p>
	</div>

	</form>
</div>

<div style="color: red;" id="messagediv">
</div>

<div id="endpage" style="top: 755px;">
	<p> MSc Properties (c) </p>
</div>


</body>
</html>