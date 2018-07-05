-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 05, 2018 at 05:58 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fladeodb`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `Id_Barang` int(11) NOT NULL,
  `Kd_Barang` int(8) NOT NULL,
  `Jenis_Barang` varchar(20) NOT NULL,
  `Hrg_Beli` double NOT NULL,
  `Hrg_Jual` double NOT NULL,
  `Kd_Supplier` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `data_akun`
--

CREATE TABLE `data_akun` (
  `Kd_Akun` varchar(8) NOT NULL,
  `Nm_Akun` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_jurnal`
--

CREATE TABLE `detail_jurnal` (
  `Id` int(11) NOT NULL,
  `No_Jurnal` varchar(10) NOT NULL,
  `Kd_Akun` varchar(8) NOT NULL,
  `Debet` double NOT NULL,
  `Kredit` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_pelunasan_pembayaran`
--

CREATE TABLE `detail_pelunasan_pembayaran` (
  `Id` int(11) NOT NULL,
  `No_Pembayaran` varchar(7) NOT NULL,
  `No_Transaksi` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_purchase_order`
--

CREATE TABLE `detail_purchase_order` (
  `Id` int(11) NOT NULL,
  `No_PO` varchar(6) NOT NULL,
  `Art_Barang` varchar(20) NOT NULL,
  `Size` varchar(20) NOT NULL,
  `Qty_Order` int(11) NOT NULL,
  `Harga_Beli` double NOT NULL,
  `Harga_Jual` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `No_Jurnal` varchar(10) NOT NULL,
  `No_Transaksi` double NOT NULL,
  `Tgl_Jurnal` date NOT NULL,
  `Ket` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pelunasan_pembayaran`
--

CREATE TABLE `pelunasan_pembayaran` (
  `No_Pembayaran` varchar(7) NOT NULL,
  `Jth_Tempo` date NOT NULL,
  `Total_Bayar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `penerimaan_barang`
--

CREATE TABLE `penerimaan_barang` (
  `No_Tanda_Terima` varchar(7) NOT NULL,
  `No_PO` varchar(6) NOT NULL,
  `Tgl_Terima_Barang` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_order`
--

CREATE TABLE `purchase_order` (
  `No_PO` varchar(6) NOT NULL,
  `Tgl_PO` date NOT NULL,
  `Tgl_Kirim` date NOT NULL,
  `Kd_Supplier` varchar(7) NOT NULL,
  `Total_Qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `Kd_Supplier` varchar(7) NOT NULL,
  `Nm_Supplier` varchar(50) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `Telepon` varchar(20) NOT NULL,
  `Kota` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembelian`
--

CREATE TABLE `transaksi_pembelian` (
  `No_Transaksi` varchar(9) NOT NULL,
  `No_Tanda_Terima` varchar(7) NOT NULL,
  `No_Invoice` varchar(7) NOT NULL,
  `Total_Transaksi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id_User` int(11) NOT NULL,
  `Username` varchar(8) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Hak_Akses` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id_User`, `Username`, `Nama`, `Hak_Akses`, `Password`) VALUES
(9, 'Fnc-001', 'Player 3', 'Finance', '1234'),
(10, 'Fnc-002', 'Player 3', 'Finance', '1234'),
(11, 'Pch-001', 'Player 3', 'Purchasing', '1234'),
(12, 'Pch-002', 'Player 3', 'Purchasing', '1234'),
(13, 'Pch-003', 'Player 3', 'Purchasing', '1234'),
(14, 'Fnc-003', 'Player 3', 'Finance', '1234'),
(15, 'Fnc-004', 'Player 3', 'Finance', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`Id_Barang`);

--
-- Indexes for table `data_akun`
--
ALTER TABLE `data_akun`
  ADD PRIMARY KEY (`Kd_Akun`);

--
-- Indexes for table `detail_jurnal`
--
ALTER TABLE `detail_jurnal`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `detail_pelunasan_pembayaran`
--
ALTER TABLE `detail_pelunasan_pembayaran`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `detail_purchase_order`
--
ALTER TABLE `detail_purchase_order`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD PRIMARY KEY (`No_Jurnal`);

--
-- Indexes for table `pelunasan_pembayaran`
--
ALTER TABLE `pelunasan_pembayaran`
  ADD PRIMARY KEY (`No_Pembayaran`);

--
-- Indexes for table `penerimaan_barang`
--
ALTER TABLE `penerimaan_barang`
  ADD PRIMARY KEY (`No_Tanda_Terima`);

--
-- Indexes for table `purchase_order`
--
ALTER TABLE `purchase_order`
  ADD PRIMARY KEY (`No_PO`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`Kd_Supplier`);

--
-- Indexes for table `transaksi_pembelian`
--
ALTER TABLE `transaksi_pembelian`
  ADD PRIMARY KEY (`No_Transaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id_User`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `Id_Barang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detail_jurnal`
--
ALTER TABLE `detail_jurnal`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detail_pelunasan_pembayaran`
--
ALTER TABLE `detail_pelunasan_pembayaran`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `detail_purchase_order`
--
ALTER TABLE `detail_purchase_order`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `Id_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
