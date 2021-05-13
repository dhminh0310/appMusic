<?php
	require "conn.php";

	class BaiHat{
		function BaiHat($idBaiHat, $tenBaiHat, $tenCaSi, $hinhBaiHat, $linkBaihat, $luotThich){
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $tenBaiHat;
			$this->TenCaSi = $tenCaSi;
			$this->HinhBaiHat = $hinhBaiHat;
			$this->LinkBaiHat = $linkBaihat;
			$this->LuotThich = $luotThich;
		}
	}

	$searchKey = $_POST['keySearch'];
	
	$query = "SELECT IdBaiHat, TenBaiHat, CaSi, HinhBaiHat, LinkBaiHat, LuotThich FROM BaiHat WHERE TenBaiHat LIKE '%$searchKey%'";
	
	
	$data = mysqli_query($connect, $query);

	$mangBaihat = array();
    
    
        while($row = mysqli_fetch_assoc($data)){
			array_push($mangBaihat, new BaiHat($row['IdBaiHat'],
											$row['TenBaiHat'],
											$row['CaSi'],
											$row['HinhBaiHat'],
											$row['LinkBaiHat'],
											$row['LuotThich']));
        }
       echo json_encode($mangBaihat);
		
?>