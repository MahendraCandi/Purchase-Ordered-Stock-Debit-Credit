-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2018 at 11:38 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fladeodb`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
  `Kd_Barang` varchar(8) NOT NULL,
  `Nama_Barang` varchar(20) NOT NULL,
  `Hrg_Beli` double NOT NULL,
  `Hrg_Jual` double NOT NULL,
  `Kd_Supplier` varchar(7) NOT NULL,
  `Warna` varchar(10) NOT NULL,
  `size` varchar(5) NOT NULL,
  `foto` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `data_perkiraan`
--

CREATE TABLE IF NOT EXISTS `data_perkiraan` (
  `Kd_Perkiraan` varchar(4) NOT NULL,
  `Nm_Perkiraan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_jurnal`
--

CREATE TABLE IF NOT EXISTS `detail_jurnal` (
  `Id` int(11) NOT NULL,
  `No_Jurnal` varchar(6) NOT NULL,
  `Kd_Perkiraan` varchar(4) NOT NULL,
  `Debet` double NOT NULL,
  `Kredit` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_pelunasan_pembayaran`
--

CREATE TABLE IF NOT EXISTS `detail_pelunasan_pembayaran` (
  `Id` int(11) NOT NULL,
  `No_Pembayaran` varchar(7) NOT NULL,
  `No_Transaksi` varchar(9) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_purchase_order`
--

CREATE TABLE IF NOT EXISTS `detail_purchase_order` (
  `Id` int(11) NOT NULL,
  `No_PO` varchar(12) NOT NULL,
  `Kd_Barang` varchar(8) NOT NULL,
  `Qty_Order` int(11) NOT NULL,
  `Harga_Beli` double NOT NULL,
  `Harga_Jual` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE IF NOT EXISTS `jurnal` (
  `No_Jurnal` varchar(6) NOT NULL,
  `No_Pembayaran` varchar(7) NOT NULL,
  `Tgl_Jurnal` date NOT NULL,
  `Ket` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pelunasan_pembayaran`
--

CREATE TABLE IF NOT EXISTS `pelunasan_pembayaran` (
  `No_Pembayaran` varchar(7) NOT NULL,
  `Jth_Tempo` date NOT NULL,
  `Total_Bayar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `penerimaan_barang`
--

CREATE TABLE IF NOT EXISTS `penerimaan_barang` (
  `No_Tanda_Terima` varchar(5) NOT NULL,
  `No_PO` varchar(12) NOT NULL,
  `Tgl_Terima_Barang` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_order`
--

CREATE TABLE IF NOT EXISTS `purchase_order` (
  `No_PO` varchar(12) NOT NULL,
  `Tgl_PO` date NOT NULL,
  `Tgl_Kirim` date NOT NULL,
  `Kd_Supplier` varchar(3) NOT NULL,
  `Total_Qty` int(11) NOT NULL,
  `Username` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `Kd_Supplier` varchar(7) NOT NULL,
  `Nm_Supplier` varchar(25) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Telepon` varchar(12) NOT NULL,
  `Kota` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembelian`
--

CREATE TABLE IF NOT EXISTS `transaksi_pembelian` (
  `No_Transaksi` varchar(9) NOT NULL,
  `No_Tanda_Terima` varchar(7) NOT NULL,
  `No_Invoice` varchar(8) NOT NULL,
  `Total_Transaksi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(8) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Hak_Akses` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`Kd_Barang`);

--
-- Indexes for table `data_perkiraan`
--
ALTER TABLE `data_perkiraan`
  ADD PRIMARY KEY (`Kd_Perkiraan`);

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
  ADD PRIMARY KEY (`Username`), ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_jurnal`
--
ALTER TABLE `detail_jurnal`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `detail_pelunasan_pembayaran`
--
ALTER TABLE `detail_pelunasan_pembayaran`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `detail_purchase_order`
--
ALTER TABLE `detail_purchase_order`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
