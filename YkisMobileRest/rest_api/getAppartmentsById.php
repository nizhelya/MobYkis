<?php
$response = array();

include_once "GeneralFunctions.php";


if (isset($_POST['address_id']) && !empty($_POST['address_id'])) {
    $address_id = $_POST['address_id'];
    $dbOperationsObject = new DBOperations();
    $generalFunctionsObject = new GeneralFunctionsClass();

        $resultAppartments = $dbOperationsObject->getAppartmentsById($address_id);
        $appartments = $generalFunctionsObject->getAppartmentsById($resultAppartments);

        $response["success"] = 1;
        $response["message"] = "Успешно";
        $response["appartments"] = $appartments;

        echo json_encode($response);

  
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}

