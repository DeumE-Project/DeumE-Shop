DROP DATABASE IF EXISTS chunjaeshop;
CREATE DATABASE chunjaeshop;
USE chunjaeshop;

CREATE TABLE customer (
	customer_idx INT PRIMARY KEY AUTO_INCREMENT,
    customer_id VARCHAR(20) NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(100) NOT NULL,
    customer_phone VARCHAR(50) NOT NULL,
    customer_password VARCHAR(150) NOT NULL,
    customer_joined DATETIME DEFAULT CURRENT_TIMESTAMP,
    customer_address1 VARCHAR(100), # api를 사용해서 받아온 주소 
    customer_address2 VARCHAR(100), # 고객 상세주소 (동/호수)
    customer_money INT DEFAULT 0,
    customer_authority VARCHAR(50) DEFAULT 'ROLE_CUSTOMER'
);
    
CREATE TABLE seller (
	seller_idx INT PRIMARY KEY AUTO_INCREMENT,
    seller_id VARCHAR(20) NOT NULL,
    seller_name VARCHAR(50) NOT NULL,
    seller_email VARCHAR(100) NOT NULL,
    seller_phone VARCHAR(50) NOT NULL,
    seller_password VARCHAR(150) NOT NULL,
    seller_joined DATETIME DEFAULT CURRENT_TIMESTAMP,
    seller_address1 VARCHAR(100), # api를 사용해서 받아온 주소 
    seller_address2 VARCHAR(100), # 판매자 상세주소 (동/호수)
    seller_income INT DEFAULT 0, # 판매자 매출
    seller_authority VARCHAR(50) DEFAULT 'ROLE_SELLER'
);

CREATE TABLE category (
	category_idx INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL
);
    
CREATE TABLE product (
	product_idx INT PRIMARY KEY AUTO_INCREMENT,
    seller_idx INT, # 외래키
    category_idx INT, # 외래키
    product_name VARCHAR(100) NOT NULL,
    product_explain VARCHAR(500) NOT NULL, # 상품 간단 설명
    product_price INT NOT NULL,
    product_stock INT, # 상품 재고수량
    product_thumb_saved VARCHAR(150) NOT NULL, # 상품 리스트 페이지에서 보여질 썸네일 파일명
    product_img_original VARCHAR(150) NOT NULL, # 상품 메인이미지 판매자가 올린 원래 파일명
    product_img_saved VARCHAR(150) NOT NULL, # 상품 메인이미지 서버에 저장된 파일명
    product_detail_original VARCHAR(150) NOT NULL, # 상품 상세 설명이미지 판매자가 올린 원래 파일명
    product_detail_saved VARCHAR(150) NOT NULL, # 상품 상세 설명이미지 서버에 저장된 파일명
    product_status INT NOT NULL DEFAULT 1, # 1번: 판매가능/0번: 품절(판매중지)
    FOREIGN KEY (seller_idx) REFERENCES seller (seller_idx) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (category_idx) REFERENCES category (category_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE product_review (
	review_idx INT PRIMARY KEY AUTO_INCREMENT,
    customer_idx INT, # 외래키
    product_idx INT, # 외래키
    review_content VARCHAR(500) NOT NULL,
    review_star INT NOT NULL, #별점 개수
    review_thumb_saved VARCHAR(150) NOT NULL,
    review_img_original VARCHAR(150) NOT NULL,
    review_img_saved VARCHAR(150) NOT NULL,
    review_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_idx) REFERENCES customer (customer_idx) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (product_idx) REFERENCES product (product_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE product_question (
	question_idx INT PRIMARY KEY AUTO_INCREMENT,
    customer_idx INT, # 외래키
    product_idx INT, # 외래키
    question_content VARCHAR(500) NOT NULL,
    FOREIGN KEY (customer_idx) REFERENCES customer (customer_idx) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (product_idx) REFERENCES product (product_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE product_answer (
	answer_idx INT PRIMARY KEY AUTO_INCREMENT,
    seller_idx INT, # 외래키
    question_idx INT, # 외래키
    answer_content VARCHAR(500) NOT NULL,
    FOREIGN KEY (seller_idx) REFERENCES seller (seller_idx) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (question_idx) REFERENCES product_question (question_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE order_product (
	order_idx INT PRIMARY KEY AUTO_INCREMENT,
    customer_idx INT, # 외래키
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    order_total_price INT NOT NULL,
    order_status INT NOT NULL DEFAULT 1, # 1번:배송중/2번:배송완료
    FOREIGN KEY (customer_idx) REFERENCES customer (customer_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE order_detail (
	order_detail_idx INT PRIMARY KEY AUTO_INCREMENT,
    order_idx INT, # 외래키
    product_idx INT, # 외래키
    product_price INT,
    product_count INT,
    product_total_price INT,
    FOREIGN KEY (order_idx) REFERENCES order_product (order_idx) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (product_idx) REFERENCES product (product_idx) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE notice (
	notice_idx INT PRIMARY KEY AUTO_INCREMENT,
    notice_title VARCHAR(100) NOT NULL,
    notice_content VARCHAR(500) NOT NULL,
    notice_date DATETIME DEFAULT CURRENT_TIMESTAMP
);