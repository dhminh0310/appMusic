<?php
	require "conn.php";
	$query = "SELECT DISTINCT * FROM Album ORDER BY RAND(" . date("Ymd") . ") LIMIT 3";
	$dataAlbum = mysqli_query($connect, $query);

	class Album{
		function Album($idAlbum, $tenAlbum, $tenCaSiAlbum, $hinhAlbum){
			$this->IdAlbum = $idAlbum;
			$this->TenAlbum = $tenAlbum;
			$this->TenCaSiAlbum = $tenCaSiAlbum;
			$this->HinhAlbum = $hinhAlbum;
		}
	}

	$MangAlbum = array();

	while($row = mysqli_fetch_assoc($dataAlbum)){
		array_push($MangAlbum, new Album($row['IdAlbum'],
										$row['TenAlBum'],
										$row['TenCaSiAlbum'],
										$row['HinhAlbum']));
	}

	echo json_encode($MangAlbum);
?>