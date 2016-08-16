<?php
include "functions.php";

if(!isset($_GET['id_user']) || !isset($_GET['adult'])) die("error Parameters");

extract($_GET);

get_question($id_user,$adult);

?>