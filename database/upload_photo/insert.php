<?PHP

include_once('koneksi.php');

if (!empty($_POST['nama']) && !empty($_POST['jenis']) && !empty($_POST['suplier']) && !empty($_POST['jumlah']) && !empty($_POST['harga'])){

    $nm = $_POST['nama'];
    $jns = $_POST['jenis'];
    $sup = $_POST['suplier'];
    $jml = $_POST['jumlah'];
    $hrg = $_POST['harga'];

    if(empty($_FILES['image'])){

    	set_response(false,"jangan lupa isi foto kak");
    }
    else{
$fileName = $_FILES['image']['name'];
$file = $_FILES['image']['tmp_name'];

move_uploaded_file($file, 'image/'.$fileName);

    $query = "INSERT INTO barang (barang_name, barang_jenis, barang_suplier, barang_jumlah, barang_harga,image) 
                VALUES ('$nm', '$jns', '$sup', '$jml', '$hrg','$fileName')";
    
    $insert = mysqli_query($connect, $query);

    if ($insert){
        set_response(true, "Tambah data berhasil!");
    }else{
        set_response(false, "Tambah data gagal!");
    }
}

}else{
    set_response(false, "Semua kolom harus diisi!");
}


function set_response($isSuccess, $message){
    $result = array(
                'isSuccess' => $isSuccess,
                'message' => $message
            );
    echo json_encode($result);
}

?>