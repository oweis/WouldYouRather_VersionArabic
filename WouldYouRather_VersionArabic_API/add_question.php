<?php
include "functions.php";

if(!isset($_GET['id']) ||  !isset($_GET['question1']) || !isset($_GET['question2']) || !isset($_GET['adult']) ) die("error Parameters");

extract($_GET);

add_question($id,$question1,$question2,$adult);

?>