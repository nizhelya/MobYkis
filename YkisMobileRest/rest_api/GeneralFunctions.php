<?php

$DBOperations_path = "../sql/DBOperations.php";
include($DBOperations_path);

class GeneralFunctionsClass
{
    public function __constructor()
    {

    }


    
  public function getAppartmentsById($resultAppartments)
    {
        $dbOperationsObject = new DBOperations();
        $appartments = array();
        while ($rowAppartment = mysqli_fetch_array($resultAppartments)) {
             $appartment = array(
                'address_id' => $rowAppartment['address_id'],
                'address' => $rowAppartment['address'],
                'nanim' => $rowAppartment['nanim'],
                'fio' => $rowAppartment['fio'],
                'order' => $rowAppartment['order'],
                'data' => $rowAppartment['data'],
                'area_full' => $rowAppartment['area_full'],
                'area_life' => $rowAppartment['area_life'],
                'area_dop' => $rowAppartment['area_dop'],
                'area_balk' => $rowAppartment['area_balk'],
                'area_otopl' => $rowAppartment['area_otopl'],

                'tenant' => $rowAppartment['tenant'],
                'podnan' => $rowAppartment['podnan'],
                'absent' => $rowAppartment['absent'],
                'email' => $rowAppartment['email'],
                'phone' => $rowAppartment['phone'],
                'raion_id' => $rowAppartment['raion_id'],
                'house_id' => $rowAppartment['house_id'],
                'tenant_tbo' => $rowAppartment['tenant_tbo'],
                'room' => $rowAppartment['room'],
                'privat' => $rowAppartment['privat'],
                'subsidia' => $rowAppartment['subsidia'],
                'vxvoda' => $rowAppartment['vxvoda'],
                'teplomer' => $rowAppartment['teplomer'],
                'distributor' => $rowAppartment['distributor'],
                'lift' => $rowAppartment['lift'],
                'kvartplata' => $rowAppartment['kvartplata'],
                'otoplenie' => $rowAppartment['otoplenie'],
                'ateplo' => $rowAppartment['ateplo'],
                'podogrev' => $rowAppartment['podogrev'],
                'voda' => $rowAppartment['voda'],
                'stoki' => $rowAppartment['stoki'],
                'avoda' => $rowAppartment['avoda'],
                'astoki' => $rowAppartment['astoki'],
                'tbo' => $rowAppartment['tbo'],
                'aggr_kv' => $rowAppartment['aggr_kv'],
                'aggr_voda' => $rowAppartment['aggr_voda'],
                'aggr_teplo' => $rowAppartment['aggr_teplo'],
                'aggr_tbo' => $rowAppartment['aggr_tbo'],
                'boiler' => $rowAppartment['boiler'],
                'enaudit' => $rowAppartment['enaudit'],
                'heated' => $rowAppartment['heated'],
                'ztp' => $rowAppartment['ztp'],
                'ovu' => $rowAppartment['ovu'],
                'paused' => $rowAppartment['paused'],
                'osmd' => $rowAppartment['osmd'],
                'osmd_id' => $rowAppartment['osmd_id'],
                'what_change' => $rowAppartment['what_change'],

                'data_change' => $rowAppartment['data_change'],
                'enaudit_id' => $rowAppartment['enaudit_id'],
                'tarif_kv' => $rowAppartment['tarif_kv'],
                'tarif_ot' => $rowAppartment['tarif_ot'],
                'tarif_aot' => $rowAppartment['tarif_aot'],
                'tarif_gv' => $rowAppartment['tarif_gv'],
                'tarif_xv' => $rowAppartment['tarif_xv'],
                'tarif_st' => $rowAppartment['tarif_st'],
                'tarif_tbo' => $rowAppartment['tarif_tbo'],
                'tne' => $rowAppartment['tne'],
                'kte' => $rowAppartment['kte'],
                'length' => $rowAppartment['length'],
                'diametr' => $rowAppartment['diametr'],
                'dvodomer_id' => $rowAppartment['dvodomer_id'],
                'dteplomer_id' => $rowAppartment['dteplomer_id'],
                'data_in' => $rowAppartment['data_in'],
                'operator' => $rowAppartment['operator'],
                'kod' => $rowAppartment['kod']               
            );
            
            array_push($appartments, $appartment);
        }
        return $appartments;
    }
    
    public function getAppartmentsByUser($resultAppartmentsMyflat)
    {
        $dbOperationsObject = new DBOperations();
        $appartments = array();
        while ($rowAppartment = mysqli_fetch_array($resultAppartmentsMyflat)) {
             $appartment = array(
                 'id'=>$rowAppartment['id'],
                 'user_id'=>$rowAppartment['user_id'],
                'address_id' => $rowAppartment['address_id'],
                'address' => $rowAppartment['address'],
                'nanim' => $rowAppartment['nanim'],
                'fio' => $rowAppartment['fio'],
                'order' => $rowAppartment['order'],
                'data' => $rowAppartment['data'],
                'area_full' => $rowAppartment['area_full'],
                'area_life' => $rowAppartment['area_life'],
                'area_dop' => $rowAppartment['area_dop'],
                'area_balk' => $rowAppartment['area_balk'],
                'area_otopl' => $rowAppartment['area_otopl'],

                'tenant' => $rowAppartment['tenant'],
                'podnan' => $rowAppartment['podnan'],
                'absent' => $rowAppartment['absent'],
                'email' => $rowAppartment['email'],
                'phone' => $rowAppartment['phone'],
                'raion_id' => $rowAppartment['raion_id'],
                'house_id' => $rowAppartment['house_id'],
                'tenant_tbo' => $rowAppartment['tenant_tbo'],
                'room' => $rowAppartment['room'],
                'privat' => $rowAppartment['privat'],
                'subsidia' => $rowAppartment['subsidia'],
                'vxvoda' => $rowAppartment['vxvoda'],
                'teplomer' => $rowAppartment['teplomer'],
                'distributor' => $rowAppartment['distributor'],
                'lift' => $rowAppartment['lift'],
                'kvartplata' => $rowAppartment['kvartplata'],
                'otoplenie' => $rowAppartment['otoplenie'],
                'ateplo' => $rowAppartment['ateplo'],
                'podogrev' => $rowAppartment['podogrev'],
                'voda' => $rowAppartment['voda'],
                'stoki' => $rowAppartment['stoki'],
                'avoda' => $rowAppartment['avoda'],
                'astoki' => $rowAppartment['astoki'],
                'tbo' => $rowAppartment['tbo'],
                'aggr_kv' => $rowAppartment['aggr_kv'],
                'aggr_voda' => $rowAppartment['aggr_voda'],
                'aggr_teplo' => $rowAppartment['aggr_teplo'],
                'aggr_tbo' => $rowAppartment['aggr_tbo'],
                'boiler' => $rowAppartment['boiler'],
                'enaudit' => $rowAppartment['enaudit'],
                'heated' => $rowAppartment['heated'],
                'ztp' => $rowAppartment['ztp'],
                'ovu' => $rowAppartment['ovu'],
                'paused' => $rowAppartment['paused'],
                'osmd' => $rowAppartment['osmd'],
                'osmd_id' => $rowAppartment['osmd_id'],
                'what_change' => $rowAppartment['what_change'],

                'data_change' => $rowAppartment['data_change'],
                'enaudit_id' => $rowAppartment['enaudit_id'],
                'tarif_kv' => $rowAppartment['tarif_kv'],
                'tarif_ot' => $rowAppartment['tarif_ot'],
                'tarif_aot' => $rowAppartment['tarif_aot'],
                'tarif_gv' => $rowAppartment['tarif_gv'],
                'tarif_xv' => $rowAppartment['tarif_xv'],
                'tarif_st' => $rowAppartment['tarif_st'],
                'tarif_tbo' => $rowAppartment['tarif_tbo'],
                'tne' => $rowAppartment['tne'],
                'kte' => $rowAppartment['kte'],
                'length' => $rowAppartment['length'],
                'diametr' => $rowAppartment['diametr'],
                'dvodomer_id' => $rowAppartment['dvodomer_id'],
                'dteplomer_id' => $rowAppartment['dteplomer_id'],
                'data_in' => $rowAppartment['data_in'],
                'operator' => $rowAppartment['operator'],
                'kod' => $rowAppartment['kod']
            );

            array_push($appartments, $appartment);
        }
        return $appartments;
    }
   public function getBlocks($resultBlocks)
    {
        $dbOperationsObject = new DBOperations();
        $blocks = array();
        while ($rowBlocks = mysqli_fetch_array($resultBlocks)) {
             $block = array(
                 'raion_id'=>$rowBlocks['raion_id'],
                 'raion'=>$rowBlocks['raion']
            );

            array_push($blocks, $block);
        }
        return $blocks;
    }
    public function getStreetsFromBlock($resultStreets)
    {
        $dbOperationsObject = new DBOperations();
        $streets = array();
        while ($rowStreets = mysqli_fetch_array($resultStreets)) {
             $street = array(
                 'street_id'=>$rowStreets['street_id'],
                 'street'=>$rowStreets['street']
            );

            array_push($streets, $street);
        }
        return $streets;
    }

    public function getHousesFromStreet($resultHouses)
    {
        $dbOperationsObject = new DBOperations();
        $houses = array();
       // print_r($resultHouses);
        while ($rowHouses = mysqli_fetch_array($resultHouses)) {
             $house = array(
                 'house_id'=>$rowHouses['house_id'],
                 'house'=>$rowHouses['house']
            );

            array_push($houses, $house);
        }
        return $houses;
    }

    public function getFlatsFromHouse($resultFlats)
    {
        $dbOperationsObject = new DBOperations();
        $flats = array();
        while ($rowFlats = mysqli_fetch_array($resultFlats)) {
             $flat = array(
                 'address_id'=>$rowFlats['address_id'],
                 'address'=>$rowFlats['address'],
                 'kod'=>$rowFlats['kod']
            );

            array_push($flats, $flat);
        }
        return $flats;
    }
    public function addFlatByUser($resultFlats)
    {
        $dbOperationsObject = new DBOperations();
        $results = array();
        //print_r($resultFlats);
        while ($rowFlats = mysqli_fetch_array($resultFlats)) {
             $flat = array(
                 'success'=>$rowFlats[0],
                 'message'=>$rowFlats[1],
                 'address'=>array()
            );

            array_push($results, $flat);
//             print_r($flat);
        }
        return $results;
    }
    public function getFamilyFromFlat($resultFamily)
    {
        $dbOperationsObject = new DBOperations();
        $families = array();
        while ($row = mysqli_fetch_array($resultFamily)) {
             $family = array(
                 'rec_id'=>$row['rec_id'],
                 'address_id'=>$row['address_id'],
                 'address'=>$row['address'],
                 'rodstvo'=>$row['rodstvo'],
                 'firstname'=>$row['firstname'],
                 'surname'=>$row['surname'],
                 'lastname'=>$row['lastname'],
                 'born'=>$row['born'],
                 'sex'=>$row['sex'],
                 'phone'=>$row['phone'],
                 'subsidia'=>$row['subsidia'],
                 'vkl'=>$row['vkl']
            );

            array_push($families, $family);
        }
        return $families;
    }
}
