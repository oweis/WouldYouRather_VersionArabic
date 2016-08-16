<?php
include "functions.php";

if(!isset($_GET['uid']) ) die("error Parameters");

extract($_GET);

add_user($uid);

?>