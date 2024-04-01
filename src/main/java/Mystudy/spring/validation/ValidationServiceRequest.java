package Mystudy.spring.validation;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data
@ToString @Getter
public class ValidationServiceRequest {

    String shopName;
    int priceMin;
    int priceMax;

    @Builder
    public ValidationServiceRequest(String shopName, int priceMin, int priceMax) {
        this.shopName = shopName;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }
}
