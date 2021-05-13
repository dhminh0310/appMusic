<?php
	require "conn.php";

	$query = "SELECT * FROM Playlist";

	$data = mysqli_query($connect, $query);

	class Playlist{
		function Playlist($id, $TenPlaylist, $HinhNenPlaylist, $HinhIconPlaylist){
			$this->mId = $id;
			$this->mTenPlaylist = $TenPlaylist;
			$this->mHinhNenPlaylist = $HinhNenPlaylist;
			$this->mHinhIconPlaylist = $HinhIconPlaylist;
		}
	}

	$mangPlaylist = array();

	while($row = mysqli_fetch_assoc($data)) {
		array_push($mangPlaylist, new Playlist($row['IdPlaylist'], $row['TenPlaylist'], $row['HinhNenPlaylist'], $row['HinhIconPlaylist']));
	}
	echo json_encode($mangPlaylist);
?>