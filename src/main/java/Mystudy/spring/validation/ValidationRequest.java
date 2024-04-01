package Mystudy.spring.validation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data @Getter
@ToString
public class ValidationRequest {

    @NotBlank(message = "가게 이름은 필수입니다.")
    String shopName;

    @Range(min = 0, max = 20000, message = "가격 최소값은 양수 값이어야 합니다.")
    int priceMin;

    @Range(min = 0, max = 20000, message = "가격 최대값은 20000원 미만이어야 합니다.")
    int priceMax;

    @AssertTrue(message = "최소값은 최대값을 넘을 수 없습니다.")
    public boolean isValidPrice(){
        return priceMin < priceMax;
    }

    public ValidationServiceRequest createRequest(){
        return ValidationServiceRequest.builder()
                .shopName(shopName)
                .priceMin(priceMin)
                .priceMax(priceMax)
                .build();
    }
}
