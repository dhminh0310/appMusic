<?php
	require "conn.php";
	$query = "SELECT * FROM BaiHat ORDER BY LuotThich DESC LIMIT 4";
	$dataBaihat = mysqli_query($connect, $query);

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

	$MangBaiHat = array();

	while($row = mysqli_fetch_assoc($dataBaihat)){
		array_push($MangBaiHat, new BaiHat($row['IdBaiHat'],
										$row['TenBaiHat'],
										$row['CaSi'],
										$row['HinhBaiHat'],
										$row['LinkBaiHat'],
										$row['LuotThich']));
	}

	echo json_encode($MangBaiHat);
?>