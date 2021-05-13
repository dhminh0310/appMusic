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
$mangBaiHat = array();

	if(strlen($_POST['idAlbum']) > 0){

		$idAlbum = $_POST['idAlbum'];
		$query = "SELECT BaiHat.IdBaiHat, BaiHat.TenBaiHat, BaiHat.HinhBaiHat, BaiHat.CaSi, BaiHat.LinkBaiHat, BaiHat.LuotThich FROM `BaiHat` WHERE IdAlbum = '$idAlbum'";
		$data = mysqli_query($connect, $query);

		while($row = mysqli_fetch_assoc($data)){
			array_push($mangBaiHat, new BaiHat($row['IdBaiHat'],
											$row['TenBaiHat'],
											$row['CaSi'],
											$row['HinhBaiHat'],
											$row['LinkBaiHat'],
											$row['LuotThich']));
		}

		echo json_encode($mangBaiHat);

	}
	
?>