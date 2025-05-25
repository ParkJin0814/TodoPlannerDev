use todoplanner;

CREATE TABLE `user` (
                        `id`	bigint AUTO_INCREMENT PRIMARY KEY COMMENT '유저 고유 식별값',
                        `name`	varchar(4)	NOT NULL	COMMENT '이름',
                        `email`	varchar(40)	NOT NULL unique 	COMMENT '이메일',
                        `password`	varchar(255)	NOT NULL	COMMENT '비밀번호',
                        `created_at`	timestamp	NOT NULL	COMMENT '생성일',
                        `updated_at`	timestamp	NOT NULL	COMMENT '수정일'
);

CREATE TABLE `plan` (
                        `id`	bigint AUTO_INCREMENT PRIMARY KEY COMMENT '일정 고유 식별 값',
                        `user_id`	bigint	NOT NULL	COMMENT '유저 외래키',
                        `title`	varchar(10)	NOT NULL	COMMENT '할일 제목',
                        `contents`	varchar(200)	NOT NULL	COMMENT '할일 내용',
                        `created_at`	timestamp	NOT NULL	COMMENT '생성일',
                        `updated_at`	timestamp	NOT NULL	COMMENT '수정일',
                        foreign key (user_id) references user(id)
);

CREATE TABLE `comment` (
                           `id`	bigint AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 고유 식별값',
                           `user_id`	bigint	NOT NULL	COMMENT '유저 외래키',
                           `plan_id`	bigint	NOT NULL	COMMENT '일정 외래키',
                           `contents`	varchar(100)	NOT NULL	COMMENT '댓글내용',
                           `created_at`	timestamp	NOT NULL	COMMENT '생성일',
                           `updated_at`	timestamp	NOT NULL	COMMENT '수정일',
                           foreign key (user_id) references user(id),
                           foreign key (plan_id) references plan(id)
);
