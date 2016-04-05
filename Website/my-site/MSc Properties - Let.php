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

<div id="letting">
	<h2>Letting your Property in England?</h2>
	
	<p>MSc Properties understands the need to achieve the best possible rent and prevent voids to maintain yields for its landlords.  We know that as a landlord you want a responsible tenant who will respect your property investment.<br>
	</p>
	
	<p>We have numerous private and corporate tenants listed at any one time, most of who are looking to move quickly.  That is why so many of our landlords rely on us to let their properties year after year.<br>
	</p>
	
	<p>When a regular Estate Agent finds a tenant for your property they should do the usual “in house” referencing process which entails obtaining a previous landlords reference, an employer’s reference, proof of ID & income etc.<br>
	</p>
	
	<p>We go one step further and outsource to an independent insurance company who run a Credit Check on the applicant to the same standard as if they are applying for a credit card or a mortgage, and we get a much more in depth understanding of their financial standing and credit history. Once the insurance company have rubber stamped the applicant as a “good tenant” we then pay the insurance company a premium to insure the tenant for the duration of the tenancy, which gives you 3 elements of comfort:<br>
	</p>
	
	<p>1)    You know that the tenant we introduce has been vetted to a high standard, not only by us, but also by an independent Insurance Company,<br>2)    The Insurance company insures you in the event that the Tenant doesn’t pay their rent for any reason, then the insurance company will pay you instead,<br>3)    If the tenant makes the transition from being a “good” tenant to a “bad” tenant - they also deal with the whole eviction process for you as well, - at their cost.<br>
	</p>
	
	<p>We effectively offer a total “peace of mind” package to our clients who can rest assured that once they have signed a tenancy agreement with an applicant introduced by us they can put the matter to bed for 12 months and relax knowing that there should be no problems!<br>
	</p>
	
	<p>Please advise when you have a property available by registering (non registered Landlords) and we would be delighted to market it for you. MSc Properties have a number of offices and actively cover a large number of major cities in England, including London, Manchester, Liverpool and more.<br>
	</p>
</div>

<div id="register2">

	<h2>To Let a Property... Register with us now.</h2>
	
	<form method="post" action="MSc_Properties - Properties.php">
	
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
	
	<tr><td><p><strong>Office: </strong></td>
	<td><select name="office" id="office" size="1" >
		<option value="-">Select Office</option>
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
	
	<tr><td><strong>Driveway: </strong></td>
	<td><select name="driveway" id="driveway" size="1" >
	<option value="-">-</option>
	<option value="Y">Yes</option>
	<option value="N">No</option>
	
	
	</table>
	
	
	<div align="right">
		<p>
		<input type="submit" value="Submit" />
		</p>
	</div>

</form>
</div>

<div id="endpage" style="top: 965px;">
	<a href="http://localhost/assignment_6_files/assignment_6_guest_calc.php">Guest Book / Mortgage Calculator</a>
	<br /><br /><br /><br />
</div>


</body>
</html>