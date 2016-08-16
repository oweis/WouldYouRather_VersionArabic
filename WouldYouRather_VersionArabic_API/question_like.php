<?php
include "functions.php";

if(!isset($_GET['id']) ) die("error Parameters");

extract($_GET);

question_like($id);

?>