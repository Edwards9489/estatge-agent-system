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

<div id="email">

	<h3><strong>Confirmation</strong></h3>
	<p> <br>Thank you for registering with MSc Properties </p>
	<p> You will be contacted within 3 working days to arrange an interview <br> <br> </p>
	<div>
	<a href="MSc Properties - Home.php" border="0">
	<p> Go Back Home <br> <br> <br> <br> <br> </p>
	</a>
	</div>
	
	<?php
		include "MSc_Properties - Common_Functions.php";
		
		$firstname = $_POST['firstname'];
		$lastname = $_POST['lastname'];
		$email = $_POST['email'];
		$phone = $_POST['phone'];
		$beds = $_POST['beds'];
		$office = $_POST['office'];
		
		$town = '';
		$area = '';
		
		if ($office !== '-')
		{
			list($town, $area) = explode('-', $office);
			$town = trim($town);
			$area = trim($area);
		}
		
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
			
			$emailTo = getOfficeContact($officeCode, 'EMAIL');
			
			$emailFrom = "online@msc_properties.com";
			
			$subject = $firstname." ".$lastname." RENTING INTEREST";
			
			$message = $firstname." ".$lastname." showed an interest in Renting<br><br>";
			$message .= "E-Mail: ".$email."<br>";
			$message .= "Tel: ".$phone."<br>";
			$message .= "Beds: ".$beds."<br>";
			
			//mail($emailTo, $subject, $message); - need to have local SMTP server running
			
			
			require('../PHPMailer/PHPMailerAutoload.php');
			
			$mail=new PHPMailer();
			$mail->CharSet = 'UTF-8';

			$body = $message;

			$mail->IsSMTP();
			$mail->Host       = 'smtp.gmail.com';

			$mail->SMTPSecure = 'tls';
			$mail->Port       = 587;
			$mail->SMTPDebug  = 1;
			$mail->SMTPAuth   = true;

			$mail->Username   = 'mscproperties.online@gmail.com';
			$mail->Password   = 'Toxic9489';

			$mail->SetFrom('mscproperties.online@gmail.com', 'test');
			$mail->AddReplyTo('no-reply@mycomp.com','no-reply');
			$mail->Subject    = $subject;
			$mail->MsgHTML($body);

			$mail->AddAddress('dwayne.edwards9489@outlook.com', 'Renting'); /* ... */
			
			$mail->send();
		}
	?>
	
	
</div>

<div id="endpage" style="top: 535px;">
	<p> MSc Properties (c) </p>
</div>


</body>
</html>