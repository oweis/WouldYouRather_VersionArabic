<?php

//                                             GESTION USER
//                                    ----------------------------------

function generate_user()
{
	$rad = rand(100, 999);
	date_default_timezone_set('GMT');
	$dt_now = date("ymdHis");
	$login = "user" . $dt_now . $rad;
	return $login;
}

function add_user()
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$login = generate_user();
	$sql = "INSERT INTO `user` (`id`, `login`, `UID`) VALUES (NULL, '$login', 'uidTest15456465')";
	if ($conn->query($sql) === TRUE) {
		$conn->close();
		echo "result:good;";
		return true;
	} else {
		$conn->close();
	   return false;
	}
}

function update_user_login($login_last,$login_new)
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	
	$sql = "UPDATE `user` SET login = $login_new WHERE login = $login_last ";

	if ($conn->query($sql) === TRUE) {
		echo "result:good;";
	} else {
		echo "Error updating record: " . $conn->error;
	}

	$conn->close();
	
}

//                                          GESTION QUESTIONS
//                                    ----------------------------------

function add_question($login,$question1,$question2,$adult)
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	$date = date("Y/m/d");
	$sql = "INSERT INTO `question` (`id`, `id_user`, `question1`, `question2`,`adult` ,`vote1`, `vote2`, `likes`, `date`) VALUES (NULL, '$login', '$question1', '$question2','$adult','0', '0', '0', '$date')";
	if ($conn->query($sql) === TRUE) {
		$conn->close();
		update_user_question($iduser);
		echo "result:good;";
		return true;
	} else {
		$conn->close();
	   return false;
	}
	
}

function question_like($idq)
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	$sql = "UPDATE `question` SET likes = likes + 1 WHERE id = $idq ";

	if ($conn->query($sql) === TRUE) {
		echo "updated quesion $idq like successfully";
	} else {
		echo "Error updating record: " . $conn->error;
	}

	$conn->close();

}

function get_question($id_user,$adult)
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 

	$sql = "SELECT q.*  FROM `question` q, `answer` a where q.id <> a.id_question and a.id_user = $id_user and q.adult = $adult";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		// output data of each row
		// $json = "[";
		$i = 0;
		$bigarray = array();
		 
		while($row = $result->fetch_assoc()) {
			
			// $json = $json . "{\"id_question\":" . $row["id"] . ",\"id_user\":" . $row["id_user"] .  ",\"question1\":" . $row["question1"] . ",\"question2\":" . $row["question2"] . ",\"vote1\":"  . $row["vote1"] 
					// . ",\"vote2\":"  . $row["vote2"] . ",\"likes\":"  . $row["likes"] . ",\"adult\":"  . $row["adult"] . "},"; 
			$c = array("id_question" => $row["id"],"id_user" => $row["id_user"],"question1" => $row["question1"],"question2" => $row["question2"],"vote1" => $row["vote1"],
					"vote2" => $row["vote2"],"likes" => $row["likes"],"adult" => $row["adult"]);
			//echo json_encode($c, JSON_FORCE_OBJECT);
			array_push($bigarray,$c);
		}
		//$arr3=array($arr1,$arr2);
		echo json_encode(array_values($bigarray));
		// $json = $json . "]";
		// echo $json;
	} else {
		echo "error no_rows selected";
	}
	$conn->close();	
	
}

//                                             GESTION ANSWER 
//                                    ----------------------------------

function set_answer($idq,$id_user,$answer,$like)
{
	include "db/db.php";
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	if($like == 1){
		question_like($idq);
	}
	$sql = "INSERT INTO `answer` (`id_question`, `id_user`, `answer`, `liked`) VALUES ('$idq', '$id_user', '$answer', '$like')";
	if ($conn->query($sql) === TRUE) {
		$conn->close();
		update_user_question($iduser);
		echo "result:good;";
		return true;
	} else {
		$conn->close();
	   return false;
	}
}




?>