<?php


function connectDatabase()
{
	error_reporting(E_ALL ^ E_DEPRECATED);
	
	//**********************************************
	//*
	//*  Connect to MySQL and Database
	//*
	//**********************************************

	$db = mysql_connect('localhost','root','');

	if (!$db)
	{
		print "<h1>Unable to Connect to MySQL</h1>";
	}

	$dbname = 'msc_propertieslive';

	$btest = mysql_select_db($dbname);

	if (!$btest)
	{
		print "<h1>Unable to Select the Database</h1>";
	}

	return $db;
}


function selectResults($statement)
{

	$output = "";
	$outputArray = array();

	$db = connectDatabase();

	if ($db)
	{
		$result = mysql_query($statement);

		if (!$result) {
			$output .= "ERROR";
			$output .= "<br /><font color=red>MySQL No: ".mysql_errno();
			$output .= "<br />MySQL Error: ".mysql_error();
			$output .= "<br />SQL Statement: ".$statement;
			$output .= "<br />MySQL Affected Rows: ".mysql_affected_rows()."</font><br />";

			array_push($outputArray, $output);

		} else {

			$numresults = mysql_num_rows($result);

			array_push($outputArray, $numresults);

			for ($i = 0; $i < $numresults; $i++)
			{
				$row = mysql_fetch_array($result);

				array_push($outputArray, $row);
			}
		}

	} else {

		array_push($outputArray, 'ERROR-No DB Connection');

	}

	return $outputArray;
}


function iduResults($statement)
{

	$output = "";
	$outputArray = array();

	$db = connectDatabase();

	if ($db)
	{
		$result = mysql_query($statement);

		if (!$result) {
			$output .= "ERROR";
			$output .= "<br /><font color=red>MySQL No: ".mysql_errno();
			$output .= "<br />MySQL Error: ".mysql_error();
			$output .= "<br />SQL Statement: ".$statement;
			$output .= "<br />MySQL Affected Rows: ".mysql_affected_rows()."</font><br />";

		} else {
			$output = mysql_affected_rows();
		}

	} else {

		$output =  'ERROR-No DB Connection';

	}

	return $output;
}


function cityDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT DISTINCT town ";
	$sql_statement .= "FROM addresses ";
	$sql_statement .= "WHERE addressRef IN ( ";
	$sql_statement .= "SELECT addressRef ";
	$sql_statement .= "FROM properties) ";
	$sql_statement .= "ORDER BY town ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$town  = $sqlResults [$ii] ['town'];
			
			$outputDisplay .= "<option value='".$town."'>".$town."</option>\n";
		}
	}
	
	return $outputDisplay;
}


function areaDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT DISTINCT area ";
	$sql_statement .= "FROM addresses ";
	$sql_statement .= "WHERE addressRef IN ( ";
	$sql_statement .= "SELECT addressRef ";
	$sql_statement .= "FROM properties) ";
	$sql_statement .= "ORDER BY area ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$area  = $sqlResults [$ii] ['area'];
			
			$outputDisplay .= "<option value='".$area."'>".$area."</option>\n";
		}
	}
	
	return $outputDisplay;
}

function officeDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT town, area ";
	$sql_statement .= "FROM addresses ";
	$sql_statement .= "WHERE addressRef IN ( ";
	$sql_statement .= "SELECT addressRef ";
	$sql_statement .= "FROM offices ";
	$sql_statement .= "WHERE endDate IS NULL OR endDate > now()) ";
	$sql_statement .= "ORDER BY town, area ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$town  = $sqlResults [$ii] ['town'];
			$area  = $sqlResults [$ii] ['area'];
			
			$outputDisplay .= "<option value='".$town." - ".$area."'>".$town." - ".$area."</option>\n";
		}
	}
	
	return $outputDisplay;
}

function typeDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT code, description ";
	$sql_statement .= "FROM propertytypes ";
	$sql_statement .= "WHERE cur=1 ";
	$sql_statement .= "ORDER BY description ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$code  = $sqlResults [$ii] ['code'];
			$description  = $sqlResults [$ii] ['description'];
			
			$outputDisplay .= "<option value='".$code."'>".$description."</option>\n";
		}
	}
	
	return $outputDisplay;
}

function subTypeDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT code, description ";
	$sql_statement .= "FROM propertysubtypes ";
	$sql_statement .= "WHERE cur=1 ";
	$sql_statement .= "ORDER BY description ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$code  = $sqlResults [$ii] ['code'];
			$description  = $sqlResults [$ii] ['description'];
			
			$outputDisplay .= "<option value='".$code."'>".$description."</option>\n";
		}
	}
	
	return $outputDisplay;
}

function ageDropDown()
{
	$outputDisplay = '';
	
	
	$sql_statement  = "SELECT DISTINCT stringValue ";
	$sql_statement .= "FROM propertyelementvalues ";
	$sql_statement .= "WHERE elementCode='AGE' ";
	$sql_statement .= "ORDER BY stringValue ";
	
	$sqlResults = selectResults($sql_statement);
	
	$error_or_rows = $sqlResults[0];
	
	if (substr($error_or_rows, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB";
	} else {
		
		for ($ii = 1; $ii <= $error_or_rows; $ii++)
		{
			$value  = $sqlResults [$ii] ['stringValue'];
			
			$outputDisplay .= "<option value='".$value."'>".$value."</option>\n";
		}
	}
	
	return $outputDisplay;
}

function printAddress($buildingNumber, $buildingName, $subStreetNumber, $subStreet, $streetNumber, $street, $area, $town, $country, $postcode)
{
	$address = '';
	if (!(empty($buildingNumber) && empty($buildingName)))
	{
        $address .= $buildingNumber." ".$buildingName."<br>";
	}
	else if (empty($buildingNumber) && !empty($buildingName))
	{
		$address .= $buildingName."<br>";
	}
	
	if (!(empty($subStreetNumber) && empty($subStreet)))
	{
		$address .= $subStreetNumber." ".$subStreet."<br>";
	}
	else if (empty($subStreetNumber) && !empty($subStreet))
	{
		$address .= $subStreet."<br>";
	}

	if (!(empty($streetNumber) && empty($street)))
	{
		$address .= $streetNumber." ".$street."<br>";
	}
	else if (empty($streetNumber) && !empty($street))
	{
		$address .= $street."<br>";
	}

	if (!empty($area))
	{
		$address .= $area."<br>";
	}

	if (!empty($town))
	{
		$address .= $town."<br>";
	}

	if (!empty($country))
	{
		$address .= $country."<br>";
	}
	
	if (!empty($postcode))
	{
		$address .= $postcode."";
	}
	
	return $address;
}

function printAddressLine($buildingNumber, $buildingName, $subStreetNumber, $subStreet, $streetNumber, $street, $area, $town, $country, $postcode)
{
	$address = '';
	if (!(empty($buildingNumber) && empty($buildingName)))
	{
        $address .= $buildingNumber." ".$buildingName.", ";
	}
	else if (empty($buildingNumber) && !empty($buildingName))
	{
		$address .= $buildingName.", ";
	}
	
	if (!(empty($subStreetNumber) && empty($subStreet)))
	{
		$address .= $subStreetNumber." ".$subStreet.", ";
	}
	else if (empty($subStreetNumber) && !empty($subStreet))
	{
		$address .= $subStreet.", ";
	}

	if (!(empty($streetNumber) && empty($street)))
	{
		$address .= $streetNumber." ".$street.", ";
	}
	else if (empty($streetNumber) && !empty($street))
	{
		$address .= $street.", ";
	}

	if (!empty($area))
	{
		//$address .= $area.", ";
	}

	if (!empty($town))
	{
		//$address .= $town.", ";
	}

	if (!empty($country))
	{
		//$address .= $country.", ";
	}
	
	if (!empty($postcode))
	{
		$address .= $postcode."";
	}
	
	return $address;
}

function searchProp($propRef, $propTypeCode, $propSubTypeCode, $propAddressRef, $town, $area, $min_price, $max_price, $kitchen, $beds, $baths, $driveway, $garden, $conservatory, $receptions, $type, $sub_type, $age, $returnedProps)
{
	$outputDisplay = '';
	$returnProp = true;
			
	$sql_statement1  = "SELECT buildingNumber, buildingName, ";
	$sql_statement1 .= "subStreetNumber, subStreet, streetNumber, ";
	$sql_statement1 .= "street, area, town, country, postcode ";
	$sql_statement1 .= "FROM addresses ";
	$sql_statement1 .= "WHERE addressRef = ".$propAddressRef;
	
	$sqlResults1 = selectResults($sql_statement1);

	$error_or_rows1 = $sqlResults1[0];
	
	if (substr($error_or_rows1, 0 , 5) == 'ERROR')
	{
		print "<br />Error on DB<br><br>".$error_or_rows1."<br><br>".$sql_statement1;
	}
	else
	{
		$propBuildingNumber  = $sqlResults1 [1] ['buildingNumber'];
		$propBuildingName  = $sqlResults1 [1] ['buildingName'];
		$propSubStreetNumber  = $sqlResults1 [1] ['subStreetNumber'];
		$propSubStreet  = $sqlResults1 [1] ['subStreet'];
		$propStreetNumber  = $sqlResults1 [1] ['streetNumber'];
		$propStreet = $sqlResults1 [1] ['street'];
		$propArea = $sqlResults1 [1] ['area'];
		$propTown  = $sqlResults1 [1] ['town'];
		$propCountry  = $sqlResults1 [1] ['country'];
		$propPostcode  = $sqlResults1 [1] ['postcode'];
		
		if ($town !== '-' && $town !== $propTown)
		{
			$returnProp = false;
		}
		
		if ($area !== '-' && $area !== $propArea && $returnProp)
		{
			$returnProp = false;
		}
		
		if ($returnProp)
		{
			$sql_statement2  = "SELECT sum(doubleValue) AS doubleValue ";
			$sql_statement2 .= "FROM propertyElementValues ";
			$sql_statement2 .= "WHERE propRef = ".$propRef." ";
			$sql_statement2 .= "AND endDate IS NULL OR endDate > now()";
			$sql_statement2 .= "AND startDate <= now() ";
			
			$sqlResults2 = selectResults($sql_statement2);
	
			$error_or_rows2 = $sqlResults2[0];
			
			if (substr($error_or_rows2, 0 , 5) == 'ERROR')
			{
				print "<br />Error on DB<br><br>".$error_or_rows2."<br><br>".$sql_statement2;
			}
			else
			{
				$propPrice = $sqlResults2 [1] ['doubleValue'];
				
				if ($min_price !== '-' && $max_price !== '-' && $min_price > $propPrice && $max_price < $propPrice)
				{
					$returnProp = false;
				}
				else if($min_price !== '-' && $min_price > $propPrice)
				{
					$returnProp = false;
				}
				else if($max_price !== '-' && $max_price < $propPrice)
				{
					$returnProp = false;
				}
				
				if ($returnProp)
				{
					$sql_statement3  = "SELECT stringValue ";
					$sql_statement3 .= "FROM propertyElementValues ";
					$sql_statement3 .= "WHERE propRef = ".$propRef." ";
					$sql_statement3 .= "AND elementCode='KITC' ";
					$sql_statement3 .= "AND startDate <= now() ";
					$sql_statement3 .= "AND endDate IS NULL OR endDate > now() ";
					
					$sqlResults3 = selectResults($sql_statement3);
			
					$error_or_rows3 = $sqlResults3[0];
					
					if (substr($error_or_rows3, 0 , 5) == 'ERROR')
					{
						print "<br />Error on DB<br><br>".$error_or_rows3."<br><br>".$sql_statement3;
					}
					else
					{
						$propKitchen = $sqlResults3 [1] ['stringValue'];
						if($kitchen !== '-' && $propKitchen !== $kitchen)
						{
							$returnProp = false;
						}
						if($returnProp)
						{
							$sql_statement4  = "SELECT stringValue ";
							$sql_statement4 .= "FROM propertyElementValues ";
							$sql_statement4 .= "WHERE propRef = ".$propRef." ";
							$sql_statement4 .= "AND elementCode='BEDS' ";
							$sql_statement4 .= "AND startDate <= now() ";
							$sql_statement4 .= "AND endDate IS NULL OR endDate > now() ";
							
							$sqlResults4 = selectResults($sql_statement4);
					
							$error_or_rows4 = $sqlResults4[0];
							
							if (substr($error_or_rows4, 0 , 5) == 'ERROR')
							{
								print "<br />Error on DB<br><br>".$error_or_rows4."<br><br>".$sql_statement4;
							}
							else
							{
								$propBeds = $sqlResults4 [1] ['stringValue'];
								if ($beds !== '-' && $propBeds !== $beds)
								{
									$returnProp = false;
								}
								
								if($returnProp)
								{
									$sql_statement5  = "SELECT stringValue ";
									$sql_statement5 .= "FROM propertyElementValues ";
									$sql_statement5 .= "WHERE propRef = ".$propRef." ";
									$sql_statement5 .= "AND elementCode='BATHS' ";
									$sql_statement5 .= "AND startDate <= now() ";
									$sql_statement5 .= "AND endDate IS NULL OR endDate > now() ";
									
									$sqlResults5 = selectResults($sql_statement5);
							
									$error_or_rows5 = $sqlResults5[0];
									
									if (substr($error_or_rows5, 0 , 5) == 'ERROR')
									{
										print "<br />Error on DB<br><br>".$error_or_rows5."<br><br>".$sql_statement5;
									}
									else
									{
										$propBaths = $sqlResults5 [1] ['stringValue'];
										if ($baths !== '-' && $propBaths !== $baths)
										{
											$returnProp = false;
										}
										
										if($returnProp)
										{
											$sql_statement6  = "SELECT stringValue ";
											$sql_statement6 .= "FROM propertyElementValues ";
											$sql_statement6 .= "WHERE propRef = ".$propRef." ";
											$sql_statement6 .= "AND elementCode='DRIV' ";
											$sql_statement6 .= "AND startDate <= now() ";
											$sql_statement6 .= "AND endDate IS NULL OR endDate > now() ";
											
											$sqlResults6 = selectResults($sql_statement6);
									
											$error_or_rows6 = $sqlResults6[0];
											
											if (substr($error_or_rows6, 0 , 5) == 'ERROR')
											{
												print "<br />Error on DB<br><br>".$error_or_rows6."<br><br>".$sql_statement6;
											}
											else
											{
												$propDriveway = $sqlResults6 [1] ['stringValue'];
												
												if ($driveway !== '-' && $propDriveway !== $driveway)
												{
													$returnProp = false;
												}
												
												if ($returnProp)
												{
													$sql_statement7  = "SELECT stringValue ";
													$sql_statement7 .= "FROM propertyElementValues ";
													$sql_statement7 .= "WHERE propRef = ".$propRef." ";
													$sql_statement7 .= "AND elementCode='GRDN' ";
													$sql_statement7 .= "AND startDate <= now() ";
													$sql_statement7 .= "AND endDate IS NULL OR endDate > now() ";
													
													$sqlResults7 = selectResults($sql_statement7);
											
													$error_or_rows7 = $sqlResults7[0];
													
													if (substr($error_or_rows7, 0 , 5) == 'ERROR')
													{
														print "<br />Error on DB<br><br>".$error_or_rows7."<br><br>".$sql_statement7;
													}
													else
													{
														$propGarden = $sqlResults7 [1] ['stringValue'];
														if ($garden !== '-' && $propGarden !== $garden)
														{
															$returnProp = false;
														}
														
														if ($returnProp)
														{
															$sql_statement8  = "SELECT stringValue ";
															$sql_statement8 .= "FROM propertyElementValues ";
															$sql_statement8 .= "WHERE propRef = ".$propRef." ";
															$sql_statement8 .= "AND elementCode='CONSV' ";
															$sql_statement8 .= "AND startDate <= now() ";
															$sql_statement8 .= "AND endDate IS NULL OR endDate > now() ";
															
															$sqlResults8 = selectResults($sql_statement8);
													
															$error_or_rows8 = $sqlResults8[0];
															
															if (substr($error_or_rows8, 0 , 5) == 'ERROR')
															{
																print "<br />Error on DB<br><br>".$error_or_rows8."<br><br>".$sql_statement8;
															}
															else
															{
																$propConservatory = $sqlResults8 [1] ['stringValue'];
														
																if ($conservatory !== '-' && $propConservatory !== $conservatory)
																{
																	$returnProp = false;
																}
																
																if ($returnProp)
																{
																	$sql_statement9  = "SELECT stringValue ";
																	$sql_statement9 .= "FROM propertyElementValues ";
																	$sql_statement9 .= "WHERE propRef = ".$propRef." ";
																	$sql_statement9 .= "AND elementCode='RECP' ";
																	$sql_statement9 .= "AND startDate <= now() ";
																	$sql_statement9 .= "AND endDate IS NULL OR endDate > now() ";
																	
																	$sqlResults9 = selectResults($sql_statement9);
															
																	$error_or_rows9 = $sqlResults9[0];
																	
																	if (substr($error_or_rows9, 0 , 5) == 'ERROR')
																	{
																		print "<br />Error on DB<br><br>".$error_or_rows9."<br><br>".$sql_statement9;
																	}
																	else
																	{
																		$propReceptions = $sqlResults9 [1] ['stringValue'];
																		if ($receptions !== '-' && $propReceptions !== $receptions)
																		{
																			$returnProp = false;
																		}
																		
																		if ($returnProp)
																		{
																			$sql_statement10  = "SELECT stringValue ";
																			$sql_statement10 .= "FROM propertyElementValues ";
																			$sql_statement10 .= "WHERE propRef = ".$propRef." ";
																			$sql_statement10 .= "AND elementCode='AGE' ";
																			$sql_statement10 .= "AND startDate <= now() ";
																			$sql_statement10 .= "AND endDate IS NULL OR endDate > now() ";
																			
																			$sqlResults10 = selectResults($sql_statement10);
																	
																			$error_or_rows10 = $sqlResults10[0];
																			
																			if (substr($error_or_rows10, 0 , 5) == 'ERROR')
																			{
																				print "<br />Error on DB<br><br>".$error_or_rows10."<br><br>".$sql_statement10;
																			}
																			else
																			{
																				$propAge = $sqlResults10 [1] ['stringValue'];
																				if ($age !== '-' && $propAge !== $age)
																				{
																					$returnProp = false;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}	
											}
										}
									}
								}
								
							}
						}
					}
				}
			}
		}
	}
	if ($returnProp)
	{
		if((($returnedProps - 1)%2)==0)
		{
			$outputDisplay .= "<tr>";
		}
		$outputDisplay .= "<td>\n";
		$address = printAddressLine($propBuildingNumber, $propBuildingName, $propSubStreetNumber, $propSubStreet, $propStreetNumber, $propStreet, $propArea, $propTown, $propCountry, $propPostcode);
		
		$outputDisplay .= "<strong>".$address."</strong><br><br>\n";
		
		$outputDisplay .= "<table border='0'>\n";
		$outputDisplay .= "<tr><td>\n";

		$formatted_price = number_format($propPrice);
		$outputDisplay .= "Rent: £".$formatted_price." pcm"."<br />\n";
		$outputDisplay .= "Beds / Baths: ".$propBeds." / ".$propBaths."<br />\n";
		$outputDisplay .= "Kitchen: ".$propKitchen."<br />\n";
		$outputDisplay .= "Driveway: ".$propDriveway."<br />\n";
		$outputDisplay .= "Garden: ".$propGarden."<br />\n";
		$outputDisplay .= "Conservatory: ".$propConservatory."<br />\n";
		$outputDisplay .= "Receptions: ".$propReceptions."<br />\n";
		$outputDisplay .= "Age: ".$propAge."<br />";
		$outputDisplay .= "Type: ".$propTypeCode."<br />\n";
		$outputDisplay .= "Sub Type: ".$propSubTypeCode."<br />\n";
		
		$outputDisplay .= "</td>\n";
		
		$outputDisplay .= '<td style="padding-left: 20px"><img src="../featured_house/bayview_2.jpg" height="120" width="120"/></td>';
		$outputDisplay .= "<tr>\n</table>\n</td>";
		
		if(($returnedProps%2)==0)
		{
			$outputDisplay .= "</tr>";
		}
		$returnedProps++;
		return $outputDisplay."|".$returnedProps;
	}
}

function getOfficeContact($officeCode, $contactType)
{
	$sql_statement1 = "SELECT contactValue ";
	$sql_statement1 .= "FROM  officeContacts ";
	$sql_statement1 .= "WHERE officeCode='".$officeCode."' ";
	$sql_statement1 .= "AND contactTypeCode='".$contactType."' ";
	
	$sqlResults1 = selectResults($sql_statement1);
	
	$error_or_rows1 = $sqlResults1[0];
	
	if (substr($error_or_rows1, 0 , 5) == 'ERROR')
	{
		return "<br />Error on DB<br><br>".$error_or_rows1."<br><br>".$sql_statement1;
	}
	else
	{
		return $sqlResults1 [1] ['contactValue'];
	}
}


?>