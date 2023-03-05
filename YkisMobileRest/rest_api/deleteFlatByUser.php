<?php
$response = array();

include_once "GeneralFunctions.php";

if (isset($_POST['user_id']) &&
    !empty($_POST['user_id']) &&
    isset($_POST['address_id']) &&
    !empty($_POST['address_id']) &&
    isset($_POST['token']) &&
    !empty($_POST['token']))   {
    $address_id = $_POST['address_id'];
    $user_id = $_POST['user_id'];
    $token = $_POST['token'];
    $dbOperationsObject = new DBOperations();
    $generalFunctionsObject = new GeneralFunctionsClass();

        $result = $dbOperationsObject->deleteFlatByUser($address_id , $user_id);
        $results = $generalFunctionsObject->deleteFlatByUser($result);
        $response["success"] = $results[0]["success"];
        $response["message"] = $results[0]["message"];
        $response["address"] = array();
        echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing getStreetsFromBlock";
    $response["address"] = array();
    echo json_encode($response);
}

?>
