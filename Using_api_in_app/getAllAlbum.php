<?php
	require "conn.php";

	class Album{
		function Album($idAlbum, $tenAlbum, $tenCaSiAlbum, $hinhAlbum){
			$this->IdAlbum = $idAlbum;
			$this->TenAlbum = $tenAlbum;
			$this->TenCaSiAlbum = $tenCaSiAlbum;
			$this->HinhAlbum = $hinhAlbum;
		}
	}

	$MangAlbum = array();

	$query = "SELECT * FROM Album";
	$dataAlbum = mysqli_query($connect, $query);

	while($row = mysqli_fetch_assoc($dataAlbum)){
		array_push($MangAlbum, new Album($row['IdAlbum'],
										$row['TenAlBum'],
										$row['TenCaSiAlbum'],
										$row['HinhAlbum']));
	}

	echo json_encode($MangAlbum);
?>