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

	if(strlen($_POST['idquangcao']) > 0){
		$idquangcao = $_POST['idquangcao'];
		$query = "SELECT * FROM QuangCao WHERE Id = '$idquangcao'";
		$data = mysqli_query($connect, $query);
		$rowQuangCao = mysqli_fetch_assoc($data);
		$idSong = $rowQuangCao['IdBaiHat'];
	}

	

	$queryBaiHat = "SELECT * FROM BaiHat WHERE IdBaiHat = '$idSong'";
	$dataBaiHat = mysqli_query($connect, $queryBaiHat);
	while($row = mysqli_fetch_assoc($dataBaiHat)){
		array_push($mangBaiHat, new BaiHat($row['IdBaiHat'],
										$row['TenBaiHat'],
										$row['CaSi'],
										$row['HinhBaiHat'],
										$row['LinkBaiHat'],
										$row['LuotThich']));
	}

	echo json_encode($mangBaiHat);
?>