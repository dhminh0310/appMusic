<?php
	require "conn.php";

	$query = "SELECT BaiHat.IdBaiHat, BaiHat.TenBaiHat, BaiHat.HinhBaiHat, QuangCao.HinhAnh, QuangCao.NoiDung, QuangCao.Id FROM QuangCao INNER JOIN BaiHat ON QuangCao.IdBaiHat = BaiHat.IdBaiHat";

	$data = mysqli_query($connect, $query);

	class QuangCao{
		function QuangCao($idquangcao, $hinhanh, $noidung, $idbaihat, $tenbaihat, $hinhbaihat) {
			$this->Idquangcao = $idquangcao;
			$this->Hinhanh = $hinhanh;
			$this->Noidung = $noidung;
			$this->Idbaihat = $idbaihat;
			$this->Tenbaihat = $tenbaihat;
			$this->Hinhbaihat = $hinhbaihat;
		}
	}

	$MangQuangCao = array();

	while($row = mysqli_fetch_assoc($data)){
		array_push($MangQuangCao, new QuangCao($row['Id'], $row['HinhAnh'],$row['NoiDung'], $row['IdBaiHat'], $row['TenBaiHat'], $row['HinhBaiHat']));
	}

	echo json_encode($MangQuangCao);
?>