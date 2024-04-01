package Mystudy.spring.validation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor @Getter
public class Validation {

    @Id @GeneratedValue
    private Long id;

    @NotBlank(message = "가게 이름은 필수입니다.")
    String shopName;

    @Range(min = 0, max = 20000, message = "가격 최소값은 양수 값이어야 합니다.")
    int priceMin;

    @Range(min = 0, max = 20000, message = "가격 최대값은 20000원 미만이어야 합니다.")
    int priceMax;

}
