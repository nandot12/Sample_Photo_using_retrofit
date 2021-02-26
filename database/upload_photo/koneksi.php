<?php

$hostname = "localhost";
$username = "root";
$password = "";
$database = "upload_photo";

$connect = mysqli_connect($hostname, $username, $password, $database);

// if (mysqli_connect_errno()){
//     echo "Failed connect to MySQL : " . mysqli_connect_error();
//     die();
// }else{
//     echo "Success connect to MySQL";
// }

?>