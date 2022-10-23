package com.dodo.bootawsproject.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDTOTest {
    @Test
    public void lombok_func_test() {
        // given
        String name = "gyuchan";
        int amount = 1557;

        // when
        HelloResponseDTO dto = new HelloResponseDTO(name,amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /* assertj : 테스트 검증 라이브러리, junit보다 편리한 면이 있음.
           assertThat() : 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 넣음 + 메서드 체이닝 지원, 메소드를 이어서 사용 가능
           isEqualTo() : 동등 비교 메서드. 앞의 값과 인자 값을 비교하여 같을 때만 성공.
         */
    }
}
