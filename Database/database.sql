


-- 2. Bảng TAIKHOAN
CREATE TABLE TAIKHOAN (
    taikhoan_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'TK001', 'TK002'
    taikhoan_name VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(20) NOT NULL -- 'ADMIN' hoặc 'DOCGIA'
);


-- 3. Bảng DOCGIA
CREATE TABLE DOCGIA (
    docgia_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'DG001', 'DG002'
    docgia_name NVARCHAR(255) NOT NULL,
    ngaysinh DATE NULL,
    diachi NVARCHAR(255) NULL,
    sdt VARCHAR(15) NULL,
    email VARCHAR(150) NULL,
    taikhoan_id VARCHAR(20) NULL,
    CONSTRAINT FK_DOCGIA_TAIKHOAN FOREIGN KEY (taikhoan_id) REFERENCES TAIKHOAN(taikhoan_id) ON DELETE SET NULL
);


-- 4. Bảng TACGIA
CREATE TABLE TACGIA (
    tacgia_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'TG001'
    tacgia_name NVARCHAR(255) NOT NULL,
    info NVARCHAR(MAX) NULL
);


-- 5. Bảng THELOAI
CREATE TABLE THELOAI (
    theloai_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'TL001'
    theloai_name NVARCHAR(255) NOT NULL,
    info NVARCHAR(MAX) NULL
);


-- 6. Bảng NXB
CREATE TABLE NXB (
    nxb_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'NXB001'
    nxb_name NVARCHAR(255) NOT NULL,
    info NVARCHAR(MAX) NULL
);


-- 7. Bảng SACH (Có lưu đường dẫn ảnh img_sach)
CREATE TABLE SACH (
    sach_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'S001', 'S002'
    sach_name NVARCHAR(255) NOT NULL,
    info_sach NVARCHAR(MAX) NULL,
    img_sach VARCHAR(255) NULL, -- Lưu đường dẫn ảnh (vd: 'uploads/sach_01.jpg')
    tacgia_id VARCHAR(20) NULL,
    theloai_id VARCHAR(20) NULL,
    nxb_id VARCHAR(20) NULL,
    so_luong INT NOT NULL DEFAULT 0,
    CONSTRAINT FK_SACH_TACGIA FOREIGN KEY (tacgia_id) REFERENCES TACGIA(tacgia_id) ON DELETE SET NULL,
    CONSTRAINT FK_SACH_THELOAI FOREIGN KEY (theloai_id) REFERENCES THELOAI(theloai_id) ON DELETE SET NULL,
    CONSTRAINT FK_SACH_NXB FOREIGN KEY (nxb_id) REFERENCES NXB(nxb_id) ON DELETE SET NULL
);


-- 8. Bảng MUONTRA
CREATE TABLE MUONTRA (
    muontra_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'MT001'
    docgia_id VARCHAR(20) NOT NULL,
    thoi_gian_muon DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tra DATETIME NULL,
    trang_thai NVARCHAR(50) NOT NULL DEFAULT N'Đang mượn', -- 'Đang mượn', 'Đã trả', 'Quá hạn'
    CONSTRAINT FK_MUONTRA_DOCGIA FOREIGN KEY (docgia_id) REFERENCES DOCGIA(docgia_id) ON DELETE CASCADE
);


-- 9. Bảng CT_MUONTRA (Chi tiết mượn trả)
CREATE TABLE CT_MUONTRA (
    ctmt_id VARCHAR(20) PRIMARY KEY, -- Ví dụ: 'CTMT001'
    muontra_id VARCHAR(20) NOT NULL,
    sach_id VARCHAR(20) NOT NULL,
    so_luong INT NOT NULL DEFAULT 1,
    CONSTRAINT FK_CTMT_MUONTRA FOREIGN KEY (muontra_id) REFERENCES MUONTRA(muontra_id) ON DELETE CASCADE,
    CONSTRAINT FK_CTMT_SACH FOREIGN KEY (sach_id) REFERENCES SACH(sach_id) ON DELETE CASCADE
);


--CHÈN DỮ LIỆU MẪU CHO CÁC BẢNG 
-- 1. Chèn dữ liệu mẫu cho bảng TAIKHOAN (5 tài khoản)
INSERT INTO TAIKHOAN (taikhoan_id, taikhoan_name, email, password, roles) VALUES
('TK001', 'admin', 'admin@library.edu.vn', '123456', 'ADMIN'),
('TK002', 'puc123', 'puc123@gmail.com', '123456', 'DOCGIA'),
('TK003', 'vinh_it', 'vinh.it@gmail.com', '123456', 'DOCGIA'),
('TK004', 'lan_tran', 'lan.tran@gmail.com', '123456', 'DOCGIA'),
('TK005', 'hung_nguyen', 'hung.nguyen@gmail.com', '123456', 'DOCGIA');


-- 2. Chèn dữ liệu mẫu cho bảng DOCGIA (4 độc giả, liên kết với tài khoản)
INSERT INTO DOCGIA (docgia_id, docgia_name, ngaysinh, diachi, sdt, email, taikhoan_id) VALUES
('DG001', N'Phạm Đức Thắng', '2006-03-24', N'Long Biên, Hà Nội', '0987654321', 'puc123@gmail.com', 'TK002'),
('DG002', N'Nguyễn Văn Vinh', '2005-08-15', N'Cầu Giấy, Hà Nội', '0912345678', 'vinh.it@gmail.com', 'TK003'),
('DG003', N'Trần Thị Lan', '2006-11-02', N'Thanh Xuân, Hà Nội', '0934567890', 'lan.tran@gmail.com', 'TK004'),
('DG004', N'Nguyễn Việt Hùng', '2005-01-20', N'Đống Đa, Hà Nội', '0978123456', 'hung.nguyen@gmail.com', 'TK005');


-- 3. Chèn dữ liệu mẫu cho bảng TACGIA (4 tác giả)
INSERT INTO TACGIA (tacgia_id, tacgia_name, info) VALUES
('TG001', N'Dale Carnegie', N'Diễn giả và nhà văn người Mỹ, tác giả nhiều cuốn sách tự lực nổi tiếng.'),
('TG002', N'Robert C. Martin', N'Chuyên gia phần mềm hàng đầu, tác giả cuốn Clean Code nổi tiếng.'),
('TG003', N'Erich Gamma', N'Một trong 4 tác giả (Gang of Four) của cuốn sách Thiết kế mẫu Design Patterns.'),
('TG004', N'Tô Hoài', N'Nhà văn nổi tiếng của Việt Nam với tác phẩm Dế Mèn Phiêu Lưu Ký.');


-- 4. Chèn dữ liệu mẫu cho bảng THELOAI (4 thể loại)
INSERT INTO THELOAI (theloai_id, theloai_name, info) VALUES
('TL001', N'Công nghệ thông tin', N'Sách về lập trình, mạng máy tính, CSDL và kỹ thuật phần mềm.'),
('TL002', N'Kỹ năng sống', N'Sách phát triển bản thân, giao tiếp và tư duy.'),
('TL003', N'Văn học thiếu nhi', N'Các tác phẩm văn học dành cho thiếu nhi và học sinh.'),
('TL004', N'Kinh tế - Quản trị', N'Sách về tài chính, quản lý doanh nghiệp và khởi nghiệp.');


-- 5. Chèn dữ liệu mẫu cho bảng NXB (4 nhà xuất bản)
INSERT INTO NXB (nxb_id, nxb_name, info) VALUES
('NXB001', N'NXB Trẻ', N'Nhà xuất bản uy tín tại Việt Nam về văn học và kỹ năng.'),
('NXB002', N'NXB Bách Khoa Hà Nội', N'Chuyên xuất bản sách giáo trình và tài liệu kỹ thuật IT/Công nghệ.'),
('NXB003', N'NXB Kim Đồng', N'Nhà xuất bản lớn chuyên về văn học thiếu nhi.'),
('NXB004', N'NXB Lao Động', N'Chuyên xuất bản các đầu sách kỹ năng, kinh tế và phát triển bản thân.');


-- 6. Chèn dữ liệu mẫu cho bảng SACH (5 đầu sách)
INSERT INTO SACH (sach_id, sach_name, info_sach, img_sach, tacgia_id, theloai_id, nxb_id, so_luong) VALUES
('S001', N'Đắc Nhân Tâm', N'Sách hướng dẫn giao tiếp và ứng xử thành công trong cuộc sống.', 'uploads/dac_nhan_tam.jpg', 'TG001', 'TL002', 'NXB001', 10),
('S002', N'Clean Code - Mã Sạch', N'Hướng dẫn chi tiết về cách viết code sạch, dễ bảo trì cho Lập trình viên.', 'uploads/clean_code.jpg', 'TG002', 'TL001', 'NXB002', 5),
('S003', N'Design Patterns', N'Các mẫu thiết kế hướng đối tượng kinh điển trong công nghệ phần mềm.', 'uploads/design_patterns.jpg', 'TG003', 'TL001', 'NXB002', 3),
('S004', N'Dế Mèn Phiêu Lưu Ký', N'Tác phẩm văn học kinh điển dành cho lứa tuổi thiếu nhi.', 'uploads/de_men.jpg', 'TG004', 'TL003', 'NXB003', 15),
('S005', N'Quẳng Gánh Lo Đi Và Vui Sống', N'Bí quyết vượt qua lo âu để sống thanh thản và hạnh phúc.', 'uploads/quang_ganh_lo.jpg', 'TG001', 'TL002', 'NXB004', 8);


-- 7. Chèn dữ liệu mẫu cho bảng MUONTRA (4 lượt mượn trả)
INSERT INTO MUONTRA (muontra_id, docgia_id, thoi_gian_muon, thoi_gian_tra, trang_thai) VALUES
('MT001', 'DG001', '2026-09-01 08:30:00', '2026-09-15 10:00:00', N'Đã trả'),
('MT002', 'DG001', '2026-10-01 09:15:00', '2026-10-15 17:00:00', N'Đang mượn'),
('MT003', 'DG002', '2026-10-05 14:00:00', '2026-10-19 17:00:00', N'Đang mượn'),
('MT004', 'DG003', '2026-09-10 10:30:00', '2026-09-24 17:00:00', N'Quá hạn');


-- 8. Chèn dữ liệu mẫu cho bảng CT_MUONTRA (Chi tiết các sách được mượn)
INSERT INTO CT_MUONTRA (ctmt_id, muontra_id, sach_id, so_luong) VALUES
('CTMT001', 'MT001', 'S001', 1),
('CTMT002', 'MT001', 'S005', 1),
('CTMT003', 'MT002', 'S002', 1),
('CTMT004', 'MT003', 'S003', 2),
('CTMT005', 'MT004', 'S004', 1);



SELECT * FROM TAIKHOAN;
SELECT * FROM DOCGIA;
SELECT * FROM TACGIA;
SELECT * FROM THELOAI;
SELECT * FROM NXB;
SELECT * FROM SACH;
SELECT * FROM MUONTRA;
SELECT * FROM CT_MUONTRA;


