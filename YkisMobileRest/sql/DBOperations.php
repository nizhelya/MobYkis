<?php
include_once "DB.php";

class DBOperations {
  public function __constructor() {

  }
  
//appartment
  public function getAppartmentsById($address_id){
    $com = new DbConnect();
    $sql = 'SELECT * FROM YIS.APPARTMENT as t1 WHERE t1.address_id= '.$address_id.'';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }

    public function getAppartmentsByUser($user_id){
    $com = new DbConnect();
    $sql = 'SELECT  t2.id , t2.user_id ,  t1.`address_id`, t1.`raion_id`, t1.`house_id`, (case when t3.`osmd_id` = 0 then "Внески ОСББ" else t3.abbr end) as osbb, t1.`kod`, t1.`address`, t1.`nanim`, t1.`fio`, t1.`order`, t1.`data`, t1.`area_full`, t1.`area_life`, t1.`area_dop`, t1.`area_balk`, t1.`area_otopl`, t1.`room`,
    (case when t1.`privat` = "да" then true else false end) as privat , t1.`tenant`, t1.`tenant_tbo`, t1.`podnan`, t1.`absent`,(case when t1.`subsidia` = "да" then true else false end) as subsidia, (case when t1.`vxvoda` = "да" then true else false end) as vxvoda, (case when t1.`teplomer` = "да" then true else false end) as teplomer, t1.`distributor`, t1.`dvodomer_id`, t1.`dteplomer_id`,(case when t1.`lift` = "да" then true else false end) as lift, (case when t1.`kvartplata` = "да" then true else false end) as kvartplata,
    (case when t1.`otoplenie` = "да" then true else false end) as otoplenie, (case when t1.`privat` = "да" then true else false end) as ateplo, (case when t1.`podogrev` = "да" then true else false end) as podogrev, (case when t1.`voda` = "да" then true else false end) as voda, (case when t1.`stoki` = "да" then true else false end) as stoki, (case when t1.`avoda` = "да" then true else false end) as avoda, (case when t1.`astoki` = "да" then true else false end) as astoki, (case when t1.`tbo` = "да" then true else false end) as tbo, t1.`tarif_kv`, t1.`tarif_ot`, t1.`tarif_aot`, t1.`tarif_gv`, t1.`tarif_xv`, t1.`tarif_st`, t1.`tarif_tbo`, (case when t1.`aggr_kv` = "да" then true else false end) as aggr_kv, (case when t1.`aggr_voda` = "да" then true else false end) as aggr_voda ,(case when t1.`aggr_teplo` = "да" then true else false end) as aggr_teplo,(case when t1.`aggr_tbo` = "да" then true else false end) as aggr_tbo,   (case when t1.`boiler` = "да" then true else false end) as boiler, t1.`enaudit`, t1.`enaudit_id`, t1.`tne`, t1.`kte`, t1.`length`, t1.`diametr`, t1.`heated`, t1.`ztp`, t1.`ovu`, t1.`paused`, t1.`phone`, t1.`email`, t1.`osmd`, t1.`osmd_id`, t1.`what_change`, t1.`operator`, t1.`data_change`, t1.`data_in`FROM YISGRAND.MYFLAT as t2  LEFT JOIN YIS.APPARTMENT as t1 USING(`address_id`) LEFT JOIN YIS.HOUSE as t3 on t3.house_id = t2.house_id WHERE  t2.user_id ='.$user_id.' ';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
  //blocks
  public function getBlocks(){
    $com = new DbConnect();
    $sql = 'SELECT * FROM YIS.RAION as t1 WHERE t1.raion_id in (1,3,4,5,2,6,7,11,12) ORDER BY t1.raion_id';
//   $sql = 'SELECT "0" as raion_id , "Выберите район" as raion UNION SELECT t2.raion_id , t2.raion FROM YIS.RAION as t2 WHERE t2.raion_id in (1,3,4,5,2,6,7,11,12)';
    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
  //streets
  public function getStreetsFromBlock($block_id){
    $com = new DbConnect();
    $sql = 'SELECT t1.street_id, t1.street FROM YIS.STREET as t1 ,YIS.HOUSE as t2'
    .' WHERE t1.street_id=t2.street_id AND t2.raion_id= '.$block_id.' GROUP BY t1.street_id ORDER BY t1.street';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
   //houses
  public function getHousesFromStreet($street_id , $block_id){
    $com = new DbConnect();
    $sql = 'SELECT t1.house_id, t1.house FROM YIS.HOUSE as t1'
    .' WHERE t1.street_id='.$street_id.' AND t1.raion_id= '.$block_id.' ORDER BY t1.house';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
   //flats
  public function getFlatsFromHouse($house_id){
    $com = new DbConnect();
    $sql = 'SELECT t1.address_id , t1.address , t1.kod FROM YIS.ADDRESS as t1 WHERE t1.house_id = '.$house_id.' ORDER BY CAST(t1.appartment AS signed)';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
  //insertFlat
  public function addFlatByUser($address_id ,$user_id){
    $com = new DbConnect();

    $sql = 'CALL YKIS.addMyFlatByUser('.$address_id.' , '.$user_id.' , @success , @message);';
    mysqli_query( $com->getDb(), $sql);
    $sqlCallBack = 'SELECT @success , @message ';
    $result = mysqli_query( $com->getDb(), $sqlCallBack);
    return $result;
  }
  //family
  public function getFamilyFromFlat($address_id){
    $com = new DbConnect();
    $sql = 'SELECT t1.* , (case when t1.`subsidia` = "да" then TRUE else FALSE end) as subsidia ,  (case when t1.`vkl` = "да" then TRUE else FALSE end) as vkl , (case when t1.`sex` = "Men" then "Чоловік" when t1.`sex` = "Women" then "Жінка" else "" end) as sex FROM YISGRAND.FAMALY as t1 WHERE t1.address_id = '.$address_id.' ORDER BY t1.surname  , t1.firstname ';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }

  public function checkAddFlat($address_id , $kod){
    $com = new DbConnect();
    $sql = 'SELECT COUNT(t1.address_id) as success FROM YIS.APPARTMENT as t1  WHERE t1.address_id = '.$address_id.' and t1.kod = "'.$kod.'" ';

    $result = mysqli_query($com->getDb(), $sql);
    return $result;
  }
public function updateBti($address_id , $phone , $email ,$user_id){
    $com = new DbConnect();

    $sql = 'UPDATE YIS.APPARTMENT as t1 SET t1.email = "'.$email.'" , t1.phone = "'.$phone.'" WHERE t1.address_id = '.$address_id.' ';
    mysqli_query( $com->getDb(), $sql);
    return $com->getDb();
  }
   public function deleteFlatByUser($address_id , $user_id){
    $com = new DbConnect();
    $sql = 'DELETE FROM  YISGRAND.MYFLAT WHERE address_id = '.$address_id.'  and user_id = '.$user_id.' ';
    mysqli_query( $com->getDb(), $sql);
    return $com->getDb();
  }
//   public function getAccruedByFlat($address_id ,$usluga){
//     $com = new DbConnect();
//     switch($usluga)
//       case 1 :
//     $sql = 'SELECT CONCAT_WS(" ",t1.mec,t1.god) as period, IFNULL(t1.zadol , 0) as zadol1, IFNULL(t2.zadol , 0 ) as zadol2, IFNULL(t12.zadol , 0) as zadol3 , IFNULL(t13.zadol, 0) as zadol4,
// 					      IFNULL(t1.zadol , 0) + IFNULL(t2.zadol , 0) + IFNULL(t12.zadol , 0 ) + IFNULL(t13.zadol ,0) as zadol,
//
// 					      IFNULL(t1.nachisleno , 0) + IFNULL(t2.nachisleno,0) + IFNULL(t12.nachisleno , 0) + IFNULL(t13.nachisleno ,0) as nachisleno,
// 					      IFNULL(t1.oplacheno ,0) + IFNULL(t2.oplacheno , 0) + IFNULL(t12.oplacheno,0) + IFNULL(t13.oplacheno , 0) as oplacheno,
// 					      IFNULL(t1.dolg , 0) + IFNULL(t2.dolg  , 0)+ IFNULL(t12.dolg , 0) + IFNULL(t13.dolg , 0) as dolg
// 					      FROM YIS.VODA as t1
//                           LEFT JOIN YIS.STOKI as t2 USING(address_id,data)
//                           LEFT JOIN YIS.AVODA as t12 USING(address_id , data)
//                           LEFT JOIN YIS.ASTOKI as t13 USING(address_id , data)
// 					       WHERE t1.address_id = 8256 ORDER BY t1.data DESC LIMIT 12; ';
//     break;
//       case 2 :
//     $sql = 'SELECT CONCAT_WS(" ",t1.mec,t1.god) as period1, IFNULL(t1.zadol , 0) as zvoda, IFNULL(t2.zadol , 0 ) as zstoki, IFNULL(t12.zadol , 0) as zavoda , IFNULL(t13.zadol, 0) as zastoki,
// 					      IFNULL(t1.zadol , 0) + IFNULL(t2.zadol , 0) + IFNULL(t12.zadol , 0 ) + IFNULL(t13.zadol ,0) as zadol,
//
// 					      IFNULL(t1.nachisleno , 0) + IFNULL(t2.nachisleno,0) + IFNULL(t12.nachisleno , 0) + IFNULL(t13.nachisleno ,0) as nachisleno,
// 					      IFNULL(t1.oplacheno ,0) + IFNULL(t2.oplacheno , 0) + IFNULL(t12.oplacheno,0) + IFNULL(t13.oplacheno , 0) as oplacheno,
// 					      IFNULL(t1.dolg , 0) + IFNULL(t2.dolg  , 0)+ IFNULL(t12.dolg , 0) + IFNULL(t13.dolg , 0) as dolg
// 					      FROM YIS.VODA as t1
//                           LEFT JOIN YIS.STOKI as t2 USING(address_id,data)
//                           LEFT JOIN YIS.AVODA as t12 USING(address_id , data)
//                           LEFT JOIN YIS.ASTOKI as t13 USING(address_id , data)
// 					       WHERE t1.address_id = 8256 ORDER BY t1.data DESC LIMIT 12;';
//     break;
//     $result = mysqli_query($com->getDb(), $sql);
//     return $result;
//   }
}
