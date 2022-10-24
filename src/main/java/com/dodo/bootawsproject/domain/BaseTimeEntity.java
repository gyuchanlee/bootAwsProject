package com.dodo.bootawsproject.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 모든 jap Entity들이 이 Entity를 상속 시, 필드도 함께 상속하도록 함.
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 Auditing 기능 포함.
public class BaseTimeEntity { // 데이터 등록/수정 시간 관리 Entity : 공통 기능

    @CreatedDate // Entity 생성 및 저장 시간을 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경한 시간을 자동 저장
    private LocalDateTime modifiedDate;
}
